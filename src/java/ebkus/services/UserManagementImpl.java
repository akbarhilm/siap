package ebkus.services;

import ebkus.model.Menu;
import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Service;

/**
 *
 * @author sapto
 */
@Service("userManagementServices")
public class UserManagementImpl extends JdbcDaoSupport implements UserManagementServices {

    private static final Logger log = LoggerFactory.getLogger(UserManagementImpl.class);

    @Autowired
    public UserManagementImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public UserManagementImpl() {
    }

    @Override
    public Pengguna loadUserByUsername(final String username, final String pass) {
        try {
            // idns , tutup ganti dg yg dibawah , final String sql = "SELECT T.I_ID, T.C_PGUN_TAPD, 'ROLE_'||T.C_PGUN_TAPD AS AUTHORITY, T.I_PGUN,T.I_SANDI, T.I_IP_ADDRESS, T.N_EMAIL, T.I_PEG_NRK, E_PEG, C_PGUN_AKTIF  as C_AKTIF FROM   trwebpgun T  WHERE   T.I_PGUN = ?   AND  T.I_SANDI = ?     ";
            final String sql = "SELECT A.I_PEG_NRK AS I_PGUN,crypto16.DECRYPT(A.I_SANDI) AS I_SANDI,A.C_AKTIF ,'ROLE_'||A.C_PGUN_GROUP AS AUTHORITY, C_PGUN_OTOR FROM TRBKUSPENGGUNA A WHERE A.I_PEG_NRK = ? AND A.I_SANDI = crypto16.ENCRYPT(?) ";
            //final String sql = "SELECT T.I_ID, T.C_PGUN_TAPD, 'ROLE_'||T.C_PGUN_TAPD AS AUTHORITY, T.I_PGUN, T.I_SANDI ,T.C_PGUN_AKTIF as C_AKTIF FROM TRWEBPGUN T  WHERE   T.I_PGUN = ? AND  T.I_SANDI = ? AND T.C_PGUN_AKTIF  = 1 ";
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
            //return jdbcTemplate.queryForObject(sql, new Object[]{username, pass}, new PenggunaMapper());
            //return jdbcTemplate.query(sql, new Object[]{username, pass}, new PenggunaMapper());
            /* idns , 07-11-2017 [*/
            final Pengguna pengguna = jdbcTemplate.queryForObject(sql, new Object[]{username, pass}, new PenggunaMapper());
            Menu menu = null;
            Integer idNumber = 0;
            ArrayList<Menu> menuPgnList = new ArrayList();
            ArrayList<Menu> menuList = null;

            //final String sqlMenu = "SELECT C.I_PEG_NRK AS I_PGUN,C.I_SANDI,C.C_AKTIF ,'ROLE_'||C.C_PGUN_GROUP AS AUTHORITY,A.I_ID,A.I_MODUL_NO,A.N_MODUL,A.N_MODUL_LINK,A.E_MODUL,B.I_IDPENGGUNA,C.C_PGUN_GROUP,C.N_PEG FROM  TRBKUSMODUL A LEFT JOIN TRBKUSPENGGUNAMODUL B ON A.I_ID = B.I_IDMODUL LEFT JOIN  TRBKUSPENGGUNA C ON B.I_IDPENGGUNA = C.I_ID WHERE C.I_PEG_NRK = ? AND C.I_SANDI = crypto16.ENCRYPT(?) AND A.I_IDINDUK = 1 AND A.C_DETAIL = '0'  AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' ";
            final String sqlMenu = "SELECT DISTINCT D.I_ID, D.N_MODUL, D.N_MODUL_LINK  FROM  TRBKUSMODUL A LEFT JOIN TRBKUSPENGGUNAMODUL B ON A.I_ID = B.I_IDMODUL LEFT JOIN  TRBKUSPENGGUNA C ON B.I_IDPENGGUNA = C.I_ID ,TRBKUSMODUL D WHERE C.I_PEG_NRK = ? AND C.I_SANDI = crypto16.ENCRYPT(?) AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' AND D.I_ID = A.I_IDINDUK AND D.C_DETAIL = '0'  AND D.I_IDINDUK = 1 ORDER BY 1 ASC";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlMenu, new Object[]{username, pass});
            for (Map row : rows) {
                idNumber = ((BigDecimal) row.get("I_ID")).intValue();
                //idNumber = 6;   // fixme
                menu = new Menu();
                menu.setIdNumber(idNumber);
                menu.setNama((String) row.get("N_MODUL"));
                menu.setLink((String) row.get("N_MODUL_LINK"));
                final String sqlMenu1 = "SELECT COUNT(1) FROM TRBKUSMODUL A WHERE A.I_IDINDUK = ?  AND A.C_AKTIF = '1' ";
                int jum = jdbcTemplate.queryForInt(sqlMenu1, new Object[]{idNumber});
                if (jum > 0) {
                    menu.setKodeAdaSubMenu("Y");
                    menuList = loadMenuByNoInduk(username, idNumber);
                    menu.setMenu(menuList);
                } else if (jum == 0) {
                    menu.setKodeAdaSubMenu("T");
                }
                menu.setMenu(menuList);
                menuPgnList.add(menu);
            }
            pengguna.setMenu(menuPgnList);
            /* idns , 07-11-2017 */
            return pengguna;
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }

    }

    private static DataSource ambilDataSource() {
        /* org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
         try {
         ds.setDriverClassName(PropertiesAplikasi.DRIVER);
         ds.setUsername(PropertiesAplikasi.USERNAME);
         ds.setPassword(PropertiesAplikasi.PASSWORD);
         ds.setInitialSize(1);
         ds.setMaxActive(4);
         ds.setMaxIdle(2);
         ds.setMinIdle(1);
         ds.setMinEvictableIdleTimeMillis(1800000);
         ds.setTimeBetweenEvictionRunsMillis(1800000);
         ds.setNumTestsPerEvictionRun(2);
         ds.setTestOnBorrow(true);
         ds.setTestWhileIdle(true);
         ds.setTestOnReturn(true);
         ds.setValidationQuery("SELECT 1");
         ds.setUrl(PropertiesAplikasi.URL);

         } catch (Exception e) {
         e.printStackTrace();
         }
         return ds;*/
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/spdDB");
        return dataSource;
    }

    @Override
    public Pengguna loadPenggunaByUsername(String usernam) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
        // idns , 07-11-2017 tutup, ganti dgn yg dibawah: final String sql = "SELECT  T.I_ID,  T.I_PGUN, T.I_SANDI, T.I_IP_ADDRESS, T.N_EMAIL, T.C_PGUN_AKTIF, T.C_PGUN_TAPD , 'ROLE_'||T.C_PGUN_TAPD AS  AUTHORITY   , "
        //        + "C_WIL  FROM TRWEBPGUN T, TRSEKOLAH S  WHERE  T.I_IDSEKOLAH = S.I_ID AND  T.I_PGUN = ?";
        final String sql = "SELECT  T.I_ID, T.I_PEG_NRK AS I_PGUN, crypto16.DECRYPT(T.I_SANDI) AS I_SANDI, T.I_IP_ADDRESS, T.N_EMAIL, T.C_AKTIF AS C_PGUN_AKTIF, T.C_PGUN_OTOR AS C_PGUN_TAPD , 'ROLE_'||T.C_PGUN_OTOR AS  AUTHORITY   , "
                + "C_WIL  FROM TRBKUSPENGGUNA T, TRSEKOLAH S  WHERE  T.I_IDSEKOLAH = S.I_ID AND  T.I_PEG_NRK = ?";

        final Pengguna pengguna = jdbcTemplate.queryForObject(sql, new RowMapper<Pengguna>() {
            @Override
            public Pengguna mapRow(ResultSet rs, int i) throws SQLException {
                final Pengguna pengguna = new Pengguna();
                pengguna.setIdPengguna(rs.getInt("I_ID"));
                pengguna.setPassPengguna(rs.getString("I_SANDI"));
                pengguna.setNamaPengguna(rs.getString("I_PGUN"));
                //pengguna.setNip(rs.getString("I_NIP_PEG"));
                //pengguna.setNrk(rs.getString("I_NRK_PEG"));
                pengguna.setIpAddress(rs.getString("I_IP_ADDRESS"));
                pengguna.setKodeProses(rs.getString("C_WIL"));
                pengguna.setKodeGrup(rs.getString("C_PGUN_TAPD"));
                return pengguna;
            }
        }, usernam);
        /* idns , 07-11-2017 [*/
        Menu menu = null;
        Integer idNumber = 0;
        ArrayList<Menu> menuPgnList = new ArrayList();
        ArrayList<Menu> menuList = null;

        //final String sqlMenu = "SELECT C.I_PEG_NRK AS I_PGUN,C.I_SANDI,C.C_AKTIF ,'ROLE_'||C.C_PGUN_GROUP AS AUTHORITY,A.I_ID,A.I_MODUL_NO,A.N_MODUL,A.N_MODUL_LINK,A.E_MODUL,B.I_IDPENGGUNA,C.C_PGUN_GROUP,C.N_PEG FROM  TRBKUSMODUL A LEFT JOIN TRBKUSPENGGUNAMODUL B ON A.I_ID = B.I_IDMODUL LEFT JOIN  TRBKUSPENGGUNA C ON B.I_IDPENGGUNA = C.I_ID WHERE C.I_PEG_NRK = ?  AND A.I_IDINDUK = 1 AND A.C_DETAIL = '0' AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' ";
        final String sqlMenu = "SELECT DISTINCT D.I_ID, D.N_MODUL, D.N_MODUL_LINK  FROM  TRBKUSMODUL A LEFT JOIN TRBKUSPENGGUNAMODUL B ON A.I_ID = B.I_IDMODUL LEFT JOIN  TRBKUSPENGGUNA C ON B.I_IDPENGGUNA = C.I_ID ,TRBKUSMODUL D WHERE C.I_PEG_NRK = ? AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' AND D.I_ID = A.I_IDINDUK AND D.C_DETAIL = '0'  AND D.I_IDINDUK = 1 ORDER BY 1 ASC";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlMenu, new Object[]{usernam});
        for (Map row : rows) {
            idNumber = ((BigDecimal) row.get("I_ID")).intValue();
            //idNumber = 6;   // fixme
            menu = new Menu();
            menu.setIdNumber(idNumber);
            menu.setNama((String) row.get("N_MODUL"));
            menu.setLink((String) row.get("N_MODUL_LINK"));
            final String sqlMenu1 = "SELECT COUNT(1) FROM TRBKUSMODUL A WHERE A.I_IDINDUK = ?  AND A.C_AKTIF = '1' ";
            int jum = jdbcTemplate.queryForInt(sqlMenu1, new Object[]{idNumber});
            if (jum > 0) {
                menu.setKodeAdaSubMenu("Y");
                menuList = loadMenuByNoInduk(usernam, idNumber);
                menu.setMenu(menuList);
            } else if (jum == 0) {
                menu.setKodeAdaSubMenu("T");
            }
            menu.setMenu(menuList);
            menuPgnList.add(menu);
        }
        pengguna.setMenu(menuPgnList);
        /* idns , 07-11-2017 */
        // idns , 07-11-2017 , tutup, ganti dgn yg dibawah: final String sqlsekolah = "SELECT  S.I_ID, S.I_NPSN, S.I_IDSKPD, S.N_SEKOLAH, S.N_SEKOLAH_PENDEK,"
        //        + "S.C_JENJANG, S.I_REK_BANKBOS, S.I_REK_BANKBOP  FROM TRWEBPGUN T LEFT JOIN TRSEKOLAH S ON T.I_IDSEKOLAH = S.I_ID WHERE T.I_ID = ? ";
        final String sqlListSekolah = "SELECT  S.I_ID, S.I_NPSN, S.I_IDSKPD, S.N_SEKOLAH, S.N_SEKOLAH_PENDEK,"
                + "S.C_JENJANG,S.I_REK_BANKBOS, S.I_REK_BANKBOP FROM TRBKUSPENGGUNA T LEFT JOIN TRSEKOLAH S ON T.I_IDSEKOLAH = S.I_ID WHERE T.I_ID = ? ";
        final List<Sekolah> listSekolah = jdbcTemplate.query(sqlListSekolah, new RowMapper<Sekolah>() {
            @Override
            public Sekolah mapRow(ResultSet rs, int i) throws SQLException {
                final Sekolah sekolah = new Sekolah();
                sekolah.setIdSekolah(rs.getInt("I_ID"));
                sekolah.setNpsn(rs.getString("I_NPSN"));
                sekolah.setIdSkpd(rs.getInt("I_IDSKPD"));
                sekolah.setNamaSekolah(rs.getString("N_SEKOLAH"));
                sekolah.setNamaSekolahPendek(rs.getString("N_SEKOLAH_PENDEK"));
                sekolah.setKodeJenjang(rs.getString("C_JENJANG"));
                return sekolah;
            }
        }, pengguna.getIdPengguna());
