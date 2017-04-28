package com.gds.dsmatch.model.type;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface DataSourceFieldDefinitionTuple {

    DataSourceFieldDefinition<?> getLhs();
    DataSourceFieldDefinition<?> getRhs();
}