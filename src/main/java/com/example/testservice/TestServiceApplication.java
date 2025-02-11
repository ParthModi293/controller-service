package com.example.testservice;

import jakarta.persistence.SharedCacheMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"org.communication", "com.example.testservice","org.common"})
@EnableJpaRepositories({"org.communication", "com.example.testservice"})
public class TestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestServiceApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        em.setPackagesToScan("org.communication", "com.example.testservice","org.common");
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        return em;
    }

}
