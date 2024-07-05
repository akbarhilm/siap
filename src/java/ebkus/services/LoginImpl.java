package ebkus.services;

import ebkus.entity.LoginMainTemplateMapper;
import ebkus.model.Menu;
import ebkus.model.Modul;
import ebkus.model.Pengguna;
import ebkus.model.Sekolah;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author idns , 07-11-2017
 */
@Service("loginServices")
@Transactional(readOnly = true)
public class LoginImpl implements LoginServices {

    @Autowired
    private LoginMainTemplateMapper loginUserMapper;

    @Override
    public Pengguna loadUserByUsername(final String username, final String pass) {
        try {
            Map param = new java.util.HashMap();
            param.put("iPegNrk", username);
            param.put("passwd", pass);
            Pengguna pengguna = loginUserMapper.loadUserOnlyByUsername(param);
//            pengguna = loginUserMapper.loadUserByUsername(param);
            pengguna.setIsAktif("1".equals(pengguna.getKodeAktif()) ? true : false);
            Menu menu = null;
            Modul modul;
            Integer idNumber = 0;
            ArrayList<Menu> menuPgnList = new ArrayList();
            ArrayList<Menu> menuList = null;
            List<Modul> list = loginUserMapper.getListModulDgnIdIndukSatu(param);
            Integer sisahari = loginUserMapper.getSisaHari(pengguna);
//            UNCOMMENT JIKA INGIN MENGAKTIFKAN 40 HARI MASA AKTIF PASSWORD
//            if (pengguna != null && sisahari > 0) {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    modul = list.get(i);
                    menu = new Menu();
                    idNumber = modul.getId();
                    menu.setIdNumber(idNumber);
                    menu.setNama(modul.getNamaModul());
                    menu.setLink(modul.getNamaModulLink());
                    int jum = loginUserMapper.getBanyakListModulByIdInduk(idNumber);
                    if (jum > 0) {
                        menu.setKodeAdaSubMenu("Y");
                        menuList = getMenuByNoInduk(username, idNumber);
                        menu.setMenu(menuList);
                    } else if (jum == 0) {
                        menu.setKodeAdaSubMenu("T");
                    }
                    menu.setMenu(menuList);
                    menuPgnList.add(menu);
                }
            }
//            }
            pengguna.setMenu(menuPgnList);
            return pengguna;
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }
    }

    @Override
    public Pengguna loadPenggunaByUsername(String username) {
        Pengguna pengguna = loginUserMapper.loadPenggunaByUsername(username);
        pengguna.setIsAktif("1".equals(pengguna.getKodeAktif()) ? true : false);
        Menu menu = null;
        Modul modul;
        Integer idNumber = 0;
        ArrayList<Menu> menuPgnList = new ArrayList();
        ArrayList<Menu> menuList = null;

        List<Modul> list = loginUserMapper.getListModulDgnIdIndukSatuByUsername(username);
        Integer sisahari = loginUserMapper.getSisaHari(pengguna);

//        if (pengguna != null && sisahari > 0) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                modul = list.get(i);
                menu = new Menu();
                idNumber = modul.getId();
                menu.setIdNumber(idNumber);
                menu.setNama(modul.getNamaModul());
                menu.setLink(modul.getNamaModulLink());
                int jum = loginUserMapper.getBanyakListModulByIdInduk(idNumber);
                if (jum > 0) {
                    menu.setKodeAdaSubMenu("Y");
                    menuList = getMenuByNoInduk(username, idNumber);
                    menu.setMenu(menuList);
                } else if (jum == 0) {
                    menu.setKodeAdaSubMenu("T");
                }
                menu.setMenu(menuList);
                menuPgnList.add(menu);
            }
        }
//        }
        pengguna.setMenu(menuPgnList);
        Sekolah sekolah = loginUserMapper.getSekolah(pengguna.getNamaPengguna());
        pengguna.setSekolah(sekolah);
        List<Sekolah> listSekolah = loginUserMapper.getListSekolah(pengguna.getNamaPengguna());
        pengguna.setListSekolah(listSekolah);

        return pengguna;

    }

    private ArrayList<Menu> getMenuByNoInduk(final String username, final Integer noInduk) {
        try {
            Menu menu = null;
            Modul modul;
            ArrayList<Menu> menuList = new ArrayList();
            Integer idNumber;
            Map param = new java.util.HashMap();
            param.put("iPegNrk", username);
            param.put("idInduk", noInduk);
            List<Modul> list = loginUserMapper.getListModulByIdInduk(param);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    modul = list.get(i);
                    menu = new Menu();
                    idNumber = modul.getId();
                    menu.setIdNumber(idNumber);
                    menu.setNama(modul.getNamaModul());
                    menu.setLink(modul.getNamaModulLink());
                    int jum = loginUserMapper.getBanyakListModulByIdInduk(idNumber);
                    if (jum > 0) {
                        menu.setKodeAdaSubMenu("Y");
                        ArrayList<Menu> menuList1 = getMenuByNoInduk(username, idNumber);
                        menu.setMenu(menuList1);
                    } else if (jum == 0) {
                        menu.setKodeAdaSubMenu("T");
                    }
                    menuList.add(menu);
                }
            }
            return menuList;
        } catch (Exception c) {
            c.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateSalahPassword(Pengguna param) {
        loginUserMapper.updateSalahPassword(param);
    }

    @Override
    public void updateStatusLock(Pengguna param) {
        loginUserMapper.updateStatusLock(param);
    }

    @Override
    public Integer getSisaHari(Pengguna param) {
        return loginUserMapper.getSisaHari(param);
    }

    @Override
    public void loginSuccess(Pengguna param) {
        loginUserMapper.loginSuccess(param);
    }

    @Override
    public void setLoginStatus(Pengguna param) {
        loginUserMapper.setLoginStatus(param);
    }

    @Override
    public Integer getLoginStatus(Pengguna param) {
        return loginUserMapper.getLoginStatus(param);
    }

    @Override
    public void deleteLoginStatus(Pengguna param) {
        loginUserMapper.deleteLoginStatus(param);
    }
}
