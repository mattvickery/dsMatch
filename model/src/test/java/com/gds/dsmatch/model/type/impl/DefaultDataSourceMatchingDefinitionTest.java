package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceMatchingDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.MatchKeyDefinition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceMatchingDefinitionTest {

    private final List<MatchKeyDefinition> matchKeyDefinition = new ArrayList<>();
    private final DataSourceFieldDefinition<String> name
            = new DefaultDataSourceFieldDefinition<>("Name", String.class);
    private final List<DataSourceFieldDefinition<?>> dataSourceFieldDefinitions
            = new ArrayList<DataSourceFieldDefinition<?>>() {{
       add(name);
    }};
    private final DataSourceRowDefinition lhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);
    private final DataSourceRowDefinition rhs = new DefaultDataSourceRowDefinition(dataSourceFieldDefinitions);

    @Test
    public void constructorNonNullArguments() {
        new DefaultDataSourceMatchingDefinition(matchKeyDefinition, lhs, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_1() {
        new DefaultDataSourceMatchingDefinition(null, lhs, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_2() {
        new DefaultDataSourceMatchingDefinition(matchKeyDefinition, null, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_3() {
        new DefaultDataSourceMatchingDefinition(matchKeyDefinition, lhs, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_4() {
        new DefaultDataSourceMatchingDefinition(null, null, null);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableCollection() {
        final DataSourceMatchingDefinition matchingDefinition
                = new DefaultDataSourceMatchingDefinition(matchKeyDefinition, lhs, rhs);
        matchingDefinition.getMatchKeyDefinitions().clear();
    }

    @Test
    public void simpleSetupAndTest() {
        final DataSourceMatchingDefinition matchingDefinition
                = new DefaultDataSourceMatchingDefinition(matchKeyDefinition, lhs, rhs);
        assertThat(matchingDefinition.getLhs(), is(lhs));
        assertThat(matchingDefinition.getRhs(), is(rhs));
        assertThat(matchingDefinition.getMatchKeyDefinitions().size(), is(0));
    }
}