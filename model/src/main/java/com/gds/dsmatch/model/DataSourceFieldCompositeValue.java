package com.gds.dsmatch.model;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceFieldCompositeValue<T extends Serializable> {

    DataSourceFieldValue<T> getDataSourceLhsValue();
    DataSourceFieldValue<T> getDataSourceRhsValue();
    boolean getMatched();
    boolean match(final Function<DataSourceFieldCompositeValue<T>, Boolean> matchingStrategyVisitor);
}