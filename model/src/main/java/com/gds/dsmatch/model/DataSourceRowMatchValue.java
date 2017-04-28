package com.gds.dsmatch.model;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceRowMatchValue {

    List<DataSourceFieldPairMatchValue<?>> getDataSourceFieldPairMatchValue();
    DataSourceRowMatchValue add(final DataSourceFieldPairMatchValue<?> dataSourceFieldPairMatchValue);
    DataSourceRowMatchValue setResultsAnnotation(final String resultsAnnotation);
    String getResultsAnnotation();
    long getRowNumber();
}