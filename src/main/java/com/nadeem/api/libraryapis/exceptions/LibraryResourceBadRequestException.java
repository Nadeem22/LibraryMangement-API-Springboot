package com.nadeem.api.libraryapis.exceptions;

public class LibraryResourceBadRequestException extends Exception{
   private String traceId;;
    public LibraryResourceBadRequestException(String traceId, String message) {
        super(message);
        this.traceId=traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
