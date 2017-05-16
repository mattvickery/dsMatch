package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinitionTuple;
import com.gds.dsmatch.model.type.DataSourceKeyDefinition;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultDataSourceKeyDefinitionTest {

    private final String matchKeyDefinitionName = "simple-match-key-1";
    private final DataSourceFieldDefinition<BigDecimal> lhs
            = new DefaultDataSourceFieldDefinition<BigDecimal>("Rate", BigDecimal.class);
    private final DataSourceFieldDefinition<BigDecimal> rhs
            = new DefaultDataSourceFieldDefinition<BigDecimal>("Source Rate", BigDecimal.class);

    @Test (expected = IllegalArgumentException.class)
    public void constructorNullValue_1() {
        new DefaultDataSourceKeyDefinition(null);
    }

    @Test (expected = IllegalStateException.class)
    public void constructorNullValue_2() {
        new DefaultDataSourceKeyDefinition("");
    }

    @Test
    public void simpleSetupAndTest() {

        final DataSourceKeyDefinition dataSourceKeyDefinition = new DefaultDataSourceKeyDefinition(matchKeyDefinitionName);
        assertThat(dataSourceKeyDefinition.getKeyFieldDefinitions().size(), is(0));
        assertThat(dataSourceKeyDefinition.getKeyDefinitionName(), is(matchKeyDefinitionName));
        dataSourceKeyDefinition.addKeyPair(lhs, rhs);

        assertThat(dataSourceKeyDefinition.getKeyFieldDefinitions().size(), is(1));
        final DataSourceFieldDefinitionTuple tuple = dataSourceKeyDefinition.getKeyFieldDefinitions().get(0);
        assertThat(tuple.getLhs(), is(lhs));
        assertThat(tuple.getRhs(), is(rhs));
    }
}