package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceCompositeDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.model.type.DataSourceKeyDefinition;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceCompositeDefinitionTest {

    private final List<DataSourceKeyDefinition> dataSourceKeyDefinition = new ArrayList<>();
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
        new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinition, lhs, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_1() {
        new DefaultDataSourceCompositeDefinition(null, lhs, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_2() {
        new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinition, null, rhs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_3() {
        new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinition, lhs, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_nullArguments_4() {
        new DefaultDataSourceCompositeDefinition(null, null, null);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void unmodifiableCollection() {
        final DataSourceCompositeDefinition matchingDefinition
                = new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinition, lhs, rhs);
        matchingDefinition.getDataSourceKeyDefinitions().clear();
    }

    @Test
    public void simpleSetupAndTest() {
        final DataSourceCompositeDefinition matchingDefinition
                = new DefaultDataSourceCompositeDefinition(dataSourceKeyDefinition, lhs, rhs);
        assertThat(matchingDefinition.getLhs(), is(lhs));
        assertThat(matchingDefinition.getRhs(), is(rhs));
        assertThat(matchingDefinition.getDataSourceKeyDefinitions().size(), is(0));
    }
}