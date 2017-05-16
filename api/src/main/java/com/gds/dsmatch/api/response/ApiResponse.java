package com.gds.dsmatch.api.response;

import com.gds.dsmatch.api.request.ApiRequest;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiResponse<R extends ApiRequest> extends Serializable {
    ApiDataSourceRowDefinition getApiDataSourceRowDefinition();
    R getApiRequest();
    ApiResponseProcessingResult getProcessingResult();
}