package utils;

public enum ApiAddress {

    API_FIND_BY_STATUS  ("/v2/pet/findByStatus"),
    API_V2_PET          ("/v2/pet");

    final String ADDRESS;

    ApiAddress(final String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getApiAddress() {
        return ADDRESS;
    }
}
