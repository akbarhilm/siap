package ebkus.services;

import ebkus.entity.FormBkuMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.FormBku;
import ebkus.model.Kegiatan;
import ebkus.model.Setor;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anita
 */
@Transactional(readOnly = true)
@Service("formBkuServices")
public class FormBkuImpl implements FormBkuServices {

    private static final Logger log = LoggerFactory.getLogger(FormBkuImpl.class);
    @Autowired
    private FormBkuMapper cetakMapper;

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }

    @Override
    public List<FormBku> getSaldoAwalTunai(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalTunai(param);
    }

    @Override
    public List<FormBku> getSaldoAwalBank(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalBank(param);
    }

    @Override
    public List<FormBku> getSaldoAwalPanjar(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPanjar(param);
    }

    @Override
    public List<FormBku> getAkunBelanja(Map<String, Object> param) {
        return cetakMapper.getAkunBelanja(param);
    }

    @Override
    public List<BukuKasUmum> getWilayah(final Map<String, Object> param) {
        return cetakMapper.getWilayah(param);
    }

    @Override
    public List<BukuKasUmum> getTriwulan(final Map<String, Object> param) {
        return cetakMapper.getTriwulan(param);
    }

    @Override
    public List<FormBku> getSaldoAwalPajak(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPajak(param);
    }

    @Override
    public List<Kegiatan> getKegiatan(Map<String, Object> param) {
        return cetakMapper.getKegiatan(param);
    }
    
    @Override
    public Integer cekRekap(Map param) {
        return cetakMapper.cekRekap(param);
    }

     @Override
    public long getBanyakListCetakSetor(Map<String, Object> param) {
        return cetakMapper.getBanyakListCetakSetor(param);
    }

    @Override
    public List<Setor> getListCetakSetor(Map<String, Object> param) {
        return cetakMapper.getListCetakSetor(param);
    }

}
