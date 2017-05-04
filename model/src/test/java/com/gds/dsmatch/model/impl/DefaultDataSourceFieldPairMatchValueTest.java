package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.matching.builder.StringMatchingStrategyBuilder;
import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceFieldPairMatchValueTest {

    public static final String FILE_NAME = "fileName";
    public static final String TEST_VALUE = "testValue";

    private final DataSourceFieldPairMatchValue<String> dataSourceFieldPairMatchValue
            = new DefaultDataSourceFieldPairMatchValue<>(
            new DefaultDataSourceFieldValue<>(
                    new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE),
            new DefaultDataSourceFieldValue<>(
                    new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE)
    );

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_1() {
        new DefaultDataSourceFieldPairMatchValue<String>(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_2() {
        new DefaultDataSourceFieldPairMatchValue<String>(new DefaultDataSourceFieldValue<>(
                new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE),
                null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorArguments_3() {
        new DefaultDataSourceFieldPairMatchValue<String>(null,
                new DefaultDataSourceFieldValue<>(
                        new DefaultDataSourceFieldDefinition<>(FILE_NAME, String.class), TEST_VALUE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void nullMatchingStrategy() {
        dataSourceFieldPairMatchValue.match(null);
    }

    @Test (expected = IllegalStateException.class)
    public void uninvokedMatch() {
        dataSourceFieldPairMatchValue.getMatched();
    }

    @Test
    public void invokedMatch() {
        dataSourceFieldPairMatchValue.match(
                new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build()
        );
        dataSourceFieldPairMatchValue.getMatched();
    }

    @Test
    public void fieldDefinitions() {
        assertThat(dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue(), is(TEST_VALUE));
        assertThat(dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue(), is(TEST_VALUE));
    }
}