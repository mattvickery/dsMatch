package com.gds.dsmatch.model;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceRowValue {

    List<DataSourceFieldValue> getDataSourceFieldValues();
    DataSourceRowValue add(final DataSourceFieldValue<?> dataSourceFieldValue);
}