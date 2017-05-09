package com.gds.dsmatch.api.request;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 09/05/2017
 */
public class ApiExcelDataSourceRequestBuilderTest {

    private final ApiExcelDataSourceRequestBuilder builder = new ApiExcelDataSourceRequestBuilder();
    private final Integer sheetIndex = 0;
    private final Integer headerRowIndex = 0;
    private final Integer dataRowIndex = 0;
    private final File sampleFile = new File("filename.txt");
    private final String dataSourceDefinitionName = "source-1";

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete() {
        builder.build();
    }

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete_file() {
        builder.addName(dataSourceDefinitionName)
                .addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex)
                .addDataRowIndex(dataRowIndex).build();
    }

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete_sataSourceDefinitionName() {
        builder.add(sampleFile)
                .addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex)
                .addDataRowIndex(dataRowIndex).build();
    }

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete_sheetIndex() {
        builder.addName(dataSourceDefinitionName)
                .add(sampleFile)
                .addHeaderRowIndex(headerRowIndex)
                .addDataRowIndex(dataRowIndex).build();
    }

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete_headerRowIndex() {
        builder.addName(dataSourceDefinitionName)
                .add(sampleFile)
                .addSheetIndex(sheetIndex)
                .addDataRowIndex(dataRowIndex).build();
    }

    @Test (expected = IllegalStateException.class)
    public void defaultBuilderIncomplete_dataRowIndex() {
        builder.addName(dataSourceDefinitionName)
                .add(sampleFile)
                .addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex).build();
    }

    @Test
    public void simpleDefaultComplete() {

        final ApiExcelDataSourceRequest request = builder.addName(dataSourceDefinitionName)
                .add(sampleFile)
                .addSheetIndex(sheetIndex)
                .addHeaderRowIndex(headerRowIndex)
                .addDataRowIndex(dataRowIndex).build();

        assertThat(request.getDataSourceDefinitionName(), is(dataSourceDefinitionName));
        assertThat(request.getFile(), is(sampleFile));
        assertThat(request.getSheetIndex(), is(sheetIndex));
        assertThat(request.getHeaderRowIndex(), is(headerRowIndex));
        assertThat(request.getDataRowIndex(), is(dataRowIndex));
    }
}