package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.DataSourceRowValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceRowValue implements DataSourceRowValue {

    private final List<DataSourceFieldValue<?>> dataSourceFieldValues = new ArrayList<>();

    @Override
    public List<DataSourceFieldValue> getDataSourceFieldValues() {
        return Collections.unmodifiableList(dataSourceFieldValues);
    }

    @Override
    public DataSourceRowValue add(final DataSourceFieldValue<?> dataSourceFieldValue) {
        notNull(dataSourceFieldValue, "Mandatory argument 'dataSourceFieldValue' is missing.");
        dataSourceFieldValues.add(dataSourceFieldValue);
        return this;
    }
}