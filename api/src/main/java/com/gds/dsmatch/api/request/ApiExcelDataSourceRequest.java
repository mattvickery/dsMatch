package com.gds.dsmatch.api.request;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiExcelDataSourceRequest extends ApiFileBasedDataSourceRequest, Serializable {

    int getHeaderRowIndex();
    int getDataRowIndex();
    int getSheetIndex();
}