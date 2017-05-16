package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldValue;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 05/05/2017
 */
public class DecimalMatchingStrategyTest {

    private DataSourceFieldDefinition<BigDecimal> rate
            = new DefaultDataSourceFieldDefinition<>("Rate", BigDecimal.class);

    private Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean> equalsMatchingStrategy
            = new DecimalMatchingStrategyBuilder().addExactEquals().build();

    private Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean> toleranceMatchingStrategy
            = new DecimalMatchingStrategyBuilder()
            .addEqualsWithTolerance(new BigDecimal("0.1")).build();

    @Test
    public void equals_identical() {

        final DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(rate, BigDecimal.ONE),
                new DefaultDataSourceFieldValue<>(rate, BigDecimal.ONE)
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_different() {

        final DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(rate, BigDecimal.ONE),
                new DefaultDataSourceFieldValue<>(rate, BigDecimal.TEN)
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_1() {

        final DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.2")),
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.4"))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_2() {

        final DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.1")),
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.4"))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_3() {

        final DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.3")),
                new DefaultDataSourceFieldValue<>(rate, new BigDecimal("5.4"))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

}