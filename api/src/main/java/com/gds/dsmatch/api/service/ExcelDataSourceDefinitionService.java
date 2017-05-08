package com.gds.dsmatch.api.service;

import com.gds.dsmatch.api.ApiException;
import com.gds.dsmatch.api.DataSourceTypeDefinitionService;
import com.gds.dsmatch.api.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.api.response.ApiResponse;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelDataSourceDefinitionService implements DataSourceTypeDefinitionService<ApiExcelDataSourceRequest> {

    @Override
    public ApiResponse handleRequest(final ApiExcelDataSourceRequest request) throws ApiException {
        return null;
    }
}