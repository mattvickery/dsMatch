package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DecimalMatchingStrategyBuilder {

    private final List<Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean>> matchingStrategies
            = new ArrayList<>();

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

    public Function<DataSourceFieldCompositeValue<BigDecimal>, Boolean> build() {
        state(matchingStrategies.size() > 0, "No matching strategies have been configured.");
        return new DefaultMatchingStrategy<>(matchingStrategies);
    }
}