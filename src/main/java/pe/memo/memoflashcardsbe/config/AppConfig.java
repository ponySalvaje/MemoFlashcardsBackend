package pe.memo.memoflashcardsbe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan({"pe.memo.memoflashcardsbe.*"})
@EntityScan({"pe.memo.memoflashcardsbe.*"})
@EnableJpaRepositories({"pe.memo.memoflashcardsbe.*"})
@Configuration
@PropertySource("application-${APP_ENVIRONMENT}.properties")
@Slf4j
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
