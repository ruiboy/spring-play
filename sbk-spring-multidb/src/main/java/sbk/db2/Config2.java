package sbk.db2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by simonkahl on 26/10/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager2")
public class Config2
{
  @Bean
  @ConfigurationProperties("other.datasource")
  public DataSource dataSource2()
  {
    return DataSourceBuilder.create().build();
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource2())
        .packages("sbk.db2")
        .persistenceUnit("2")
        .build();
  }

  @Bean
  public PlatformTransactionManager transactionManager2(
      @Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory
  )
  {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
