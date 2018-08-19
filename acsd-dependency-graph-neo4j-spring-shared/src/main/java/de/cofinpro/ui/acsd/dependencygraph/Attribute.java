package de.cofinpro.ui.acsd.dependencygraph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by David Olah on 19.08.2018.
 */
@NodeEntity
public class Attribute {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @Relationship(type = "DEPENDENCY")
    private Set<Attribute> dependencies = new HashSet<>();

    private Attribute() {
        // Empty constructor required as of Neo4j API 2.0.5
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void dependsOn(Attribute attribute) {
        dependencies.add(attribute);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dependencies=" + Optional.ofNullable(dependencies).orElse(Collections.emptySet()).stream().map(Attribute::getName).collect(Collectors.toList()) +
                '}';
    }
}
