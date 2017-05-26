package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldValue;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 26/05/2017
 */
public class BooleanMatchingStrategyTest {

    private DataSourceFieldDefinition<Boolean> located
            = new DefaultDataSourceFieldDefinition<>("Located?", Boolean.class);

    private Function<DataSourceFieldCompositeValue<Boolean>, Boolean> equalsMatchingStrategy
            = new BooleanMatchingStrategyBuilder().addExactEquals().build();

    @Test
    public void equals_identical() {

        final DataSourceFieldCompositeValue<Boolean> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(located, Boolean.TRUE),
                new DefaultDataSourceFieldValue<>(located, Boolean.TRUE)
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_different() {

        final DataSourceFieldCompositeValue<Boolean> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(located, Boolean.TRUE),
                new DefaultDataSourceFieldValue<>(located, Boolean.FALSE)
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }
}