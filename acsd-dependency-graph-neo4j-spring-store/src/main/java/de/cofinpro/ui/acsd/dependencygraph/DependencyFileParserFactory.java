package de.cofinpro.ui.acsd.dependencygraph;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

/**
 * Created by David Olah on 19.08.2018.
 */
public class DependencyFileParserFactory {
    private static final Logger logger = LoggerFactory.getLogger(DependencyFileParserFactory.class);

    private AttributeRepository attributeRepository;

    public DependencyFileParserFactory(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public DependencyFileParser createForFilename(String filename) {
        boolean isInterdepency = StringUtils.endsWith(filename, "DEPENDENCIES.tsv");
        String path = "classpath:acsd-dependency-files/" + filename;
        List<String> lines;
        try {
            File file = ResourceUtils.getFile(path);
            lines = FileUtils.readLines(file, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            lines = Collections.emptyList();
            logger.error("Could not read content from path {}", path);
        }
        return new DependencyFileParser(attributeRepository, filename, lines, isInterdepency);
    }
}
