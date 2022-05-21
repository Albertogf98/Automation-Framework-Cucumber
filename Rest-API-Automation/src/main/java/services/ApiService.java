package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class ApiService extends RestAssured {

    public ApiService() { }
    /*
    private static Response doGetRequest(String baseEndpoint, String apiAddress, Map<String, String> headers) {
        RequestSpecification requestSpecification = given();

        if (!headers.isEmpty())
            requestSpecification.headers(headers);

        return requestSpecification
                .when()
                .get(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response();
    }

    private static Response doPostRequest(String baseEndpoint, String apiAddress, Map<String, String> headers, String body) {
        RequestSpecification requestSpecification = given();

        if (!headers.isEmpty())
            requestSpecification.headers(headers);

        return body != null ? requestSpecification
                .when()
                .body(body)
                .post(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response() : requestSpecification
                .when()
                .post(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response();
    }

    private static Response doPutRequest(String baseEndpoint, String apiAddress, Map<String, String> headers, String body) {
        RequestSpecification requestSpecification = given();

        if (!headers.isEmpty())
            requestSpecification.headers(headers);

        return body != null ? requestSpecification
                .when()
                .body(body)
                .put(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response() : requestSpecification
                .when()
                .put(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response();
    }

    private static Response doDeleteRequest(String baseEndpoint, String apiAddress, Map<String, String> headers, String body) {
        RequestSpecification requestSpecification = given();

        if (!headers.isEmpty())
            requestSpecification.headers(headers);

        return body != null ? requestSpecification
                .when()
                .body(body)
                .delete(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response() : requestSpecification
                .when()
                .delete(baseEndpoint + "/" + apiAddress)
                .then()
                .extract()
                .response();
    }

    public static Response getResponse(String action, String baseEndpoint, String apiAddress, Map<String, String> headers, String body) {
        RequestSpecification requestSpecification = given();

        if (!headers.isEmpty())
            requestSpecification.headers(headers);

        Response response = switch (action.toUpperCase()) {
            case "GET"      ->      doGetRequest(baseEndpoint, apiAddress, headers);
            case "POST"     ->      doPostRequest(baseEndpoint, apiAddress, headers, body);
            case "PUT"      ->      doPutRequest(baseEndpoint, apiAddress, headers, body);
            case "DELETE"   ->      doDeleteRequest(baseEndpoint, apiAddress, headers, body);
            default         -> {
                //TODO: lanzar excepción report
                yield null;
            }
        };

        return response;
    }
     */

    public Response performRequest(String requestType, String url, Map<String, Object> headers, Object body) {
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
    /*
    public static void main(String[] args) {

       //Get "available" pets. Assert expected result
        Map<String, String> headersGET = new HashMap<String, String>();
        headersGET.put("accept", "application/json");
        Response responseGet = getResponse(
                ApiActions.GET.getApiAction(),
                Webs.WEB_BASE_PET_STORE.getWeb(),
                Webs.API_FIND_BY_STATUS.getWeb() + "?status=available",
                headersGET, ""
        );



        //Post a new available pet to the store. Assert new pet added.
        Map<String, String> headerspost = new HashMap<String, String>();
        headerspost.put("accept", "application/json");
        headerspost.put("Content-Type", "application/json");
        String json         = ReadJson.readJsonFile(Constants.PATH_JSON_POST);
        Pet pet             = new Pet("Manchita", 3745, "string", Status.AVAILABLE.getStatusAvailable());
        Category category   = new Category("Rabbit", 14);
        Tag tag             = new Tag("#Happy", 16);
        String jsonPost = ReadJson.setPostJsonValue(json, pet, category, tag);
        Response responsePOST = getResponse(ApiActions.POST.getApiAction(),
                WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb(),
                headerspost,
                jsonPost
        );
        String postID = responsePOST.jsonPath().getString("id");
        //. Assert new pet added.
        Map<String, String> headersGetPet = new HashMap<String, String>();
        headersGET.put("accept", "application/json");
        Response responseGetPet = getResponse(
                ApiActions.GET.getApiAction(),
                Webs.WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb() + "/" + postID,
                headersGetPet,
                null
        );
       // System.out.println(responseGetPet.body().jsonPath().getString("id").equals(postID));


        //Update this pet status to "sold". Assert status updated
        Map<String, String> headersput = new HashMap<String, String>();
        headersput.put("accept", "application/json");
        headersput.put("Content-Type", "application/json");
        Pet petBeforeUpdate = new Pet("Coke", 3745, "string",
                Status.AVAILABLE.getStatusAvailable());
        String jsonPut = ReadJson.setPostJsonValue(
                ReadJson.readJsonFile(Constants.PATH_JSON_POST),
                petBeforeUpdate,
                new Category("Idiota", 14),
                new Tag("#Mosqueado", 16)
        );
        Response responsePUT = getResponse(ApiActions.PUT.getApiAction(),
                WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb(),
                headersput,
                jsonPut
        );

        //. Assert status updated
        Map<String, String> headersGetPetPut = new HashMap<String, String>();
        headersGetPetPut.put("accept", "application/json");
        Response responseGetPetPut = getResponse(
                ApiActions.GET.getApiAction(),
                Webs.WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb() + "/" + responsePUT.getBody().jsonPath().getString("id"),
                headersGetPetPut,
                null
        );

        // Delete this pet. Assert deletion.
        Map<String, String> headersdel = new HashMap<String, String>();
        headersdel.put("accept", "application/json");
        Pet petBeforeDelete = new Pet("Coke", 3745, "string", Status.AVAILABLE.getStatusAvailable());

        Response responseDel = getResponse(ApiActions.DELETE.getApiAction(),
                WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb() + "/" + petBeforeDelete.getId(),
                headersdel,
                null
        );

        //. Assert status updated
        Map<String, String> headersGetPetDel = new HashMap<String, String>();
        headersGetPetDel.put("accept", "application/json");
        Response responseGetPetDel = getResponse(
                ApiActions.GET.getApiAction(),
                Webs.WEB_BASE_PET_STORE.getWeb(),
                Webs.API_V2_PET.getWeb() + "/" + responseDel.getBody().jsonPath().getString("message"),
                headersGetPetDel,
                null
        );
        responseGetPetDel.getBody().prettyPrint();
    }
     */
}
