package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;

import java.io.Serializable;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceFieldValue<T extends Serializable> implements DataSourceFieldValue<T> {

    private final DataSourceFieldDefinition<T> dataSourceFieldDefinition;
    private final T dataSourceFieldValue;

    public DefaultDataSourceFieldValue(final DataSourceFieldDefinition<T> dataSourceFieldDefinition,
                                        final T dataSourceFieldValue) {

        notNull(dataSourceFieldDefinition, "Mandatory argument 'dataSourceFieldDefinition' is missing.");
        notNull(dataSourceFieldValue, "Mandatory argument 'dataSourceFieldValue' is missing.");
        this.dataSourceFieldDefinition = dataSourceFieldDefinition;
        this.dataSourceFieldValue = dataSourceFieldValue;
    }

    @Override
    public DataSourceFieldDefinition<T> getDataSourceFieldDefinition() {
        return dataSourceFieldDefinition;
    }

    @Override
    public T getDataSourceFieldValue() {
        return dataSourceFieldValue;
    }
}
