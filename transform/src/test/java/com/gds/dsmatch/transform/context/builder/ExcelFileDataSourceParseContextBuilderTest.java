package com.gds.dsmatch.transform.context.builder;

import org.junit.Test;

import java.io.File;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelFileDataSourceParseContextBuilderTest {

    private final ExcelDataSourceParseContextBuilder builder =
            new ExcelDataSourceParseContextBuilder();

    @Test (expected = IllegalStateException.class)
    public void build_file_missing() {
        builder.build();
    }

    @Test
    public void build_standard() {
        builder.add(new File("sample.xlsx"));
    }

    @Test (expected = IllegalStateException.class)
    public void build_negativeSheetIndex() {
        builder.add(new File("sample.xlsx")).addSheetIndex(-1);
    }

    @Test (expected = IllegalStateException.class)
    public void build_negativeHeaderRowIndex() {
        builder.add(new File("sample.xlsx")).addHeaderRowIndex(-1);
    }

    @Test (expected = IllegalStateException.class)
    public void build_negativeDataRowIndex() {
        builder.add(new File("sample.xlsx")).addDataRowIndex(-1);
    }
}