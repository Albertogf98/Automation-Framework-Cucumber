package utils;

public enum Webs {

    WEB_BASE_PET_STORE  ("https://petstore.swagger.io");

    final String URL;

    Webs(final String URL) {
        this.URL = URL;
    }

    public String getWeb() {
        return URL;
    }

}
