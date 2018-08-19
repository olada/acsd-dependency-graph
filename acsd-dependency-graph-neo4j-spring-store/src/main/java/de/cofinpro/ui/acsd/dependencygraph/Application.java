package de.cofinpro.ui.acsd.dependencygraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * Created by David Olah on 19.08.2018.
 */
@SpringBootApplication
@EnableNeo4jRepositories
public class Application {
    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(AttributeRepository attributeRepository, DependencyFileParserFactory dependencyFileParserFactory) {
        return args -> {
            attributeRepository.deleteAll();

            List<String> filenames = Arrays.asList("co#sad.tsv", "coDEPENDENCIES.tsv", "N0#DEPENDENCIES.tsv", "N0#sad.tsv");

            filenames.forEach(filename -> {
                DependencyFileParser dependencyFileParser = dependencyFileParserFactory.createForFilename(filename);
                dependencyFileParser.parseAndPersist();
            });
        };
    }
}
