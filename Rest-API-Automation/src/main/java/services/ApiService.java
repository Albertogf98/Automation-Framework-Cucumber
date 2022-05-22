package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

/**
 * This class is in charge of making the request.
 * */
public class ApiService extends RestAssured {

    public ApiService() { }

    public static Response performRequest(String requestType, String url, Map<String, Object> headers, Object body) {
        RequestSpecification requestSpecification = given();

        if (headers != null)
            requestSpecification = requestSpecification.headers(headers);

        if (body != null) {
            if (body instanceof String)
                requestSpecification = requestSpecification.body(body.toString());
            else if (body instanceof File)
                requestSpecification = requestSpecification.body((File) body);
            else if (body instanceof byte[])
                requestSpecification = requestSpecification.body((byte[]) body);
            else
                requestSpecification = requestSpecification.body(body);
        }

        return switch (requestType.toUpperCase()) {
            case "GET"	    -> requestSpecification.get(url);
            case "POST"	    -> requestSpecification.post(url);
            case "PUT"	    -> requestSpecification.put(url);
            case "DELETE"	-> requestSpecification.delete(url);
            default		    -> null;
        };
    }
}
