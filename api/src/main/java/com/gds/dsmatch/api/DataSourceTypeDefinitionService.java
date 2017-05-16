package com.gds.dsmatch.api;

import com.gds.dsmatch.api.request.ApiRequest;
import com.gds.dsmatch.api.response.ApiResponse;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface DataSourceTypeDefinitionService<T extends ApiRequest> {
    ApiResponse handleRequest(final T request) throws ApiException;
}
