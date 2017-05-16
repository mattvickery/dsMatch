package com.gds.dsmatch.api.service.protocol;

import com.gds.dsmatch.api.ApiException;
import com.gds.dsmatch.api.request.ApiExcelDataSourceRequest;
import com.gds.dsmatch.api.request.ApiExcelDataSourceRequestBuilder;
import com.gds.dsmatch.api.response.ApiResponse;
import com.gds.dsmatch.api.service.ExcelDataSourceDefinitionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import static org.springframework.util.Assert.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 09/05/2017
 */
@RestController
public class RestExcelDataSourceDefinitionController {

    private final ExcelDataSourceDefinitionService<ApiExcelDataSourceRequest> service;
    private final ApiExcelDataSourceRequestBuilder builder;

    public RestExcelDataSourceDefinitionController(final ExcelDataSourceDefinitionService<ApiExcelDataSourceRequest> service,
                                                   final ApiExcelDataSourceRequestBuilder builder) {
        notNull(service, "Mandatory argument 'service' is missing.");
        notNull(builder, "Mandatory argument 'builder' is missing.");
        this.service = service;
        this.builder = builder;
    }

    @RequestMapping(value = "/dataSourceDefinition", method = POST)
    public ApiResponse<ApiExcelDataSourceRequest> handleRequest(@RequestParam(value = "dataSourceDefinitionName")
                                                                    final String dataSourceDefinitionName,
                                                                @RequestParam(value = "dataSourceFile")
                                                                    final File dataSourceFile,
                                                                @RequestParam(value = "sheetIndex")
                                                                    final int sheetIndex,
                                                                @RequestParam(value = "headerRowIndex")
                                                                    final int headerRowIndex,
                                                                @RequestParam(value = "dataRowIndex")
                                                                    final int dataRowIndex) throws ApiException {

        return service.handleRequest(builder.addName(dataSourceDefinitionName).add(dataSourceFile).addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex).addDataRowIndex(dataRowIndex).build());
    }
}