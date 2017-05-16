package com.gds.dsmatch.model;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceRowCompositeValue {

    List<DataSourceFieldCompositeValue<?>> getDataSourceFieldCompositeValues();
    DataSourceRowCompositeValue add(final DataSourceFieldCompositeValue<?> dataSourceFieldCompositeValue);
    DataSourceRowCompositeValue setResultsAnnotation(final String resultsAnnotation);
    String getResultsAnnotation();
    long getRowNumber();
}