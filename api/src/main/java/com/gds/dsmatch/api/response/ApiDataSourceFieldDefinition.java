package com.gds.dsmatch.api.response;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiDataSourceFieldDefinition<T extends Serializable> extends Serializable {
    String getFieldName();
    Class<T> getFieldType();
}