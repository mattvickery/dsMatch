package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.DataSourceRowMatchValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceRowMatchValueTest {

    private final DataSourceRowMatchValue rowMatchValue = new DefaultDataSourceRowMatchValue(0L);
    private final DataSourceFieldDefinition<BigDecimal> rate
            = new DefaultDataSourceFieldDefinition<>("Rate", BigDecimal.class);
    private final DataSourceFieldValue<BigDecimal> lhs = new DefaultDataSourceFieldValue<>(rate, BigDecimal.ONE);
    private final DataSourceFieldValue<BigDecimal> rhs = new DefaultDataSourceFieldValue<>(rate, BigDecimal.TEN);
    private final DataSourceFieldPairMatchValue<BigDecimal> fieldPairMatchValue
            = new DefaultDataSourceFieldPairMatchValue<>(lhs, rhs);

    @Test(expected = IllegalStateException.class)
    public void constructor_1() {
        new DefaultDataSourceRowMatchValue(-1L);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableFieldPairMatchValues_add() {
        rowMatchValue.getDataSourceFieldPairMatchValue().add(fieldPairMatchValue);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableFieldPairMatchValues_remove() {
        rowMatchValue.add(fieldPairMatchValue);
        rowMatchValue.getDataSourceFieldPairMatchValue().remove(0);
    }

    @Test
    public void addRetrieve() {
        assertThat(rowMatchValue.getDataSourceFieldPairMatchValue().size(), is(0));
        rowMatchValue.add(fieldPairMatchValue);
        assertThat(rowMatchValue.getDataSourceFieldPairMatchValue().size(), is(1));
        assertThat(rowMatchValue.getDataSourceFieldPairMatchValue().get(0), is(fieldPairMatchValue));
    }

    @Test
    public void retrieveResultsAnnotation() {
        final String annotation = "sample";
        rowMatchValue.setResultsAnnotation(annotation);
        assertThat(rowMatchValue.getResultsAnnotation(), is(annotation));
    }

    @Test (expected = IllegalArgumentException.class)
    public void setResultsAnnotation_null() {
        rowMatchValue.setResultsAnnotation(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void add_null() {
        rowMatchValue.add(null);
    }
}