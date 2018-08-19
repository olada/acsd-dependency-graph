package de.cofinpro.ui.acsd.dependencygraph;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by David Olah on 19.08.2018.
 */
@Configuration
public class Config {
    @Bean
    public DependencyFileParserFactory dependencyFileParserFactory(AttributeRepository attributeRepository) {
        return new DependencyFileParserFactory(attributeRepository);
    }
}
