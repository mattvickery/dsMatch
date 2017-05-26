package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldCompositeValue;
import com.gds.dsmatch.model.pojo.DefaultDataSourceFieldValue;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import java.time.LocalDate;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 26/05/2017
 */
public class DateMatchingStrategyBuilderTest {

    private static final int DAY = 1;
    private static final int MONTH = 5;
    private static final int YEAR = 2018;
    private static final LocalDate START_DATE = LocalDate.of(YEAR, MONTH, DAY);
    private DataSourceFieldDefinition<LocalDate> startDate
            = new DefaultDataSourceFieldDefinition<>("StartDate", LocalDate.class);

    private Function<DataSourceFieldCompositeValue<LocalDate>, Boolean> equalsMatchingStrategy
            = new DateMatchingStrategyBuilder().addExactEquals().build();

    private Function<DataSourceFieldCompositeValue<LocalDate>, Boolean> toleranceMatchingStrategy
            = new DateMatchingStrategyBuilder()
            .addEqualsWithTolerance(2).build();

    @Test
    public void equals_identical() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE)
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_different() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.minusDays(1L))
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_1() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.minusDays(10L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_2() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.plusDays(10L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_3() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.plusDays(1L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_tolerance_4() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.minusDays(1L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_tolerance_5() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.minusDays(2L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_tolerance_6() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.minusDays(3L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equals_tolerance_7() {

        final DataSourceFieldCompositeValue<LocalDate> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(startDate, START_DATE),
                new DefaultDataSourceFieldValue<>(startDate, START_DATE.plusDays(3L))
        );
        candidateMatchPair.match(toleranceMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }
}