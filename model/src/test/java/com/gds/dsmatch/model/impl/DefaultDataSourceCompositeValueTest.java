package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceCompositeValue;
import com.gds.dsmatch.model.DataSourceRowCompositeValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceCompositeDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.DataSourceKeyDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceCompositeDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceRowDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceKeyDefinition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceCompositeValueTest {

    private final DataSourceFieldDefinition<String> name
            = new DefaultDataSourceFieldDefinition<>("Name", String.class);
    private final DataSourceFieldDefinition<String> type
            = new DefaultDataSourceFieldDefinition<>("Type", String.class);
    private final DataSourceKeyDefinition dataSourceKeyDefinition = new DefaultDataSourceKeyDefinition("type-a");
    {
        dataSourceKeyDefinition.addKeyPair(name,name);
    }
    private final List<DataSourceKeyDefinition> dataSourceKeyDefinitions = new ArrayList<DataSourceKeyDefinition>() {{
        add(dataSourceKeyDefinition);
    }};
    private final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions
            = new ArrayList<DataSourceFieldDefinition<?>>(){{
        add(name);
        add(type);
    }};
    private final DataSourceRowDefinition lhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);
    private final DataSourceRowDefinition rhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);
    private final DataSourceCompositeDefinition dataSourceCompositeDefinition
            = new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinitions, lhs, rhs);
    private final DataSourceCompositeValue dataSourceCompositeValue
            = new DefaultDataSourceCompositeValue(dataSourceCompositeDefinition);

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullValue() {
        new DefaultDataSourceCompositeValue(null);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiable() {
        dataSourceCompositeValue.getDataSourceRowCompositeValues().clear();
    }

    @Test
    public void simpleSetupAndTest() {
        assertThat(dataSourceCompositeValue.getDataSourceCompositeDefinition(), is(dataSourceCompositeDefinition));
        final DataSourceRowCompositeValue dataSourceRowCompositeValue = new DefaultDataSourceRowCompositeValue(1L);
        dataSourceCompositeValue.add(dataSourceRowCompositeValue);
        assertThat(dataSourceCompositeValue.getDataSourceCompositeDefinition(), is(dataSourceCompositeDefinition));
        assertThat(dataSourceCompositeValue.getDataSourceRowCompositeValues().get(0), is (dataSourceRowCompositeValue));
    }

    @Test (expected = IllegalArgumentException.class)
    public void add_nullArgument() {
        dataSourceCompositeValue.add(null);
    }
}