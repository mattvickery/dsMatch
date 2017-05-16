package com.gds.dsmatch.api.transform;

import com.gds.dsmatch.api.ApiException;

import java.io.Serializable;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiTransformer <S extends Serializable, T extends Serializable>{
    T transform(final S source) throws ApiException;
}