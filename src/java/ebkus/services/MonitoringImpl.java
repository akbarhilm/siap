package ebkus.services;

import java.math.BigDecimal;
import static java.rmi.server.LogStream.log;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ebkus.entity.MonitoringMapper;
import ebkus.model.Monitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Sakadaek
 */
@Transactional(readOnly = true)
@Service("laporanServicesServices")
public class MonitoringImpl implements MonitoringServices {

    @Autowired
    private MonitoringMapper monitoringMapper;

  
    @Override
    public List<Map> getnilaiparam(Map param) {
        return monitoringMapper.getnilaiparam(param);
    }
  
    
}
