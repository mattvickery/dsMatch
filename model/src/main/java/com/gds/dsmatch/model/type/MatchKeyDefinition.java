package com.gds.dsmatch.model.type;

import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public interface MatchKeyDefinition {

    String getMatchKeyDefinitionName();
    MatchKeyDefinition addMatchKeyPair(final DataSourceFieldDefinition<?> lhs,
                                       final DataSourceFieldDefinition<?> rhs);
    List<DataSourceFieldDefinitionTuple> getMatchKeys();
}