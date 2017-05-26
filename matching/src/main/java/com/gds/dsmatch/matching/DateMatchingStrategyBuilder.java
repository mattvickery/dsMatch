package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DateMatchingStrategyBuilder {

    private final List<Function<DataSourceFieldCompositeValue<LocalDate>, Boolean>> matchingStrategies
            = new ArrayList<>();

    public DateMatchingStrategyBuilder addExactEquals() {
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
                dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().equals(
                        dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue()));
        return this;
    }

    public DateMatchingStrategyBuilder addEqualsWithTolerance(final int daysTolerance) {

        matchingStrategies.add(dataSourceFieldPairMatchValue -> {
            final LocalDate lhsValue = dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue();
            final LocalDate rhsValue = dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue();
            if ((lhsValue.isBefore(rhsValue.plusDays(daysTolerance+1)))
                    && (lhsValue.isAfter(rhsValue.minusDays(daysTolerance+1))))
                return Boolean.TRUE;
            return Boolean.FALSE;
        });
        return this;
    }

    public Function<DataSourceFieldCompositeValue<LocalDate>, Boolean> build() {
        state(matchingStrategies.size() > 0, "No matching strategies have been configured.");
        return new DefaultMatchingStrategy<>(matchingStrategies);
    }
}