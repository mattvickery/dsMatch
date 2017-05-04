package com.gds.dsmatch.model.impl;

import com.gds.dsmatch.matching.MatchingStrategyVisitor;
import com.gds.dsmatch.model.DataSourceFieldPairMatchValue;
import com.gds.dsmatch.model.DataSourceFieldValue;

import java.io.Serializable;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 28/04/2017
 */
public class DefaultDataSourceFieldPairMatchValue<T extends Serializable>
        implements DataSourceFieldPairMatchValue <T> {

    private final DataSourceFieldValue<T> lhs;
    private final DataSourceFieldValue<T> rhs;
    private boolean matched = true;
    private boolean matchInvocationCompleted = false;

    public DefaultDataSourceFieldPairMatchValue(final DataSourceFieldValue<T> lhs,
                                                 final DataSourceFieldValue<T> rhs) {

        notNull(lhs, "Mandatory argument 'lhs' is missing.");
        notNull(rhs, "Mandatory argument 'rhs' is missing.");
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public DataSourceFieldValue<T> getDataSourceLhsValue() {
        return lhs;
    }

    @Override
    public DataSourceFieldValue<T> getDataSourceRhsValue() {
        return rhs;
    }

    @Override
    public boolean getMatched() {
        state(matchInvocationCompleted, "Matching has not yet been invoked.");
        return matched;
    }

    @Override
    public boolean match(final MatchingStrategyVisitor matchingStrategyVisitor) {

        notNull(matchingStrategyVisitor, "Mandatory argument 'matchingStrategyVisitor' is missing.");
        matchInvocationCompleted = true;
        if(matchingStrategyVisitor.visit(this))
            return true;
        matched = false;
        return false;
    }
}
