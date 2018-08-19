package de.cofinpro.ui.acsd.dependencygraph;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by David Olah on 19.08.2018.
 */
public interface AttributeRepository extends CrudRepository<Attribute, Long> {
    Attribute findByName(String name);
}
