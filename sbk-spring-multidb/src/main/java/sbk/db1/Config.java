package sbk.db1;

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
@EnableJpaRepositories
public class Config
{
  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource()
  {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder)
  {
    return builder
        .dataSource(dataSource())
        .packages("sbk.db1")
        //.persistenceUnit("1")
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
  )
  {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
