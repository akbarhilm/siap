package ebkus.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ebkus.util.DoublePropertyEditor;
import ebkus.util.SqlDatePropertyEditor;

/**
 * Equivalent to &lt;mvc:annotation:driven/&gt; .
 *
 * @author Hari Saptoadi
 *
 */
//
@Configuration
@EnableTransactionManagement
@EnableWebMvc
@MapperScan("ebkus.entity")
@ComponentScan(basePackages = {"ebkus.ui.action", "ebkus.services"})
@Import({SecurityConfig.class})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory
            .getLogger(WebMvcConfig.class);

    /**
     * @return the view resolver
     */
    @Bean
    public ViewResolver viewResolver() {
        logger.debug("setting up view resolver");

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver lr = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
        lr.setDefaultLocale(new Locale("in"));
        return lr;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/").addResourceLocations(
                "/static/**").setCachePeriod(Integer.MIN_VALUE);
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        //logger.debug("configureDefaultServletHandling");
        configurer.enable();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
        /* mappings.put("org.springframework.dao.DataAccessException",
         "p500");
         mappings.put("org.springframework.transaction.TransactionException",
         "p500");*/
        mappings.put("org.springframework.security.access.AccessDeniedException",
                "deny");
        b.setExceptionMappings(mappings);
        return b;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        /*
         org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
         try {

         ds.setDriverClassName(PropertiesAplikasi.DRIVER);
         ds.setUsername(PropertiesAplikasi.USERNAME);
         ds.setPassword(PropertiesAplikasi.PASSWORD);
         ds.setInitialSize(1);
         ds.setMaxActive(5);
         ds.setMaxIdle(2);
         ds.setMinIdle(1);
         ds.setMinEvictableIdleTimeMillis(18000);
         ds.setTimeBetweenEvictionRunsMillis(18000);
         ds.setNumTestsPerEvictionRun(2);
         ds.setTestOnBorrow(true);
         ds.setTestWhileIdle(true);
         ds.setTestOnReturn(true);
         ds.setRemoveAbandoned(true);
         ds.setRemoveAbandonedTimeout(45);
         ds.setValidationInterval(19000);
         ds.setValidationQuery(PropertiesAplikasi.VALIDATION_QUERY);
         ds.setUrl(PropertiesAplikasi.URL);

         } catch (Exception e) {
         e.printStackTrace();
         }
         return ds;*/
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/spdDB");
        return dataSource;
    }

    @Bean
    public JdbcOperations tpl() {
        return new JdbcTemplate(dataSource());

    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public CustomEditorConfigurer customEditors() {
        final CustomEditorConfigurer cec = new CustomEditorConfigurer();
        final Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("java.sql.Date", new SqlDatePropertyEditor());
        map.put("java.lang.Double", new DoublePropertyEditor());
        cec.setCustomEditors(map);
        return cec;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());
        return sessionFactory.getObject();
    }

    /*@Bean
     public ViewResolver setupViewResolver() {
     ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
     resourceBundleViewResolver.setBasenames(new String[]{"resourceviews"});
     return resourceBundleViewResolver;
     }*/
}
