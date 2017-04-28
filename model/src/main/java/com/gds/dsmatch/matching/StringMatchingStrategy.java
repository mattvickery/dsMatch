package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class StringMatchingStrategy implements MatchingStrategyVisitor<String> {

    private final List<MatchingStrategyVisitor<String>> matchingStrategies;

    public StringMatchingStrategy(final List<MatchingStrategyVisitor<String>> matchingStrategies) {
        this.matchingStrategies = matchingStrategies;
    }

    @Override
    public boolean visit(final DataSourceFieldPairMatchValue<String> dataSourceFieldPairMatchValue) {

        notNull(dataSourceFieldPairMatchValue, "Mandatory argument 'dataSourceFieldPairMatchValue' is missing.");
        final List<MatchingStrategyVisitor<String>> failures = matchingStrategies.stream().filter(
                dataSourceFieldPairMatchValue::match).collect(Collectors.toList());
        return failures.size() > 0;
    }
}