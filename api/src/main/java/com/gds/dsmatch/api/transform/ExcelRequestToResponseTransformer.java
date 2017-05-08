package com.gds.dsmatch.api.transform;

import com.gds.dsmatch.api.ApiException;
import com.gds.dsmatch.api.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.api.response.ApiDataSourceFieldDefinition;
import com.gds.dsmatch.api.response.ApiDataSourceRowDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.transform.DataSourceModelTransformer;
import com.gds.dsmatch.transform.context.builder.ExcelDataSourceParseContextBuilder;
import com.gds.dsmatch.transform.excel.ExcelFileDataSourceParseContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelRequestToResponseTransformer
        implements ApiTransformer<ApiExcelDataSourceRequest, ApiDataSourceRowDefinition> {

    private final DataSourceModelTransformer<ExcelFileDataSourceParseContext> modelTransformer;
    private final ExcelDataSourceParseContextBuilder builder;

    public ExcelRequestToResponseTransformer(
            final DataSourceModelTransformer<ExcelFileDataSourceParseContext> modelTransformer,
            final ExcelDataSourceParseContextBuilder builder) {

        notNull(modelTransformer, "Mandatory argument 'modelTransformer' is missing.");
        notNull(builder, "Mandatory argument 'builder' is missing.");
        this.modelTransformer = modelTransformer;
        this.builder = builder;
    }

    public ApiDataSourceRowDefinition transform(final ApiExcelDataSourceRequest source) throws ApiException {
        notNull(source, "Mandatory argument 'source' is missing.");
        try {
            return getResponseTransformer().transform(
                    modelTransformer.transform(
                            getRequestTransformer().transform(source)
                    )
            );
        } catch (final IOException e) {
            throw new ApiException(getClass().getSimpleName(), "Error forming transformation response", e);
        }
    }

    private ApiTransformer<ApiExcelDataSourceRequest, ExcelFileDataSourceParseContext> getRequestTransformer() {

        return source -> builder.addName(source.getDataSourceDefinitionName())
                .add(source.getFile()).addSheetIndex(source.getSheetIndex())
                .addHeaderRowIndex(source.getHeaderRowIndex()).addDataRowIndex(source.getDataRowIndex()).build();
    }

    private ApiTransformer<DataSourceRowDefinition, ApiDataSourceRowDefinition> getResponseTransformer() {
        return source -> {
            return () -> {
                return source.getDataSourceFieldDefinitions().stream()
                        .map(this :: transform).collect(Collectors.toList());
            };
        };
    }

    private <T extends Serializable> ApiDataSourceFieldDefinition<T> transform(final DataSourceFieldDefinition<T> source) {

        notNull(source, "Mandatory argument 'source' is missing.");
        return new ApiDataSourceFieldDefinition<T>() {
            @Override
            public String getFieldName() {
                return source.getFieldName();
            }

            @Override
            public Class<T> getFieldType() {
                return source.getFieldType();
            }
        };
    }
}