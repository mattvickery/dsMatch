package com.gds.dsmatch.model;

import com.gds.dsmatch.model.type.DataSourceMatchingDefinition;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceMatchValue {

    DataSourceMatchingDefinition getDataSourceMatchingDefinition();
    List<DataSourceRowMatchValue> getDataSOurceRowMatchValues();
    void add(final DataSourceRowMatchValue dataSourceRowMatchValue);
}