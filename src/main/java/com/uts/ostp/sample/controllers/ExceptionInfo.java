package com.uts.ostp.sample.controllers;

/**
 * Sample exception info holder for any REST exceptions.
 */
public class ExceptionInfo {
    private String url;
    private String message;

    public ExceptionInfo() {
        // Required by `jackson`
    }

    public ExceptionInfo(String url, Exception message) {
        this.url = url;
        this.message = message.getLocalizedMessage();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
