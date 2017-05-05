package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinitionTuple;
import com.gds.dsmatch.model.type.MatchKeyDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultMatchKeyDefinition implements MatchKeyDefinition {

    private final String matchKeyDefinitionName;
    private final List<DataSourceFieldDefinitionTuple> matchKeys = new ArrayList<>();

    public DefaultMatchKeyDefinition(final String matchKeyDefinitionName) {
        notNull(matchKeyDefinitionName, "Mandatory argument 'matchKeyDefinitionName' is missing.");
        state(matchKeyDefinitionName.length() > 0, "Invalid empty argument 'matchKeyDefinitionName'.");
        this.matchKeyDefinitionName = matchKeyDefinitionName;
    }


    @Override
    public String getMatchKeyDefinitionName() {
        return matchKeyDefinitionName;
    }

    @Override
    public MatchKeyDefinition addMatchKeyPair(final DataSourceFieldDefinition<?> lhs,
                                              final DataSourceFieldDefinition<?> rhs) {

        notNull(lhs, "Mandatory argument 'lhs' is missing.");
        notNull(rhs, "Mandatory argument 'rhs' is missing.");
        matchKeys.add(new DataSourceFieldDefinitionTuple() {
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
    public List<DataSourceFieldDefinitionTuple> getMatchKeys() {
        return Collections.unmodifiableList(matchKeys);
    }
}
