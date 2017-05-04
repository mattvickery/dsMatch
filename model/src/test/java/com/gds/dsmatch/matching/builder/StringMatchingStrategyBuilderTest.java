package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.MatchingStrategyVisitor;
import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.impl.DefaultDataSourceFieldPairMatchValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class StringMatchingStrategyBuilderTest {

    private DataSourceFieldPairMatchValue<String> candidateMatchPair
            = mock(DefaultDataSourceFieldPairMatchValue.class);

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
        final MatchingStrategyVisitor<String> equalsIgnoreCase
                = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build();
        equalsIgnoreCase.visit(candidateMatchPair);
        verify(candidateMatchPair, times(1)).match(any());
    }

    @Test
    public void multipleStrategies() {
        final MatchingStrategyVisitor<String> equalsIgnoreCase
                = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().addStartsWith("x").build();
        equalsIgnoreCase.visit(candidateMatchPair);
        verify(candidateMatchPair, times(2)).match(any());
    }
}