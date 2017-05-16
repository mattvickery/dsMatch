package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
abstract class AbstractDataSourceModelTransformerTest {

    public void dataTypesFromExcelSpreadSheet_typeSet1(final DataSourceRowDefinition rowDefinition) throws Exception {

        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("Name").get(0).getFieldType(),
                is(equalTo(String.class)));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("Rate").get(0).getFieldType(),
                is(equalTo(BigDecimal.class)));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("Test").get(0).getFieldType(),
                is(equalTo(BigDecimal.class)));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("JoinDate").get(0).getFieldType(),
                is(equalTo(Date.class)));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("Member").get(0).getFieldType(),
                is(equalTo(Boolean.class)));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName("Notes").get(0).getFieldType(),
                is(equalTo(String.class)));
    }
}