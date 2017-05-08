package com.gds.dsmatch.transform;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.transform.context.FileDataSourceParseContext;

import java.io.IOException;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface DataSourceModelTransformer<T extends FileDataSourceParseContext> {
    DataSourceRowDefinition transform(final T parseContext) throws IOException;
}
