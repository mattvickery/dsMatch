package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface MatchingStrategyVisitor<T extends Serializable> {
    boolean visit(final DataSourceFieldCompositeValue<T> dataSourceFieldCompositeValue);
}