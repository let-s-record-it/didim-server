package com.didim.dbmain.config

import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.didim.dbmain"])
@EnableJpaRepositories(basePackages = ["com.didim.dbmain"])
@EnableJpaAuditing
internal class JpaConfig