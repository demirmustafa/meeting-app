package io.github.demirmustafa.meetingapp.configuration;

import io.github.demirmustafa.meetingapp.api.filter.AuditLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfiguration {

    @Bean
    public FilterRegistrationBean<AuditLoggingFilter> auditLoggingFilter() {
        FilterRegistrationBean<AuditLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuditLoggingFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
