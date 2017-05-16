package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.DataSourceRowCompositeValue;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceRowCompositeValueTest {

    private final DataSourceRowCompositeValue rowMatchValue = new DefaultDataSourceRowCompositeValue(0L);
    private final DataSourceFieldDefinition<BigDecimal> rate
            = new DefaultDataSourceFieldDefinition<>("Rate", BigDecimal.class);
    private final DataSourceFieldValue<BigDecimal> lhs = new DefaultDataSourceFieldValue<>(rate, BigDecimal.ONE);
    private final DataSourceFieldValue<BigDecimal> rhs = new DefaultDataSourceFieldValue<>(rate, BigDecimal.TEN);
    private final DataSourceFieldCompositeValue<BigDecimal> fieldPairMatchValue
            = new DefaultDataSourceFieldCompositeValue<>(lhs, rhs);

    @Test(expected = IllegalStateException.class)
    public void constructor_1() {
        new DefaultDataSourceRowCompositeValue(-1L);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableFieldPairMatchValues_add() {
        rowMatchValue.getDataSourceFieldCompositeValues().add(fieldPairMatchValue);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableFieldPairMatchValues_remove() {
        rowMatchValue.add(fieldPairMatchValue);
        rowMatchValue.getDataSourceFieldCompositeValues().remove(0);
    }

    @Test
    public void addRetrieve() {
        assertThat(rowMatchValue.getDataSourceFieldCompositeValues().size(), is(0));
        rowMatchValue.add(fieldPairMatchValue);
        assertThat(rowMatchValue.getDataSourceFieldCompositeValues().size(), is(1));
        MatcherAssert.assertThat(rowMatchValue.getDataSourceFieldCompositeValues().get(0), is(fieldPairMatchValue));
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