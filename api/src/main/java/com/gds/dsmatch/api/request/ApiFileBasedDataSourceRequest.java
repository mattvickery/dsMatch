package com.gds.dsmatch.api.request;

import java.io.File;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public interface ApiFileBasedDataSourceRequest extends ApiRequest {
    File getFile();
}