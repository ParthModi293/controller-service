package com.clapcle.controller;

import jakarta.persistence.SharedCacheMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@ComponentScan({"com.clapcle.communication", "com.clapcle.controller", "com.clapcle.core"})
@EnableJpaRepositories({"com.clapcle.communication.repository"})
@EntityScan("com.clapcle.communication.entity")
public class ControllerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllerServiceApplication.class, args);
    }

}
