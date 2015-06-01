package be.gerard.common.converter.config;

import be.gerard.common.converter.ConversionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

/**
 * ConverterConfig
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Configuration
@ComponentScan("be.gerard.common.converter")
public class ConverterConfig {

    @Bean
    public ConversionService conversionService() {
        ConversionServiceImpl conversionService = new ConversionServiceImpl();
        //conversionService.setConverters();
        return conversionService.getObject();
    }

}
