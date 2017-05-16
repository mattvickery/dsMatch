package com.gds.dsmatch.api;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ApiException extends Exception {

    private final String serviceName;

    public ApiException(final String serviceName, final String message) {
        super(message);
        notNull(serviceName, "Mandtory argument 'serviceName' is missing.");
        this.serviceName = serviceName;
    }

    public ApiException(final String serviceName, final String message, final Throwable cause) {
        super(message, cause);
        notNull(serviceName, "Mandtory argument 'serviceName' is missing.");
        this.serviceName = serviceName;
    }

    @Override
    public String getMessage() {
        return new StringBuilder().append(serviceName).append(':').append(getMessage()).toString();
    }
}