package mingle.musemingle

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig : AbstractHttpConfigurer<SecurityConfig, HttpSecurity>() {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // CSRF 보안 비활성화 (필요에 따라)
            .authorizeHttpRequests {
                it.anyRequest().permitAll() // 모든 요청에 대해 인증 생략
            }
            .httpBasic { it.disable() }  // 기본 HTTP 인증 비활성화

        return http.build()
    }
}
