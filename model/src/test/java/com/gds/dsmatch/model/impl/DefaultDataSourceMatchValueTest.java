package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.model.DataSourceMatchValue;
import com.gds.dsmatch.model.DataSourceRowMatchValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceMatchingDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.MatchKeyDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceMatchingDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceRowDefinition;
import com.gds.dsmatch.model.type.impl.DefaultMatchKeyDefinition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DefaultDataSourceMatchValueTest {

    private final DataSourceFieldDefinition<String> name
            = new DefaultDataSourceFieldDefinition<>("Name", String.class);
    private final DataSourceFieldDefinition<String> type
            = new DefaultDataSourceFieldDefinition<>("Type", String.class);
    private final MatchKeyDefinition matchKeyDefinition = new DefaultMatchKeyDefinition("type-a");
    {
        matchKeyDefinition.addMatchKeyPair(name,name);
    }
    private final List<MatchKeyDefinition> matchKeyDefinitions = new ArrayList<MatchKeyDefinition>() {{
        add(matchKeyDefinition);
    }};
    private final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions
            = new ArrayList<DataSourceFieldDefinition<?>>(){{
        add(name);
        add(type);
    }};
    private final DataSourceRowDefinition lhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);
    private final DataSourceRowDefinition rhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);
    private final DataSourceMatchingDefinition dataSourceMatchingDefinition
            = new DefaultDataSourceMatchingDefinition(matchKeyDefinitions, lhs, rhs);
    private final DataSourceMatchValue dataSourceMatchValue
            = new DefaultDataSourceMatchValue(dataSourceMatchingDefinition);

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullValue() {
        new DefaultDataSourceMatchValue(null);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiable() {
        dataSourceMatchValue.getDataSOurceRowMatchValues().clear();
    }

    @Test
    public void simpleSetupAndTest() {
        assertThat(dataSourceMatchValue.getDataSourceMatchingDefinition(), is(dataSourceMatchingDefinition));
        final DataSourceRowMatchValue dataSourceRowMatchValue = new DefaultDataSourceRowMatchValue(1L);
        dataSourceMatchValue.add(dataSourceRowMatchValue);
        assertThat(dataSourceMatchValue.getDataSourceMatchingDefinition(), is(dataSourceMatchingDefinition));
        assertThat(dataSourceMatchValue.getDataSOurceRowMatchValues().get(0), is (dataSourceRowMatchValue));
    }

    @Test (expected = IllegalArgumentException.class)
    public void add_nullArgument() {
        dataSourceMatchValue.add(null);
    }
}