package ebkus.entity;

import ebkus.model.RefBasTrx;
import java.util.List;
import java.util.Map;

public abstract interface RefBasTrxMapper{
  public abstract List<RefBasTrx> getListBasTrx(Map<String, Object> paramMap);
  
  public abstract Integer getBanyakListBasTrx(Map<String, Object> paramMap);
}