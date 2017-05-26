package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.DecimalMatchingStrategyBuilder;
import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldCompositeValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 26/05/2017
 */
public class DecimalMatchingStrategyBuilderTest {

    private DataSourceFieldCompositeValue<BigDecimal> candidateMatchPair
            = mock(DefaultDataSourceFieldCompositeValue.class);

    @Before
    public void before() {
        Mockito.reset(candidateMatchPair);
    }

    @Test (expected = IllegalStateException.class)
    public void emptyBuilder() {
        new DecimalMatchingStrategyBuilder().build();
    }

    @Test
    public void singleStrategy() {
        final Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean> matchFunction
                = new DecimalMatchingStrategyBuilder().addExactEquals().build();
        matchFunction.apply(candidateMatchPair);
        verify(candidateMatchPair, times(1)).match(any());
    }

    @Test
    public void multipleStrategies() {
        final Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean> matchFunction
                = new DecimalMatchingStrategyBuilder().addExactEquals().addEqualsWithTolerance(BigDecimal.ONE).build();
        matchFunction.apply(candidateMatchPair);
        verify(candidateMatchPair, times(2)).match(any());
    }
}