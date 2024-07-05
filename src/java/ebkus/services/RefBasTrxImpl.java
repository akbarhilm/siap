package ebkus.services;

import ebkus.entity.RefBasTrxMapper;
import ebkus.model.RefBasTrx;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("refBasTrxServices")
@Transactional(readOnly=true)
public class RefBasTrxImpl implements RefBasTrxServices{

  @Autowired
  private RefBasTrxMapper refBasTrxMapper;

  @Override
  public List<RefBasTrx> getListBasTrx(Map<String, Object> map)
  {
    return refBasTrxMapper.getListBasTrx(map);
  }
  
  @Override
  public Integer getBanyakListBasTrx(Map<String, Object> map)
  {
    return refBasTrxMapper.getBanyakListBasTrx(map);
  }
}