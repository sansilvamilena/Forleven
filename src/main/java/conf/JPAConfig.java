package conf;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author milena
 */
@EnableTransactionManagement
public class JPAConfig 
{
  @Value("${database.url}")
  private String url;
  @Value("${database.username}")
  private String username;
  @Value("${database.password}")
  private String password;
  @Value("${database.driver}")
  private String driver;
  
  /*
   * Datasource de conexão da JPA com o Banco de Dados
   */
  @Bean
  public DataSource dataSource()
  {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(driver);
    return dataSource;
  }
  
  /*
   * Metodo para criar a fabrica da JPA com o caminho dos pacotes do banco
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean()
  {
    LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    managerFactoryBean.setDataSource(dataSource());
    managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    managerFactoryBean.setJpaProperties(myJpaConfig());
    managerFactoryBean.setPackagesToScan(new String[]{"model"});
    return managerFactoryBean;
  }
  
  /*
  * Metodo de propriedades da JPA e Hibernate
  */
  private Properties myJpaConfig()
  {
    Properties properties = new Properties();
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    return properties;
  }
  
  /*
  * Gerenciador de Transações
  */
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
  {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(emf);
    return jpaTransactionManager;
  }
}
