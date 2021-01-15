package pl.application.cocktailVibe.appConfig;

import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan("pl.application.cocktailVibe")
public class AppConfig {

    public Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();

        return converters;
    }

    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.setConverters(getConverters());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

}
