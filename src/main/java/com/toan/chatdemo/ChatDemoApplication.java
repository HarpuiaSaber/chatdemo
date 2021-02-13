package com.toan.chatdemo;

import com.toan.chatdemo.entities.User;
import com.toan.chatdemo.services.impls.AuditorAwareImpl;
import com.toan.chatdemo.utils.DateTimeUtils;
import org.modelmapper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.toan.chatdemo.daos")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ChatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatDemoApplication.class, args);
    }

    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelmapper = new ModelMapper();

        Provider<Date> localDateProvider = new AbstractProvider<Date>() {
            @Override
            public Date get() {
                return new Date();
            }
        };

        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                return DateTimeUtils.parseDate(source, DateTimeUtils.DD_MM_YYYY);
            }
        };

        modelmapper.createTypeMap(String.class, Date.class);
        modelmapper.addConverter(toStringDate);
        modelmapper.getTypeMap(String.class, Date.class).setProvider(localDateProvider);
        return modelmapper;
    }
}
