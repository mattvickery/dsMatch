package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.StringMatchingStrategyBuilder;
import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldCompositeValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class StringMatchingStrategyBuilderTest {

    private DataSourceFieldCompositeValue<String> candidateMatchPair
            = mock(DefaultDataSourceFieldCompositeValue.class);

    @Before
    public void before() {
        Mockito.reset(candidateMatchPair);
    }

    @Test (expected = IllegalStateException.class)
    public void emptyBuilder() {
        new StringMatchingStrategyBuilder().build();
    }

    @Test
    public void singleStrategy() {
        final Function<DataSourceFieldCompositeValue<String>, Boolean> matchFunction
                = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build();
        matchFunction.apply(candidateMatchPair);
        verify(candidateMatchPair, times(1)).match(any());
    }

    @Test
    public void multipleStrategies() {
        final Function<DataSourceFieldCompositeValue<String>, Boolean> matchFunction
                = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().addStartsWith("x").build();
        matchFunction.apply(candidateMatchPair);
        verify(candidateMatchPair, times(2)).match(any());
    }
}