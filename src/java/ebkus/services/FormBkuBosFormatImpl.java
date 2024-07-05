package ebkus.services;

import ebkus.entity.FormBkuBosFormatMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuBosFormat;
import ebkus.model.Kegiatan;
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
@Service("formBkuBosFormatServices")
public class FormBkuBosFormatImpl implements FormBkuBosFormatServices {

    private static final Logger log = LoggerFactory.getLogger(FormBkuBosFormatImpl.class);
    @Autowired
    private FormBkuBosFormatMapper cetakMapper;

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }

    @Override
    public List<FormBkuBosFormat> getSaldoAwalTunai(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalTunai(param);
    }

    @Override
    public List<FormBkuBosFormat> getSaldoAwalBank(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalBank(param);
    }

    @Override
    public List<FormBkuBosFormat> getSaldoAwalPanjar(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPanjar(param);
    }

    @Override
    public List<FormBkuBosFormat> getAkunBelanja(Map<String, Object> param) {
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
    public List<FormBkuBosFormat> getSaldoAwalPajak(Map<String, Object> param) {
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
    public List<FormBkuBosFormat> getBulanBku(Map<String, Object> param) {
        return cetakMapper.getBulanBku(param);
    }

    @Override
    public void insertTIBKUBulan(FormBkuBosFormat formBkuBosFormat) {
        cetakMapper.insertTIBKUBulan(formBkuBosFormat);
    }
}
