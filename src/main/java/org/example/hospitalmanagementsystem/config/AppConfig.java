package org.example.hospitalmanagementsystem.config;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//
//        // Настройка стратегии соответствия
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Конвертер для преобразования строки в LocalDateTime
//        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
//            @Override
//            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
//                // Пример парсинга строки в LocalDateTime
//                String source = context.getSource();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Укажите нужный формат
//                return LocalDateTime.parse(source, formatter);
//            }
//        });

        return modelMapper;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
