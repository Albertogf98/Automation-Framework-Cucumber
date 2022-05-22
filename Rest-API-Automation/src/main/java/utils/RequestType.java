package utils;

/**
 * This enum contains the types of the request
 * */

public enum RequestType {

    GET     ("GET"),
    POST    ("POST"),
    PUT     ("PUT"),
    DELETE  ("DELETE");

    final String REQUEST;

    RequestType(final String ACTION) {
        this.REQUEST = ACTION;
    }

    public String getRequestType() {
        return REQUEST;
    }
}
