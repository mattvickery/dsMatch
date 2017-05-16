package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.DataSourceRowCompositeValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceRowCompositeValue implements DataSourceRowCompositeValue {

    private final List<DataSourceFieldCompositeValue<?>> dataSourceFieldCompositeValues = new ArrayList<>();
    private String resultsAnnotation;
    private final long rowNumber;

    public DefaultDataSourceRowCompositeValue(final long rowNumber) {

        state(rowNumber >= 0, "Invalid argument, row number should be >= 0");
        this.rowNumber = rowNumber;
    }

    public List<DataSourceFieldCompositeValue<?>> getDataSourceFieldCompositeValues() {
        return Collections.unmodifiableList(dataSourceFieldCompositeValues);
    }

    @Override
    public DataSourceRowCompositeValue add(final DataSourceFieldCompositeValue<?> dataSourceFieldCompositeValue) {
        notNull(dataSourceFieldCompositeValue, "Mandatory argument 'dataSourceFieldPairCompositeValue' is missing.");
        dataSourceFieldCompositeValues.add(dataSourceFieldCompositeValue);
        return this;
    }

    @Override
    public DataSourceRowCompositeValue setResultsAnnotation(final String resultsAnnotation) {
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