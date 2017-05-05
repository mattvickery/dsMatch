package com.gds.dsmatch.model.type.impl;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinitionTuple;
import com.gds.dsmatch.model.type.MatchKeyDefinition;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DefaultMatchKeyDefinitionTest {

    private final String matchKeyDefinitionName = "simple-match-key-1";
    private final DataSourceFieldDefinition<BigDecimal> lhs
            = new DefaultDataSourceFieldDefinition<BigDecimal>("Rate", BigDecimal.class);
    private final DataSourceFieldDefinition<BigDecimal> rhs
            = new DefaultDataSourceFieldDefinition<BigDecimal>("Source Rate", BigDecimal.class);

    @Test (expected = IllegalArgumentException.class)
    public void constructorNullValue_1() {
        new DefaultMatchKeyDefinition(null);
    }

    @Test (expected = IllegalStateException.class)
    public void constructorNullValue_2() {
        new DefaultMatchKeyDefinition("");
    }

    @Test
    public void simpleSetupAndTest() {

        final MatchKeyDefinition matchKeyDefinition = new DefaultMatchKeyDefinition(matchKeyDefinitionName);
        assertThat(matchKeyDefinition.getMatchKeys().size(), is(0));
        assertThat(matchKeyDefinition.getMatchKeyDefinitionName(), is(matchKeyDefinitionName));
        matchKeyDefinition.addMatchKeyPair(lhs, rhs);

        assertThat(matchKeyDefinition.getMatchKeys().size(), is(1));
        final DataSourceFieldDefinitionTuple tuple = matchKeyDefinition.getMatchKeys().get(0);
        assertThat(tuple.getLhs(), is(lhs));
        assertThat(tuple.getRhs(), is(rhs));
    }
}