package com.gds.dsmatch.model.pojo.type;

import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 04/05/2017
 */
public class DataSourceFieldDefinitionCacheTest {

    private final DataSourceFieldDefinitionCache cache = new DataSourceFieldDefinitionCache();
    private final String fieldName = "sampleFieldName";
    private final DataSourceFieldDefinition<Integer> integerDefinition
            = new DefaultDataSourceFieldDefinition<>(fieldName, Integer.class);
    private final DataSourceFieldDefinition<String> stringDefinition
            = new DefaultDataSourceFieldDefinition<>(fieldName, String.class);

    @Test (expected = IllegalArgumentException.class)
    public void getFieldDefinitionNullArgument_1() {
        cache.getFieldDefinition(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getFieldDefinitionNullArgument_2() {
        cache.getFieldDefinition(fieldName, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getFieldDefinitionNullArgument_3() {
        cache.getFieldDefinition(null, String.class);
    }

    @Test (expected = IllegalArgumentException.class)
    public void add_nullArgument() {
        cache.add(null);
    }

    @Test
    public void missingFieldDefinition() {

        assertThat(cache.getFieldDefinition(fieldName, String.class).isPresent(), is(false));
        assertThat(cache.getFieldDefinition(fieldName, Integer.class).isPresent(), is(false));
        cache.add(stringDefinition);
        assertThat(cache.getFieldDefinition(fieldName, String.class).isPresent(), is(true));
        assertThat(cache.getFieldDefinition(fieldName, Integer.class).isPresent(), is(false));
    }

    @Test
    public void identicalFieldsDifferentType() {

        assertThat(cache.getFieldDefinition(fieldName, String.class).isPresent(), is(false));
        assertThat(cache.getFieldDefinition(fieldName, Integer.class).isPresent(), is(false));
        cache.add(stringDefinition);
        cache.add(integerDefinition);
        assertThat(cache.getFieldDefinition(fieldName, String.class).isPresent(), is(true));
        assertThat(cache.getFieldDefinition(fieldName, Integer.class).isPresent(), is(true));
    }
}