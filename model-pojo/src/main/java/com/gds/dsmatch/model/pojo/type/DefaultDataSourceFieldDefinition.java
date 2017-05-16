package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;

import java.io.Serializable;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceFieldDefinition<T extends Serializable> implements DataSourceFieldDefinition<T> {

    private final String fieldName;
    private final Class<T> fieldType;

    public DefaultDataSourceFieldDefinition(final String fieldName, final Class<T> fieldType) {

        notNull(fieldName, "Mandatory argument 'fieldName' is missing.");
        notNull(fieldType, "Mandatory argument 'fieldType' is missing.");
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Class<T> getFieldType() {
        return fieldType;
    }
}