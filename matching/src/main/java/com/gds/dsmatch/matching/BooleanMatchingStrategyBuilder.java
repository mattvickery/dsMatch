package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class BooleanMatchingStrategyBuilder {

    private final List<Function<DataSourceFieldCompositeValue<Boolean>, Boolean>> matchingStrategies
            = new ArrayList<>();

    public BooleanMatchingStrategyBuilder addExactEquals() {
        matchingStrategies.add(dataSourceFieldPairMatchValue ->
                dataSourceFieldPairMatchValue.getDataSourceLhsValue().getDataSourceFieldValue().equals(
                        dataSourceFieldPairMatchValue.getDataSourceRhsValue().getDataSourceFieldValue()));
        return this;
    }

    public Function<DataSourceFieldCompositeValue<Boolean>, Boolean> build() {
        state(matchingStrategies.size() > 0, "No matching strategies have been configured.");
        return new DefaultMatchingStrategy<>(matchingStrategies);
    }
}