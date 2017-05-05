package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceRowDefinition implements DataSourceRowDefinition {

    private final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions;

    public DefaultDataSourceRowDefinition(final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions) {
        notNull(dataSourceFieldDefinitions, "Mandatory argument 'dataSourceFieldDefinitions' is missing.");
        this.dataSourceFieldDefinitions = dataSourceFieldDefinitions;
    }

    @Override
    public List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitions() {
        return Collections.unmodifiableList(dataSourceFieldDefinitions);
    }

    @Override
    public List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitionByName(final String fieldName) {
        notNull(fieldName, "Mandatory argument 'fieldName' is missing.");
        return dataSourceFieldDefinitions.stream()
                .filter(fieldDefinition -> fieldName.equalsIgnoreCase(fieldDefinition.getFieldName()))
                .collect(Collectors.toList());
    }

    @Override
    public DataSourceRowDefinition add(final DataSourceFieldDefinition<?> dataSourceFieldDefinition) {
        notNull(dataSourceFieldDefinition, "Mandatory argument 'dataSourceFieldDefinition' is missing.");
        dataSourceFieldDefinitions.add(dataSourceFieldDefinition);
        return this;
    }
}
