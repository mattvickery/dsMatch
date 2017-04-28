package com.gds.dsmatch.model.type;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceFieldDefinition<T extends Serializable> {

    String getFieldName();
    Class<T> getFieldType();
}