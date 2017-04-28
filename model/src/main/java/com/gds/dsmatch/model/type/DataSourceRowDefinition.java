package com.gds.dsmatch.model.type;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceRowDefinition {

    List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitions();
    List<DataSourceFieldDefinition<?>> getDataSourceFieldDefinitionByName();
    DataSourceRowDefinition add(final DataSourceFieldDefinition<?> dataSourceFieldDefinition);
}