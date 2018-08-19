package de.cofinpro.ui.acsd.dependencygraph;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by David Olah on 19.08.2018.
 */
public class DependencyFileParser {
    private static final Logger logger = LoggerFactory.getLogger(DependencyFileParser.class);
    private AttributeRepository attributeRepository;
    private String filename;
    private List<String> lines;

    private boolean isInterdepency = false;

    private static final int INDEX_ATTRIBUTE_ID = 0;
    private static final int INDEX_ATTRIBUTE_DEPENDS_ON = 1;

    public DependencyFileParser(AttributeRepository attributeRepository, String filename, List<String> lines, boolean isInterdepency) {
        this.attributeRepository = attributeRepository;
        this.filename = filename;
        this.lines = lines;
        this.isInterdepency = isInterdepency;
    }

    public void parseAndPersist() {
        logger.info("Starting to parse contents of {} ({} lines)", filename, lines);
        if (CollectionUtils.isNotEmpty(lines)) {
            int i = 1;
            for (String line : lines) {
                if (StringUtils.isNotBlank(line)) {
                    logger.info("Parsing line #{} of {} of {}", i, lines.size(), filename);
                    String[] tabSeparatedParts = line.split("\t");
                    String attributeId = tabSeparatedParts[INDEX_ATTRIBUTE_ID];
                    String dependsOnAttributeId = tabSeparatedParts[INDEX_ATTRIBUTE_DEPENDS_ON];

                    Attribute dependsOnAttribute = getAttributeForId(dependsOnAttributeId);
                    Attribute attribute = getAttributeForId(attributeId);
                    attribute.dependsOn(dependsOnAttribute);
                    attributeRepository.save(attribute);
                }
                i++;
            }
        }
    }

    private Attribute getAttributeForId(String attributeId) {
        Attribute attribute = attributeRepository.findByName(attributeId);
        if (attribute == null) {
            attribute = new Attribute(attributeId);
            attributeRepository.save(attribute);
        }
        return attribute;
    }
}
