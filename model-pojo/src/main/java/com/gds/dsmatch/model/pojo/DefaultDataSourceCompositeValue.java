package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceCompositeValue;
import com.gds.dsmatch.model.DataSourceRowCompositeValue;
import com.gds.dsmatch.model.type.DataSourceCompositeDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceCompositeValue implements DataSourceCompositeValue {

    private final DataSourceCompositeDefinition dataSourceCompositeDefinition;
    private final List<DataSourceRowCompositeValue> dataSourceRowCompositeValues = new ArrayList<>();

    public DefaultDataSourceCompositeValue(final DataSourceCompositeDefinition dataSourceCompositeDefinition) {

        notNull(dataSourceCompositeDefinition, "Mandatory argument 'dataSourceCompositeDefinition' is missing.");
        this.dataSourceCompositeDefinition = dataSourceCompositeDefinition;
    }

    public DataSourceCompositeDefinition getDataSourceCompositeDefinition() {
        return dataSourceCompositeDefinition;
    }

    @Override
    public List<DataSourceRowCompositeValue> getDataSourceRowCompositeValues() {
        return Collections.unmodifiableList(dataSourceRowCompositeValues);
    }

    @Override
    public void add(final DataSourceRowCompositeValue dataSourceRowCompositeValue) {

        notNull(dataSourceRowCompositeValue, "Mandatory argument 'dataSourceRowCompositeValue' is missing.");
        dataSourceRowCompositeValues.add(dataSourceRowCompositeValue);
    }
}
