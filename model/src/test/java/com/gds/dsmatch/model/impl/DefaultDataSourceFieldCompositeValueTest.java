package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.matching.builder.StringMatchingStrategyBuilder;
import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceFieldCompositeValueTest {

    public static final String FILE_NAME = "fileName";
    public static final String TEST_VALUE = "testValue";

    private final DataSourceFieldCompositeValue<String> dataSourceFieldCompositeValue
            = new DefaultDataSourceFieldCompositeValue<>(
            new DefaultDataSourceFieldValue<>(
                    new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE),
            new DefaultDataSourceFieldValue<>(
                    new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE)
    );

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_1() {
        new DefaultDataSourceFieldCompositeValue<String>(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_2() {
        new DefaultDataSourceFieldCompositeValue<String>(new DefaultDataSourceFieldValue<>(
                new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE),
                null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_3() {
        new DefaultDataSourceFieldCompositeValue<String>(null,
                new DefaultDataSourceFieldValue<>(
                        new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullMatchingStrategy() {
        dataSourceFieldCompositeValue.match(null);
    }

    @Test (expected = IllegalStateException.class)
    public void uninvokedMatch() {
        dataSourceFieldCompositeValue.getMatched();
    }

    @Test
    public void invokedMatch() {
        dataSourceFieldCompositeValue.match(
                new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build()
        );
        dataSourceFieldCompositeValue.getMatched();
    }

    @Test
    public void fieldDefinitions() {
        assertThat(dataSourceFieldCompositeValue.getDataSourceLhsValue().getDataSourceFieldValue(), is(TEST_VALUE));
        assertThat(dataSourceFieldCompositeValue.getDataSourceRhsValue().getDataSourceFieldValue(), is(TEST_VALUE));
    }
}