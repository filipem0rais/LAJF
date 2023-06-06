//
// Auteur : Filipe Dias Morais
// Projet : Dev
// Date   : 09.05.2023
// 


package ch.es.pl.quotes.api.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class FilterConfig {
    @Autowired
    private AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean< AuthFilter > filterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/users/*");
        return registrationBean;
    }
}