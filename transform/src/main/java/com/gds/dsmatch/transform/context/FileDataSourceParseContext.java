package com.gds.dsmatch.transform.context;

import java.io.File;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface FileDataSourceParseContext extends DataSourceParseContext {

    File getFile();
    int getHeaderRowIndex();
    int getDataRowIndex();
}