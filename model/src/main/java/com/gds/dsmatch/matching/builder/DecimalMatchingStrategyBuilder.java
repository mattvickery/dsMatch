package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.DecimalMatchingStrategy;
import com.gds.dsmatch.matching.MatchingStrategyVisitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DecimalMatchingStrategyBuilder {

    private final List<MatchingStrategyVisitor<BigDecimal>> matchingStrategies = new ArrayList<>();

    public DecimalMatchingStrategyBuilder addExactEquals() {
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
            dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().equals(
                    dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue()));
        return this;
    }

    public DecimalMatchingStrategyBuilder addEqualsWithTolerance(final BigDecimal tolerance) {

        notNull(tolerance, "Mandatory argument 'tolerance' is missing.");
        matchingStrategies.add(dataSourceFieldPairMatchValue -> dataSourceFieldPairMatchValue.getDataSourceRhsValue()
                .getDataSourceFieldValue().abs().subtract(
                    dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().abs()
                ).compareTo(tolerance) <= 0);
        return this;
    }

    public DecimalMatchingStrategy build() {
        return new DecimalMatchingStrategy(matchingStrategies);
    }
}