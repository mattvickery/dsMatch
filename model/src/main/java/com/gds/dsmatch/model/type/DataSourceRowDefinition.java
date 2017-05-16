package com.gds.dsmatch.model.type;

import java.io.Serializable;
import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceRowDefinition extends Serializable {

    List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitions();
    List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitionByName(final String name);
    DataSourceRowDefinition add(final DataSourceFieldDefinition<?> dataSourceFieldDefinition);
}