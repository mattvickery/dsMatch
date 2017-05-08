package com.gds.dsmatch.transform.context.builder;

import com.gds.dsmatch.transform.excel.ExcelFileDataSourceParseContext;

import java.io.File;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelDataSourceParseContextBuilder {

    public static final int DEFAULT_SHEET_INDEX = 0;
    public static final int DEFAULT_HEADER_ROW_INDEX = 0;
    public static final int DEFAULT_DATA_ROW_INDEX = 0;

    private int sheetIndex = DEFAULT_SHEET_INDEX;
    private File file;
    private int headerRowIndex = DEFAULT_HEADER_ROW_INDEX;
    private int dataRowIndex = DEFAULT_DATA_ROW_INDEX;
    private String name;

    public ExcelDataSourceParseContextBuilder addSheetIndex(final int sheetIndex) {
        state(sheetIndex >= 0, "Invalid negative sheet index.");
        this.sheetIndex = sheetIndex;
        return this;
    }

    public ExcelDataSourceParseContextBuilder addHeaderRowIndex(final int headerRowIndex) {
        state(headerRowIndex >= 0, "Invalid header row index.");
        this.headerRowIndex = headerRowIndex;
        return this;
    }

    public ExcelDataSourceParseContextBuilder addDataRowIndex(final int dataRowIndex) {
        state(dataRowIndex >= 0, "Invalid data row index.");
        this.dataRowIndex = dataRowIndex;
        return this;
    }

    public ExcelDataSourceParseContextBuilder add(final File file) {
        notNull(file, "Mandatory argument 'file' is missing.");
        this.file = file;
        return this;
    }

    public ExcelDataSourceParseContextBuilder addName(final String name) {
        notNull(name, "Mandatory argument 'name' is missing.");
        this.name = name;
        return this;
    }

    public ExcelFileDataSourceParseContext build() {
        state(file != null, "File has not been set.");
        state(name != null, "Name has not been set.");
        return new ExcelFileDataSourceParseContext() {

            @Override
            public String getName() {
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