//======================================== TEST TEST TEST TEST TEST TEST
//        final String sqlSekolah = "SELECT  S.I_ID, S.I_NPSN, S.I_IDSKPD, S.N_SEKOLAH, S.N_SEKOLAH_PENDEK,"
//                + "S.C_JENJANG FROM TRBKUSPENGGUNA T LEFT JOIN TRSEKOLAH S ON T.I_IDSEKOLAH = S.I_ID WHERE T.I_PEG_NRK = ? ";
//        final Sekolah sekolah = jdbcTemplate.queryForObject(sqlSekolah, new RowMapper<Sekolah>() {
//            @Override
//            public Sekolah mapRow(ResultSet rs, int i) throws SQLException {
//                final Sekolah sekolah = new Sekolah();
//                sekolah.setIdSekolah(rs.getInt("I_ID"));
//                sekolah.setNpsn(rs.getString("I_NPSN"));
//                sekolah.setIdSkpd(rs.getInt("I_IDSKPD"));
//                sekolah.setNamaSekolah(rs.getString("N_SEKOLAH"));
//                sekolah.setNamaSekolahPendek(rs.getString("N_SEKOLAH_PENDEK"));
//                sekolah.setKodeJenjang(rs.getString("C_JENJANG"));
//                return sekolah;
//            }
//        }, usernam);
//        pengguna.setSekolah(sekolah);
        pengguna.setListSekolah(listSekolah);
        log.debug("*** LIST SKPD USER IMPL ================ " + listSekolah.toString() + " " + listSekolah.size());

        return pengguna;

    }
    /*
     public Pengguna loadPenggunaByUsername(String usernam) {
     final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
     final String sql = "SELECT  T.I_ID,  T.I_PGUN, T.I_SANDI, T.I_IP_ADDRESS, T.N_EMAIL, T.C_PGUN_AKTIF, T.C_PGUN_TAPD , 'ROLE_'||T.C_PGUN_TAPD AS  AUTHORITY   , "
     + "C_WIL  FROM TRWEBPGUN T, TRSEKOLAH S  WHERE  T.I_IDSEKOLAH = S.I_ID AND  T.I_PGUN = ?";
     final Pengguna pengguna = jdbcTemplate.queryForObject(sql, new RowMapper<Pengguna>() {
     @Override
     public Pengguna mapRow(ResultSet rs, int i) throws SQLException {
     final Pengguna pengguna = new Pengguna();
     pengguna.setIdPengguna(rs.getInt("I_ID"));
     pengguna.setNamaPengguna(rs.getString("I_PGUN"));
     //pengguna.setNip(rs.getString("I_NIP_PEG"));
     //pengguna.setNrk(rs.getString("I_NRK_PEG"));
     pengguna.setIpAddress(rs.getString("I_IP_ADDRESS"));
     pengguna.setKodeProses(rs.getString("C_WIL"));
     pengguna.setKodeGrup(rs.getString("C_PGUN_TAPD"));
     return pengguna;
     }

     }, SipkdHelpers.splitString(usernam, "|", 0));
     final String sqlsekolah = "SELECT  S.I_ID, S.I_NPSN, S.I_IDSKPD, S.N_SEKOLAH, S.N_SEKOLAH_PENDEK,"
     + "S.C_JENJANG FROM TRWEBPGUN T LEFT JOIN TRSEKOLAH S ON T.I_IDSEKOLAH = S.I_ID WHERE T.I_PGUN = ? ";
     final List<Sekolah> listSekolah = jdbcTemplate.query(sqlsekolah, new RowMapper<Sekolah>() {
     @Override
     public Sekolah mapRow(ResultSet rs, int i) throws SQLException {
     final Sekolah sekolah = new Sekolah();
     sekolah.setIdSekolah(rs.getInt("I_ID"));
     sekolah.setNpsn(rs.getInt("I_NPSN"));
     sekolah.setIdSkpd(rs.getInt("I_IDSKPD"));
     sekolah.setNamaSekolah(rs.getString("N_SEKOLAH"));
     sekolah.setNamaSekolahPendek(rs.getString("N_SEKOLAH_PENDEK"));
     sekolah.setKodeJenjang(rs.getString("C_JENJANG"));
     return sekolah;

     }
     }, pengguna.getIdPengguna());
     pengguna.setSekolah(listSekolah);
     //log.debug("*** LIST SEKOLAH *** "+listSekolah.toString());
     return pengguna;

     }*/

    private class PenggunaMapper implements ParameterizedRowMapper<Pengguna> {

        @Override
        public Pengguna mapRow(ResultSet rs, int arg1) throws SQLException {
            final boolean aktif = rs.getInt("C_AKTIF") > 0;
            final Pengguna pengguna = new Pengguna();
            /* idns , 07-11-2017 [*/
            /* orig ; */
            pengguna.setNamaPengguna(rs.getString("I_PGUN"));
            pengguna.setPassPengguna(rs.getString("I_SANDI"));
            pengguna.setKodeGrup(rs.getString("AUTHORITY"));
            pengguna.setIsAktif(aktif);
            //
            /*
             boolean aktif = false;
             String iPgun = "",iSandi = "", noModule = "", namaModule = "", namaModuleLink = "";
             Menu menu = null;
             MenuLi menuLi = null;
             ArrayList<Menu> menuList = new ArrayList();
             ArrayList<MenuLi> menuLiList = new ArrayList();
             while(rs.next()){
             iPgun = rs.getString("I_PGUN");
             iSandi = rs.getString("I_SANDI");
             aktif = rs.getInt("C_AKTIF") > 0;

             noModule = rs.getString("I_MODUL_NO");
             namaModule = rs.getString("N_MODUL");
             namaModuleLink = rs.getString("N_MODUL_LINK");
             if(!noModule.contains(".")){
             if(menu != null){
             menu.setMenuLi(menuLiList);
             menuList.add(menu);
             menu = null;
             }
             if(menu == null){
             menu = new Menu();
             menuLiList = new ArrayList();
             menu.setJudul(namaModule);
             //menu.setUlName(namaModuleLink);
             }
             } else if(noModule.contains(".")){
             menuLi = new MenuLi();
             menuLi.setNama(namaModule);
             menuLi.setLink(namaModuleLink);
             menuLiList.add(menuLi);
             }
             }
             */

            ///////////////
            //pengguna.setNamaPengguna(iPgun);
            //pengguna.setPassPengguna(iSandi);
            pengguna.setKodeGrup(rs.getString("AUTHORITY"));
            //pengguna.setIsAktif(aktif);
            //pengguna.setMenu(menuList);
            /*] idns , 07-11-2017 */
            return pengguna;
            /* SimpleGrantedAuthority sga = new SimpleGrantedAuthority(rs.getString("AUTHORITY"));
             final List<SimpleGrantedAuthority> listSimpleGrantedAuthority = new ArrayList<SimpleGrantedAuthority>();
             return new User(rs.getString("I_PGUN"), rs.getString("I_SANDI"), aktif, true, true, true, listSimpleGrantedAuthority);*/
        }
    }

    /* idns , 09-11-2017 [*/
    private ArrayList<Menu> loadMenuByNoInduk(final String username, final Integer noInduk) {
        try {
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(ambilDataSource());
            Menu menu = null;
            ArrayList<Menu> menuList = new ArrayList();
            Integer idNumber;
            //final String sqlMenu = "SELECT A.I_ID,A.I_MODUL_NO,A.N_MODUL,A.N_MODUL_LINK,A.E_MODUL FROM  TRBKUSMODUL A WHERE A.I_IDINDUK = ?  AND A.C_AKTIF = '1' ";
            final String sqlMenu = "SELECT C.I_ID,C.N_MODUL,C.N_MODUL_LINK FROM TRBKUSPENGGUNA A,TRBKUSPENGGUNAMODUL B, TRBKUSMODUL C WHERE A.I_PEG_NRK = ? AND B.I_IDPENGGUNA = A.I_ID AND C.I_ID = B.I_IDMODUL AND C.I_IDINDUK = ? AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' UNION SELECT DISTINCT D.I_ID,D.N_MODUL,D.N_MODUL_LINK FROM TRBKUSPENGGUNA A,TRBKUSPENGGUNAMODUL B, TRBKUSMODUL C, TRBKUSMODUL D WHERE A.I_PEG_NRK = ? AND B.I_IDPENGGUNA = A.I_ID AND C.I_ID = B.I_IDMODUL AND D.I_ID = C.I_IDINDUK AND D.I_IDINDUK = ? AND A.C_AKTIF = '1' AND B.C_AKTIF = '1' AND C.C_AKTIF = '1' AND D.C_AKTIF = '1' ORDER BY 1 ASC";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlMenu, new Object[]{username, noInduk, username, noInduk});
            for (Map row : rows) {
                menu = new Menu();
                idNumber = ((BigDecimal) row.get("I_ID")).intValue();
                menu.setIdNumber(idNumber);
                menu.setNama((String) row.get("N_MODUL"));
                menu.setLink((String) row.get("N_MODUL_LINK"));
                final String sqlMenu1 = "SELECT COUNT(1) FROM TRBKUSMODUL A WHERE A.I_IDINDUK = ?  AND A.C_AKTIF = '1' ";
                int jum = jdbcTemplate.queryForInt(sqlMenu1, new Object[]{idNumber});
                if (jum > 0) {
                    menu.setKodeAdaSubMenu("Y");
                    ArrayList<Menu> menuList1 = loadMenuByNoInduk(username, idNumber);
                    menu.setMenu(menuList1);
                } else if (jum == 0) {
                    menu.setKodeAdaSubMenu("T");
                }
                menuList.add(menu);
            }
            return menuList;
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }
    }

    /*] idns , 09-11-2017 */
}
