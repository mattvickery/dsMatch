package com.gds.dsmatch.api.request;

import java.io.File;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 09/05/2017
 */
public class ApiExcelDataSourceRequestBuilder {

    private Integer sheetIndex ;
    private File file;
    private Integer headerRowIndex;
    private Integer dataRowIndex;
    private String name;

    public ApiExcelDataSourceRequestBuilder addSheetIndex(final Integer sheetIndex) {
        state(sheetIndex >= 0, "Invalid negative sheet index.");
        this.sheetIndex = sheetIndex;
        return this;
    }

    public ApiExcelDataSourceRequestBuilder addHeaderRowIndex(final Integer headerRowIndex) {
        state(headerRowIndex >= 0, "Invalid header row index.");
        this.headerRowIndex = headerRowIndex;
        return this;
    }

    public ApiExcelDataSourceRequestBuilder addDataRowIndex(final Integer dataRowIndex) {
        state(dataRowIndex >= 0, "Invalid data row index.");
        this.dataRowIndex = dataRowIndex;
        return this;
    }

    public ApiExcelDataSourceRequestBuilder add(final File file) {
        notNull(file, "Mandatory argument 'file' is missing.");
        this.file = file;
        return this;
    }

    public ApiExcelDataSourceRequestBuilder addName(final String name) {
        notNull(name, "Mandatory argument 'name' is missing.");
        this.name = name;
        return this;
    }

    public ApiExcelDataSourceRequest build() {
        state(name != null, "Name has not been set.");
        state(sheetIndex != null, "Sheet index has not been set.");
        state(file != null, "File has not been set.");
        state(headerRowIndex != null, "Header row index has not been set.");
        state(dataRowIndex != null, "Data row index has not been set.");
        return new ApiExcelDataSourceRequest() {

            @Override
            public String getDataSourceDefinitionName() {
                return name;
            }

            @Override
            public int getSheetIndex() {
                return sheetIndex;
            }

            @Override
            public File getFile() {
                return file;
            }

            @Override
            public int getHeaderRowIndex() {
                return headerRowIndex;
            }

            @Override
            public int getDataRowIndex() {
                return dataRowIndex;
            }
        };
    }
}