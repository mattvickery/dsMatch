package com.gds.dsmatch.matching;

import com.gds.dsmatch.matching.builder.StringMatchingStrategyBuilder;
import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.impl.DefaultDataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.impl.DefaultDataSourceFieldValue;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.impl.DefaultDataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class StringMatchingStrategyTest {

    private DataSourceFieldDefinition<String> stringFieldDefinition
            = new DefaultDataSourceFieldDefinition<>("fieldA", String.class);

    private MatchingStrategyVisitor<String> equalsMatchingStrategy
            = new StringMatchingStrategyBuilder().addEquals().build();

    private MatchingStrategyVisitor<String> equalsIgnoreCaseMatchingStrategy
            = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().build();

    private MatchingStrategyVisitor<String> equalsIgnoreCaseAndStartsWithMatchingStrategy
            = new StringMatchingStrategyBuilder().addEqualsIgnoreCase().addStartsWith("na").build();

    private MatchingStrategyVisitor<String> startsWithMatchingStrategy
            = new StringMatchingStrategyBuilder().addStartsWith("he").build();

    @Test
    public void equals_identical() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equals_incorrectCase() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "Name")
        );
        candidateMatchPair.match(equalsMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equalsIgnoreCase_identical() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_oneCharIncorrectCase() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "Name")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_allCharIncorrectCase() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "NAME")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCase_differentWord() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "HELLO")
        );
        candidateMatchPair.match(equalsIgnoreCaseMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void equalsIgnoreCaseAndStartsWith_identical() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "name")
        );
        candidateMatchPair.match(equalsIgnoreCaseAndStartsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void equalsIgnoreCaseAndStartsWith_wrongStartsWith() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello")
        );
        candidateMatchPair.match(equalsIgnoreCaseAndStartsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }

    @Test
    public void startsWith_identical() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void startsWith_identicalFirstThreeCharsOnly() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "help")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(true));
    }

    @Test
    public void startsWith_differentWords() {

        final DataSourceFieldPairMatchValue<String> candidateMatchPair
                = new DefaultDataSourceFieldPairMatchValue<>(
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "hello"),
                new DefaultDataSourceFieldValue<>(stringFieldDefinition, "halo")
        );
        candidateMatchPair.match(startsWithMatchingStrategy);
        assertThat(candidateMatchPair.getMatched(), is(false));
    }
}