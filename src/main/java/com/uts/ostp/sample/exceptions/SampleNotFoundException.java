package com.uts.ostp.sample.exceptions;

/**
 * Sample exception.
 */
public class SampleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1810670226111021816L;

    /**
     * Constructs exception with the message.
     *
     * @param message exception message.
     */
    public SampleNotFoundException(final String message) {
        super(message);
    }
}
