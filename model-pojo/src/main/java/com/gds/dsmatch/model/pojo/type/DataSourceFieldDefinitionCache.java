package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DataSourceFieldDefinitionCache {

        private final Map<String, Map<String, DataSourceFieldDefinition<?>>> fieldDefinitionCache = new HashMap<>();

    public Optional<DataSourceFieldDefinition<?>> getFieldDefinition(final String fieldName, final Class<?> type) {

        notNull(fieldName, "Mandatory argument 'fieldName' is missing.");
        notNull(type, "Mandatory argument 'type' is missing.");
        final Map<String, DataSourceFieldDefinition<?>> fieldDefinition = fieldDefinitionCache.get(fieldName);
        if (fieldDefinition == null)
            return Optional.empty();
        return Optional.ofNullable(fieldDefinition.get(type.getSimpleName()));
    }

    public void add(final DataSourceFieldDefinition<?> dataSourceFieldDefinition) {

        notNull(dataSourceFieldDefinition, "Mandatory argument 'dataSourceFieldDefinition' is missing.");
        final Map<String, DataSourceFieldDefinition<?>> fieldDefinition
                = fieldDefinitionCache.get(dataSourceFieldDefinition.getFieldName());
        if (fieldDefinition == null) {
            final Map<String, DataSourceFieldDefinition<?>> typeCache = new HashMap<>();
            typeCache.put(dataSourceFieldDefinition.getFieldType().getSimpleName(), dataSourceFieldDefinition);
            fieldDefinitionCache.put(dataSourceFieldDefinition.getFieldName(), typeCache);
        } else {
            fieldDefinition.putIfAbsent(dataSourceFieldDefinition.getFieldType().getSimpleName(),
                    dataSourceFieldDefinition);
        }

    }
}