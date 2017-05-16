package com.gds.dsmatch.model.pojo;

import com.gds.dsmatch.model.DataSourceFieldValue;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceFieldValueTest {

    private final DataSourceFieldDefinition<Integer> intDefinition
            = new DefaultDataSourceFieldDefinition<>("sampleFieldName", Integer.class);
    private final DataSourceFieldValue<Integer> intFieldValue = new DefaultDataSourceFieldValue<>(
            intDefinition, Integer.MIN_VALUE
    );

    @Test (expected = IllegalArgumentException.class)
    public void constructor_1() {
        new DefaultDataSourceFieldValue<>(null, Integer.class);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_2() {
        new DefaultDataSourceFieldValue<>(intDefinition, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_3() {
        new DefaultDataSourceFieldValue<>(null, null);
    }

    @Test
    public void intValueConfiguration() {
        assertThat(intFieldValue.getDataSourceFieldValue(), is(Integer.MIN_VALUE));
    }

    @Test
    public void intDefinitionConfiguration() {
        assertThat(intFieldValue.getDataSourceFieldDefinition(), is(intDefinition));
    }
}