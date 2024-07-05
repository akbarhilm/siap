package ebkus.services;

import ebkus.entity.FormBkuBosMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuBos;
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
@Service("formBkuBosServices")
public class FormBkuBosImpl implements FormBkuBosServices {

    private static final Logger log = LoggerFactory.getLogger(FormBkuBosImpl.class);
    @Autowired
    private FormBkuBosMapper cetakMapper;

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }

    @Override
    public List<FormBkuBos> getSaldoAwalTunai(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalTunai(param);
    }

    @Override
    public List<FormBkuBos> getSaldoAwalBank(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalBank(param);
    }

    @Override
    public List<FormBkuBos> getSaldoAwalPanjar(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPanjar(param);
    }

    @Override
    public List<FormBkuBos> getAkunBelanja(Map<String, Object> param) {
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
    public List<BukuKasUmum> getBulan(final Map<String, Object> param) {
        return cetakMapper.getBulan(param);
    }
    
    @Override
    public List<BukuKasUmum> getSemester(final Map<String, Object> param) {
        return cetakMapper.getSemester(param);
    }

    @Override
    public List<FormBkuBos> getSaldoAwalPajak(Map<String, Object> param) {
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
