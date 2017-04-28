package com.gds.dsmatch.matching.builder;

import com.gds.dsmatch.matching.MatchingStrategyVisitor;
import com.gds.dsmatch.matching.StringMatchingStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DecimalMatchingStrategyBuilder {

    private final List<MatchingStrategyVisitor<String>> matchingStrategies = new ArrayList<>();

    public DecimalMatchingStrategyBuilder addExactEquals() {
        //..
        return this;
    }

    public DecimalMatchingStrategyBuilder addEqualsWithTolerance(final BigDecimal tolerance) {
        //..
        return this;
    }

    public StringMatchingStrategy build() {
        return new StringMatchingStrategy(matchingStrategies);
    }
}