package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.DecimalMatchingStrategy;
import com.gds.dsmatch.matching.MatchingStrategyVisitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        matchingStrategies.add(dataSourceFieldPairMatchValue -> {
            if ((dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue().compareTo(
                    dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().add(tolerance)
            ) > 0) &&
                    (dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue().compareTo(
                            dataSourceFieldPairMatchValue.getDataSourceLhsValue()
                                    .getDataSourceFieldValue().subtract(tolerance)
                    ) < 0))
                return true;
            return false;
        });
        return this;
    }

    public DecimalMatchingStrategy build() {
        return new DecimalMatchingStrategy(matchingStrategies);
    }
}