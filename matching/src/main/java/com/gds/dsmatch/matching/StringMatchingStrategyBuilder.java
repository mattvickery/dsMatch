package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class StringMatchingStrategyBuilder {

    private final List<Function<DataSourceFieldCompositeValue<String>, Boolean>> matchingStrategies = new ArrayList<>();

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

    public Function<DataSourceFieldCompositeValue<String>, Boolean> build() {
        state(matchingStrategies.size() > 0, "No matching strategies have been configured.");
        return new DefaultMatchingStrategy<>(matchingStrategies);
    }
}