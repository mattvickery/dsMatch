package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.DataSourceRowMatchValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceRowMatchValue implements DataSourceRowMatchValue {

    private final List<DataSourceFieldPairMatchValue<?>> dataSourceFieldPairMatchValues = new ArrayList<>();
    private String resultsAnnotation;
    private final long rowNumber;

    private DefaultDataSourceRowMatchValue(final long rowNumber) {

        state(rowNumber >= 0, "Invalid argument, row number should be >= 0");
        this.rowNumber = rowNumber;
    }

    @Override
    public List<DataSourceFieldPairMatchValue<?>> getDataSourceFieldPairMatchValue() {
        return Collections.unmodifiableList(dataSourceFieldPairMatchValues);
    }

    @Override
    public DataSourceRowMatchValue add(final DataSourceFieldPairMatchValue<?> dataSourceFieldPairMatchValue) {
        notNull(dataSourceFieldPairMatchValue, "Mandatory argument 'dataSourceFieldPairMatchValue' is missing.");
        dataSourceFieldPairMatchValues.add(dataSourceFieldPairMatchValue);
        return this;
    }

    @Override
    public DataSourceRowMatchValue setResultsAnnotation(final String resultsAnnotation) {
        notNull(resultsAnnotation, "Mandatory argument 'resultsAnnotation' is missing.");
        this.resultsAnnotation = resultsAnnotation;
        return this;
    }

    @Override
    public String getResultsAnnotation() {
        return resultsAnnotation;
    }

    @Override
    public long getRowNumber() {
        return rowNumber;
    }
}