package leticia.edu.login_backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> {
            csrf.disable(); // desabilito a "tela de login"
        }).authorizeHttpRequests((auth) -> {
            auth.requestMatchers(new AntPathRequestMatcher("/api", "POST")).permitAll();
            auth.requestMatchers(new AntPathRequestMatcher("/login", "POST")).permitAll();
            auth.requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll();
            auth.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "/v3/api-docs")).permitAll();
        }).addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
