package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.DataSourceRowValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceRowValueTest {

    private final DataSourceRowValue dataSourceRowValue
            = new DefaultDataSourceRowValue();
    private final DataSourceFieldDefinition<Long> rate
            = new DefaultDataSourceFieldDefinition<>("Amount", Long.class);
    private final DataSourceFieldValue<Long> dataSourceFieldValue
            = new DefaultDataSourceFieldValue<>(rate, Long.MIN_VALUE);

    @Test
    public void emptyDataSourceFieldValues() {
        assertThat(dataSourceRowValue.getDataSourceFieldValues().size(), is(0));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiable() {
        dataSourceRowValue.getDataSourceFieldValues().add(dataSourceFieldValue);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNullFieldValue() {
        dataSourceRowValue.add(null);
    }

    @Test
    public void addFieldValue() {
        dataSourceRowValue.add(dataSourceFieldValue);
        assertThat(dataSourceRowValue.getDataSourceFieldValues().size(), is(1));
    }
}