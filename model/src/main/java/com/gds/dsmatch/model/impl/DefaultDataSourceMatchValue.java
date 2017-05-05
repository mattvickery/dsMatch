package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceMatchValue;
import com.gds.dsmatch.model.DataSourceRowMatchValue;
import com.gds.dsmatch.model.type.DataSourceMatchingDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceMatchValue implements DataSourceMatchValue {

    private final DataSourceMatchingDefinition dataSourceMatchingDefinition;
    private final List<DataSourceRowMatchValue> dataSourceRowMatchValues = new ArrayList<>();

    public DefaultDataSourceMatchValue(final DataSourceMatchingDefinition dataSourceMatchingDefinition) {

        notNull(dataSourceMatchingDefinition, "Mandatory argument 'dataSourceMatchingDefinition' is missing.");
        this.dataSourceMatchingDefinition = dataSourceMatchingDefinition;
    }

    @Override
    public DataSourceMatchingDefinition getDataSourceMatchingDefinition() {
        return dataSourceMatchingDefinition;
    }

    @Override
    public List<DataSourceRowMatchValue> getDataSOurceRowMatchValues() {
        return Collections.unmodifiableList(dataSourceRowMatchValues);
    }

    @Override
    public void add(final DataSourceRowMatchValue dataSourceRowMatchValue) {

        notNull(dataSourceRowMatchValue, "Mandatory argument 'dataSourceRowMatchValue' is missing.");
        dataSourceRowMatchValues.add(dataSourceRowMatchValue);
    }
}
