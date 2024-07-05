package ebkus.services;


import ebkus.entity.GenerateIdMapper;
import ebkus.entity.PenggunaMapper;
import ebkus.entity.GenerateIdMapper;
import ebkus.model.GenerateId;
import ebkus.model.Skpd;
import ebkus.model.User;
import ebkus.model.Pengguna;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zaenab
 */
@Service("userAdministrationServices")
@Transactional(readOnly = true)
public class PenggunaImpl implements PenggunaServices {

    @Autowired
    private PenggunaMapper userMapper;
    
    @Autowired
    private GenerateIdMapper genMapper;
    

    @Override
    public List<User> getListUser(Map<String, Object> map) {
        return userMapper.getListUser(map);
    }
    
    @Override
    public Map getMaxDepag() {
         
        return userMapper.getMaxDepag();
    }

    @Override
    public Integer getBanyakListUser(Map<String, Object> map) {
        return userMapper.getBanyakListUser(map);
    }
    
    @Override
    public User getPenggunaById(Integer id) {
        return userMapper.getPenggunaById(id);
    }

    @Override
    public Map<Integer, String> getlistKodeGroup() {
        final Map<Integer, String> listKodeGroup = new LinkedHashMap<>();
        listKodeGroup.put(1, "BOS");
        listKodeGroup.put(2, "BOP");
        listKodeGroup.put(3, "BOS/BOP");
        
        return listKodeGroup;
    }
    
    @Override
    public Map<Integer, String> getlistKodeOtoritas(Pengguna param) {
        final Map<Integer, String> listKodeGroup = new LinkedHashMap<>();
       
        final String otor = param.getKodeOtoritas();
        if(otor.equals("9")){
             listKodeGroup.put(0, "SUDIN/DINAS");
        listKodeGroup.put(1, "SEKOLAH (PA)");
        listKodeGroup.put(2, "SEKOLAH (PK)");
        listKodeGroup.put(8, "ADMIN");
        }
        if(otor.equals("8")){
             listKodeGroup.put(0, "SUDIN/DINAS");
        listKodeGroup.put(1, "SEKOLAH (PA)");
        listKodeGroup.put(2, "SEKOLAH (PK)");
       
        }
        if(otor.equals("0")){
        listKodeGroup.put(1, "SEKOLAH (PA)");
        listKodeGroup.put(2, "SEKOLAH (PK)");
       
        }
         if(otor.equals("1")){
        listKodeGroup.put(2, "SEKOLAH (PK)");
      
       
        }
       
        
        
        return listKodeGroup;
    }
    @Override
    @Transactional(readOnly = false)
    public void insertUser(User user) {
        GenerateId gen = new GenerateId();
        gen.setNamaTabel("TRBKUSPENGGUNA");
        genMapper.insertGetId(gen);
        user.setIdPengguna(gen.getId());
        
        userMapper.insertUser(user);
       
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateUser(User user) {
        userMapper.updateUser(user);
       
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        userMapper.deleteUser(user);

    }
//
//    @Override
//    public Map<Integer, String> getKodeSp2dProses() {
//        final Map<Integer, String> listKodeGroup = new LinkedHashMap<>();
//        listKodeGroup.put(8, "Pilih Wilayah");
//        listKodeGroup.put(0, "PROVINSI");
//        listKodeGroup.put(1, "PUSAT");
//        listKodeGroup.put(2, "UTARA");
//        listKodeGroup.put(3, "BARAT");
//        listKodeGroup.put(4, "SELATAN");
//        listKodeGroup.put(5, "TIMUR");
//        listKodeGroup.put(6, "P. SERIBU");
//        listKodeGroup.put(7, "BALAIKOTA");
//        
//        return listKodeGroup;//  private static final String[] KODE_SP2D_PROSES = {"PROVINSI ", "PUSAT", "UTARA", "BARAT", "SELATAN", "TIMUR", "P. SERIBU", "BALAIKOTA"};
//    }
//
//    @Override
//    public Map<Integer, String> getlistKodeGroup() {
//        final Map<Integer, String> listKodeGroup = new LinkedHashMap<>();
//        listKodeGroup.put(0, "INITIALISASI");
//        listKodeGroup.put(88, "ADMIN REFERENSI");
//        listKodeGroup.put(99, "ADMIN USERID");
//        listKodeGroup.put(1, "RKA");
//        listKodeGroup.put(2, "DPA");
//        listKodeGroup.put(3, "DPPA");
//        listKodeGroup.put(41, "STS KPKD");
//        listKodeGroup.put(42, "KONSOLIDASI STS");
//        listKodeGroup.put(5, "SPD");
//        listKodeGroup.put(6, "SPP");
//        listKodeGroup.put(7, "SPM");
//        listKodeGroup.put(8, "SP2D");
//        listKodeGroup.put(9, "SPJ");
//        listKodeGroup.put(10, "SKPD PPKD");
//        listKodeGroup.put(11, "LPJ BPKD");
//        listKodeGroup.put(12, "RECON BANK");
//        listKodeGroup.put(20, "MONITORING");
//        listKodeGroup.put(21, "MONITORING BUKAN SKPD");
//        return listKodeGroup;
//    }
//
//    @Override
//    public User getUserById(Integer id) {
//        return userMapper.getUserById(id);
//    }

}
