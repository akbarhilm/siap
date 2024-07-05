package ebkus.services;

import ebkus.model.RefBasTrx;
import java.util.List;
import java.util.Map;

public abstract interface RefBasTrxServices
{
  public abstract List<RefBasTrx> getListBasTrx(Map<String, Object> paramMap);
  
  public abstract Integer getBanyakListBasTrx(Map<String, Object> paramMap);
}