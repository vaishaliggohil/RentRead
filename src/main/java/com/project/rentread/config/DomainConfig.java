package com.project.rentread.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.project.rentread.entities")
@EnableJpaRepositories("com.project.rentread.repositories")
@EnableTransactionManagement
public class DomainConfig {
}
