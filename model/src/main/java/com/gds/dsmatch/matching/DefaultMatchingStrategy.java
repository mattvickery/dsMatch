package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultMatchingStrategy<T extends Serializable> implements MatchingStrategyVisitor<T> {

    private final List<MatchingStrategyVisitor<T>> matchingStrategies;

    public DefaultMatchingStrategy(final List<MatchingStrategyVisitor<T>> matchingStrategies) {
        notNull(matchingStrategies, "Mandatory argument 'matchingStrategies' is missing.");
        this.matchingStrategies = matchingStrategies;
    }

    @Override
    public boolean visit(final DataSourceFieldCompositeValue<T> dataSourceFieldCompositeValue) {

        notNull(dataSourceFieldCompositeValue, "Mandatory argument 'dataSourceFieldPairMatchValue' is missing.");
        final List<MatchingStrategyVisitor<T>> failures = matchingStrategies.stream()
                .filter(dataSourceFieldCompositeValue::match)
                .collect(Collectors.toList());
        return failures.size() > 0;
    }
}