package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceCompositeDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.DataSourceKeyDefinition;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceCompositeDefinition implements DataSourceCompositeDefinition {

    private final List<DataSourceKeyDefinition> dataSourceKeyDefinitions;
    private final DataSourceRowDefinition lhs;
    private final DataSourceRowDefinition rhs;

    public DefaultDataSourceCompositeDefinition(final List<DataSourceKeyDefinition> dataSourceKeyDefinitions,
                                                final DataSourceRowDefinition lhs,
                                                final DataSourceRowDefinition rhs) {

        notNull(dataSourceKeyDefinitions, "Mandatory argument 'dataSourceKeyDefinitions' is missing.");
        notNull(lhs, "Mandatory argument 'lhs' is missing.");
        notNull(rhs, "Mandatory argument 'rhs' is missing.");
        this.dataSourceKeyDefinitions = dataSourceKeyDefinitions;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public List<DataSourceKeyDefinition> getDataSourceKeyDefinitions() {
        return Collections.unmodifiableList(dataSourceKeyDefinitions);
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