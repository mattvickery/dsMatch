package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.transform.DataSourceModelTransformer;
import com.gds.dsmatch.transform.context.builder.ExcelDataSourceParseContextBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
@Ignore
public class XssfToDataSourceModelTransformerTest extends AbstractDataSourceModelTransformerTest {

    private static final String TEST_FIXTURE_101 = "test-fixture-101.xlsx";
    private final DataSourceModelTransformer<ExcelFileDataSourceParseContext> transformer
            = new XssfToDataSourceModelTransformer<>();
    private final ClassPathResource resource = new ClassPathResource(TEST_FIXTURE_101);
    private File file;
    private DataSourceRowDefinition dataSourceRowDefinition;
    private ExcelFileDataSourceParseContext parseContext;

    @Before
    public void before() throws Exception {
        file = resource.getFile();
        parseContext = new ExcelDataSourceParseContextBuilder().add(file).addName("default").build();
        dataSourceRowDefinition = transformer.transform(parseContext);
    }

    @Test
    public void defaultTransformerInvocation() throws Exception {
        assertThat(dataSourceRowDefinition.getDataSourceFieldDefinitionByName("Name").get(0).getFieldType(),
                is(equalTo(String.class)));
    }

    @Test
    public void dataSourceModelTypeMatching() throws Exception {
        dataTypesFromExcelSpreadSheet_typeSet1(dataSourceRowDefinition);
    }

    @Test
    public void missingField() throws Exception {
        assertThat(dataSourceRowDefinition.getDataSourceFieldDefinitionByName("House").size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() throws Exception {
        transformer.transform(null);
    }

    @Test (expected = IOException.class)
    public void openNonExistingFile() throws Exception {
         transformer.transform(new ExcelDataSourceParseContextBuilder().add(new File("missing.xsl")).build());
    }

    @Test (expected = IOException.class)
    public void unsupportedFileType() throws Exception {
        transformer.transform(new ExcelDataSourceParseContextBuilder().add(new File("wrongType.o")).build());
    }
}