package com.springone.rest;

import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.Module;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.module.SimpleSerializers;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.repository.context.AnnotatedHandlerRepositoryEventListener;
import org.springframework.data.rest.webmvc.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfig.class)
@EnableJpaRepositories
@EnableTransactionManagement
public class ApplicationConfig {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder db = new EmbeddedDatabaseBuilder();
    return db.setType(EmbeddedDatabaseType.HSQL).build();
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.HSQL);
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan(getClass().getPackage().getName());
    factory.setDataSource(dataSource());

    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  public JpaDialect jpaDialect() {
    return new HibernateJpaDialect();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }

  @Bean
  public Module customModule() {
    return new SimpleModule("custom", Version.unknownVersion()) {
      @Override
      public void setupModule(SetupContext context) {
        super.setupModule(context);

        // Set up ISO-8601 date (de)serialization
        context.getDeserializationConfig().withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        SimpleSerializers sers = new SimpleSerializers();
        sers.addSerializer(Timestamp.class, new SerializerBase<Timestamp>(Timestamp.class) {
          @Override
          public void serialize(Timestamp value, JsonGenerator jgen, SerializerProvider provider) throws
                                                                                                  IOException,
                                                                                                  JsonGenerationException {
            jgen.writeString(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(value));
          }
        });

        context.addSerializers(sers);
      }
    };
  }

}
