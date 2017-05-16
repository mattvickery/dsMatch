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
 * @since 04/05/2017
 */
public class StringMatchingStrategyTest {

    private DataSourceFieldDefinition<String> stringFieldDefinition
            = new DefaultDataSourceFieldDefinition<>("fieldA", String.class);

    private Function<DataSourceFieldCompositeValue<String>, Boolean> equalsMatchingStrategy
            = new StringMatchingStrategyBuilder().addEquals().build();

    private Function<DataSourceFieldCompositeValue<String>, Boolean> equalsIgnoreCaseMatchingStrategy
            = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build();

    private Function<DataSourceFieldCompositeValue<String>, Boolean> equalsIgnoreCaseAndStartsWithMatchingStrategy
            = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().addStartsWith("na").build();

    private Function<DataSourceFieldCompositeValue<String>, Boolean> startsWithMatchingStrategy
            = new StringMatchingStrategyBuilder().addStartsWith("he").build();

    @Test
    public void equals_identical() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_incorrectCase() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "Name")
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equalsIgnoreCase_identical() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_oneCharIncorrectCase() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "Name")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_allCharIncorrectCase() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "NAME")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_differentWord() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "HELLO")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equalsIgnoreCaseAndStartsWith_identical() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsIgnoreCaseAndStartsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCaseAndStartsWith_wrongStartsWith() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello")
        );
        candidateMatchPair.match(equalsIgnoreCaseAndStartsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void startsWith_identical() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void startsWith_identicalFirstThreeCharsOnly() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "help")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void startsWith_differentWords() {

        final DataSourceFieldCompositeValue<String> candidateMatchPair
                = new DefaultDataSourceFieldCompositeValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "halo")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }
}