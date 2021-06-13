package com.micah.demo.config;

import com.micah.demo.commons.LoggedInUsernameAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() { return new LoggedInUsernameAuditorAware(); }
}
