package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.transform.context.FileDataSourceParseContext;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ExcelFileDataSourceParseContext extends FileDataSourceParseContext {
    int getSheetIndex();
}