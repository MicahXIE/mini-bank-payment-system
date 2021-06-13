package com.micah.demo.commons;

import com.micah.demo.auth.UserContext;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class LoggedInUsernameAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.of(UserContext.getCurrentUserName());
        } catch (Exception ignored) {
            return Optional.of("System");
        }
    }
}
