package ebkus.services;

import ebkus.entity.ModulMapper;
import ebkus.model.Modul;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ebkus.model.GenerateId;
import ebkus.entity.GenerateIdMapper;

/**
 *
 * @author idns
 */
@Service("modulAdministrationServices")
@Transactional(readOnly = true)
public class ModulAdministrationImpl implements ModulAdministrationServices {

    @Autowired
    private ModulMapper modulMapper;

    @Autowired
    private GenerateIdMapper generateIdMapper;

    @Override
    public List<Modul> getListModul(Map<String, Object> map) {
        List<Modul> list = modulMapper.getListModul(map);
        if(list != null){
            for(int i = 0; i < list.size() ; i++){
                Modul modul = list.get(i);
                if(modul != null){
                    if(modul.getIdInduk() == 1 || modul.getNamaModulLink() == null || "".equals(modul.getNamaModulLink().trim())){   // induk 1 atau link null 
                        Integer jumSubModul = modulMapper.getBanyakListModulByIdInduk(modul.getId());
                        modul.setJumlahSubModul(jumSubModul);
                        list.set(i, modul);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public Integer getBanyakListModul(Map<String, Object> map) {
        return modulMapper.getBanyakListModul(map);
    }
    
    @Override
    @Transactional(readOnly = false)
    public Modul insertModul(Modul modul) {
        try{
            GenerateId idgen = new GenerateId(); 
            idgen.setNamaTabel("TRBKUSMODUL"); // nama tabel menyesuaikan
            generateIdMapper.insertGetId(idgen);
            Integer id = idgen.getId();        
            modul.setId(id);        
            modulMapper.insertModul(modul);            
        } catch(java.lang.Exception e){
           e.printStackTrace();
           return null;
        }            
        return modul;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void updateModul(Modul modul) {
        modulMapper.updateModul(modul);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteModul(Integer id) {
        modulMapper.deleteModul(id);

    }

    @Override
    public Modul getModulById(Integer id) {
        return modulMapper.getModulById(id);
    }

}
