package ebkus.services;

import ebkus.entity.FormBkuFormatMapper;
import ebkus.model.BukuKasUmum;
import ebkus.model.FormBkuFormat;
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
@Service("formBkuFormatServices")
public class FormBkuFormatImpl implements FormBkuFormatServices {

    private static final Logger log = LoggerFactory.getLogger(FormBkuFormatImpl.class);
    @Autowired
    private FormBkuFormatMapper cetakMapper;

    @Override
    public List<Map> getnilaiparam(Map param) {
        return cetakMapper.getnilaiparam(param);
    }

    @Override
    public List<FormBkuFormat> getSaldoAwalTunai(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalTunai(param);
    }

    @Override
    public List<FormBkuFormat> getSaldoAwalBank(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalBank(param);
    }

    @Override
    public List<FormBkuFormat> getSaldoAwalPanjar(Map<String, Object> param) {
        return cetakMapper.getSaldoAwalPanjar(param);
    }

    @Override
    public List<FormBkuFormat> getAkunBelanja(Map<String, Object> param) {
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
    public List<FormBkuFormat> getSaldoAwalPajak(Map<String, Object> param) {
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

    @Override
    public List<FormBkuFormat> getIdSkpd(final Map<String, Object> param) {
        return cetakMapper.getIdSkpd(param);
    }

    @Override
    public List<FormBkuFormat> getKecamatan(Map<String, Object> param) {
        return cetakMapper.getKecamatan(param);
    }

    @Override
    public List<FormBkuFormat> getSisdik(final Map<String, Object> param) {
        return cetakMapper.getSisdik(param);
    }

    @Override
    public void updateSisdik(Map param) {
        cetakMapper.updateSisdik(param);
    }
}
