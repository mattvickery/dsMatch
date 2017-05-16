package com.gds.dsmatch.model;

import com.gds.dsmatch.model.type.DataSourceCompositeDefinition;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceCompositeValue {

    DataSourceCompositeDefinition getDataSourceCompositeDefinition();
    List<DataSourceRowCompositeValue> getDataSourceRowCompositeValues();
    void add(final DataSourceRowCompositeValue dataSourceRowCompositeValue);
}