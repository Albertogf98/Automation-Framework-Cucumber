package utils;

public enum Status {

    AVAILABLE  ("available");

    final String STATUS;

    Status(final String STATUS) {
        this.STATUS = STATUS;
    }

    public String getStatusAvailable() {
        return STATUS;
    }
}
