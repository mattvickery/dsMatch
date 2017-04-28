package com.gds.dsmatch.model;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceFieldValue<T extends Serializable> {

    DataSourceFieldDefinition<T> getDataSourceFieldDefinition();
    T getDataSourceFieldValue();
}