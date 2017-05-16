package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinitionTuple;
import com.gds.dsmatch.model.type.DataSourceKeyDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceKeyDefinition implements DataSourceKeyDefinition {

    private final String keyDefinitionName;
    private final List<DataSourceFieldDefinitionTuple> keyDefinitions = new ArrayList<>();

    public DefaultDataSourceKeyDefinition(final String keyDefinitionName) {
        notNull(keyDefinitionName, "Mandatory argument 'keyDefinitionName' is missing.");
        state(keyDefinitionName.length() > 0, "Invalid empty argument 'keyDefinitionName'.");
        this.keyDefinitionName = keyDefinitionName;
    }


    @Override
    public String getKeyDefinitionName() {
        return keyDefinitionName;
    }

    @Override
    public DataSourceKeyDefinition addKeyPair(final DataSourceFieldDefinition<?> lhs,
                                              final DataSourceFieldDefinition<?> rhs) {

        notNull(lhs, "Mandatory argument 'lhs' is missing.");
        notNull(rhs, "Mandatory argument 'rhs' is missing.");
        keyDefinitions.add(new DataSourceFieldDefinitionTuple() {
            @Override
            public DataSourceFieldDefinition<?> getLhs() {
                return lhs;
            }

            @Override
            public DataSourceFieldDefinition<?> getRhs() {
                return rhs;
            }
        });
        return this;
    }

    @Override
    public List<DataSourceFieldDefinitionTuple> getKeyFieldDefinitions() {
        return Collections.unmodifiableList(keyDefinitions);
    }
}
