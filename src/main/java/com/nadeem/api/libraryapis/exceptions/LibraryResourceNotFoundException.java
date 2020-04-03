package com.nadeem.api.libraryapis.exceptions;

public class LibraryResourceNotFoundException extends Exception{
   private String traceId;;
    public LibraryResourceNotFoundException(String traceId,String message) {
        super(message);
        this.traceId=traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}
