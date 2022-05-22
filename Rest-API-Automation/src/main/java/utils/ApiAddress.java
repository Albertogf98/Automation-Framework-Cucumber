package utils;

/**
* This enum contains the addresses of the endpoints
* */
public enum ApiAddress {

    API_FIND_BY_STATUS  ("/v2/pet/findByStatus?status="),
    API_V2_PET          ("/v2/pet");

    final String ADDRESS;

    ApiAddress(final String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getApiAddress() {
        return ADDRESS;
    }
}
