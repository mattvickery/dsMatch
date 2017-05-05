package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceRowDefinitionTest {

    public static final String FIELD_NAME = "Rate";
    public static final Class<BigDecimal> TYPE = BigDecimal.class;

    private final DataSourceFieldDefinition<BigDecimal> fieldDefinition
            = new DefaultDataSourceFieldDefinition<>(FIELD_NAME, TYPE);
    private final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions
            = new ArrayList<DataSourceFieldDefinition<?>>() {{
        add(fieldDefinition);
    }};
    private final DataSourceRowDefinition rowDefinition
            = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullValues_1() {
        new DefaultDataSourceRowDefinition(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getFieldByName_nullArgument() {
        rowDefinition.getDataSourceFieldDefinitionByName(null);
    }

    @Test
    public void simpleSetupAndTest() {
        assertThat(rowDefinition.getDataSourceFieldDefinitions(), notNullValue());
        assertThat(rowDefinition.getDataSourceFieldDefinitions().size(), is(1));
    }

    @Test
    public void getFieldByName() {
        assertThat(rowDefinition.getDataSourceFieldDefinitions().size(), is(1));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName(FIELD_NAME).size(), is(1));
        rowDefinition.add(new DefaultDataSourceFieldDefinition<>(FIELD_NAME, TYPE));
        assertThat(rowDefinition.getDataSourceFieldDefinitions().size(), is(2));
        assertThat(rowDefinition.getDataSourceFieldDefinitionByName(FIELD_NAME).size(), is(2));
    }
}