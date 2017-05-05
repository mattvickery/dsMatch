package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceMatchingDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.MatchKeyDefinition;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceMatchingDefinition implements DataSourceMatchingDefinition {

    private final List<MatchKeyDefinition> matchKeyDefinitions;
    private final DataSourceRowDefinition lhs;
    private final DataSourceRowDefinition rhs;

    public DefaultDataSourceMatchingDefinition(final List<MatchKeyDefinition> matchKeyDefinitions,
                                                final DataSourceRowDefinition lhs,
                                                final DataSourceRowDefinition rhs) {

        notNull(matchKeyDefinitions, "Mandatory argument 'matchKeyDefinitions' is missing.");
        notNull(lhs, "Mandatory argument 'lhs' is missing.");
        notNull(rhs, "Mandatory argument 'rhs' is missing.");
        this.matchKeyDefinitions = matchKeyDefinitions;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public List<MatchKeyDefinition> getMatchKeyDefinitions() {
        return Collections.unmodifiableList(matchKeyDefinitions);
    }

    @Override
    public DataSourceRowDefinition getLhs() {
        return lhs;
    }

    @Override
    public DataSourceRowDefinition getRhs() {
        return rhs;
    }
}