package com.gds.dsmatch.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiDataSourceRowDefinition extends Serializable {
    List<ApiDataSourceFieldDefinition<?>> getDataSourceFieldDefinitions();
}