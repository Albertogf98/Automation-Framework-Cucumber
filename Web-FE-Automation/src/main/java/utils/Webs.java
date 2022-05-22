package utils;

/**
 *This class contains the URLs of the websites
 * */

public enum Webs {

    WEB_BLAZE_HOME ("https://www.demoblaze.com/index.html");

    final String URL;

    Webs(final String URL) {
        this.URL = URL;
    }

    public String getUrl() {
        return URL;
    }
}
