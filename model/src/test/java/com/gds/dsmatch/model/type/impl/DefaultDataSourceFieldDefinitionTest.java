package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceFieldDefinitionTest {

    public static final String SAMPLE_FIELD_NAME = "sampleFieldName";
    public static Class<String> TYPE = String.class;

    @Test
    public void constructorNonNullArgument() {
        new DefaultDataSourceFieldDefinition<>(SAMPLE_FIELD_NAME, TYPE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorNullArgument_1() {
        new DefaultDataSourceFieldDefinition<>(null, TYPE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorNullArgument_2() {
        new DefaultDataSourceFieldDefinition<>(SAMPLE_FIELD_NAME, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorNullArgument_3() {
        new DefaultDataSourceFieldDefinition<>(null, null);
    }

    @Test
    public void simpleSetupAndTest() {
        final DataSourceFieldDefinition<String> fieldDefinition
                = new DefaultDataSourceFieldDefinition<>(SAMPLE_FIELD_NAME, TYPE);
        assertThat(fieldDefinition.getFieldName(), is(SAMPLE_FIELD_NAME));
        assertThat(fieldDefinition.getFieldType(), is(instanceOf(TYPE.getClass())));
    }
}