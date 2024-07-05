package ebkus.config;

/**
 *
 * @author sapto
 */
public interface PropertiesAplikasi {

    static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DBNAME = "SIPKD";
    // static final String DBNAME = "SIPKDNEW";
    //static final String URL = "jdbc:oracle:thin:@10.15.3.7:1521:ORD1";
    static final String URL = "jdbc:oracle:thin:@10.1.150.121:1521:ORCL";
    // static final String URL = "jdbc:oracle:thin:@10.1.150.121:1522:ORCL";
    static final String USERNAME = "BKUS";
    static final String PASSWORD = "BKUS";
    /*static final String USERNAME = "sipkdnew";
     static final String PASSWORD = "sipkdnew";*/
    static final String VALIDATION_QUERY = "SELECT 1";
}
