package utils;

public enum ApiActions {

    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE");

    final String ACTION;

    ApiActions(final String ACTION) {
        this.ACTION = ACTION;
    }

    public String getApiAction() {
        return ACTION;
    }

}
