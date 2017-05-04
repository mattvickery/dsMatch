package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DecimalMatchingStrategy implements MatchingStrategyVisitor<BigDecimal> {

    private final List<MatchingStrategyVisitor<BigDecimal>> matchingStrategies;

    public DecimalMatchingStrategy(final List<MatchingStrategyVisitor<BigDecimal>> matchingStrategies) {
        this.matchingStrategies = matchingStrategies;
    }

    @Override
    public boolean visit(final DataSourceFieldPairMatchValue<BigDecimal> dataSourceFieldPairMatchValue) {

        notNull(dataSourceFieldPairMatchValue, "Mandatory argument 'dataSourceFieldPairMatchValue' is missing.");
        final List<MatchingStrategyVisitor<BigDecimal>> failures = matchingStrategies.stream()
                .filter(dataSourceFieldPairMatchValue::match)
                .collect(Collectors.toList());
        return failures.size() > 0;
    }
}