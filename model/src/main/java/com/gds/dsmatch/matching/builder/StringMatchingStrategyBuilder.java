package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.MatchingStrategyVisitor;
import com.gds.dsmatch.matching.StringMatchingStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class StringMatchingStrategyBuilder {

    private final List<MatchingStrategyVisitor<String>> matchingStrategies = new ArrayList<>();

    public StringMatchingStrategyBuilder addEquals() {
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
            dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().equals(
                    dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue()
            ));
        return this;
    }

    public StringMatchingStrategyBuilder addEqualsIgnoreCase() {
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
                dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().equalsIgnoreCase(
                        dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue()
                ));
        return this;
    }

    public StringMatchingStrategyBuilder addStartsWith(final String token) {
        notNull(token, "Mandatory argument 'token' is missing.");
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
                dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().startsWith(token) &&
                dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue().startsWith(token));
        return this;
    }

    public StringMatchingStrategy build() {
        return new StringMatchingStrategy(matchingStrategies);
    }
}