package com.gds.dsmatch.api.service;

import com.gds.dsmatch.api.ApiException;
import com.gds.dsmatch.api.DataSourceTypeDefinitionService;
import com.gds.dsmatch.api.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.api.response.ApiDataSourceRowDefinition;
import com.gds.dsmatch.api.response.ApiResponse;
import com.gds.dsmatch.api.response.ApiResponseProcessingResult;
import com.gds.dsmatch.api.transform.ExcelRequestToResponseTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.gds.dsmatch.api.response.ApiResponseProcessingResult.PROCESSING_EXCEPTION;
import static com.gds.dsmatch.api.response.ApiResponseProcessingResult.SUCCESS;
import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelDataSourceDefinitionService<T extends ApiExcelDataSourceRequest>
        implements DataSourceTypeDefinitionService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelDataSourceDefinitionService.class);
    private final ExcelRequestToResponseTransformer transformer;

    public ExcelDataSourceDefinitionService(final ExcelRequestToResponseTransformer transformer) {
        notNull(transformer, "Mandatory argument 'transformer' is missing.");
        this.transformer = transformer;
    }

    @Override
    public ApiResponse<T> handleRequest(final T request) throws ApiException {

        notNull(request, "Mandatory argument 'request' is missing.");
        return new ApiResponse<T>() {
            private ApiResponseProcessingResult result;
            @Override
            public ApiDataSourceRowDefinition getApiDataSourceRowDefinition() {
                try {
                    final ApiDataSourceRowDefinition rowDefinition = transformer.transform(request);
                    result = SUCCESS;
                    return rowDefinition;
                } catch (ApiException e) {
                    LOG.error("API Processing exception:", e);
                    result = PROCESSING_EXCEPTION;
                    return () -> new ArrayList<>();
                }
            }

            @Override
            public T getApiRequest() {
                return request;
            }

            @Override
            public ApiResponseProcessingResult getProcessingResult() {
                return result;
            }
        };
    }
}