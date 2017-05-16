package com.gds.dsmatch.matching;

import com.gds.dsmatch.model.DataSourceFieldCompositeValue;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultMatchingStrategy<T extends Serializable>
        implements Function<DataSourceFieldCompositeValue<T>, Boolean> {

    private final List<Function<DataSourceFieldCompositeValue<T>, Boolean>> matchingStrategies;

    public DefaultMatchingStrategy(final List<Function<DataSourceFieldCompositeValue<T>, Boolean>> matchingStrategies) {
        notNull(matchingStrategies, "Mandatory argument 'matchingStrategies' is missing.");
        this.matchingStrategies = matchingStrategies;
    }

    @Override
    public Boolean apply(final DataSourceFieldCompositeValue<T> dataSourceFieldCompositeValue) {

        notNull(dataSourceFieldCompositeValue, "Mandatory argument 'dataSourceFieldPairMatchValue' is missing.");
        final List<Function<DataSourceFieldCompositeValue<T>, Boolean>> failures = matchingStrategies.stream()
                .filter(dataSourceFieldCompositeValue::match)
                .collect(Collectors.toList());
        return failures.size() > 0;
    }

}