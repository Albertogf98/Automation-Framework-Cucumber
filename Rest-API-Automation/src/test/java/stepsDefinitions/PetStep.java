package stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import managers.ScenarioManager;
import models.Category;
import models.Pet;
import models.Tag;
import readers.ReadJson;
import services.ApiService;
import services.TestAssertService;
import utils.CommonsFunctions;
import utils.Constants;
import utils.ApiAddress;
import utils.RequestType;
import utils.Webs;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PetStep {

    private Map<String, Object> headers;
    private Response response;
    private JsonPath jsonPath;

    private String json;
    private int id, resposeDeletePetId;

    private Pet pet;
    private Category category;
    private Tag tag;

    public PetStep() { }

    @Given("^Values to do the requests$")
    public void valuesToDoTheRequests() {
        headers   = new HashMap<String, Object>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");

        json      = ReadJson.readJsonFile(Constants.PATH_JSON_POST);
        id        = CommonsFunctions.generateRandomNumber.apply(10000, 10000);
        pet       = new Pet(Constants.PET_NAME, id, "string", "");
        category  = new Category(Constants.CATEGORY_NAME, id);
        tag       = new Tag(Constants.TAG_NAME, id);
    }

    @When("^I do a Get to (.*?) pets$")
    public void iDoAGetToAvailablePets(String available) {
        response = ApiService.performRequest(
                RequestType.GET.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_FIND_BY_STATUS.getApiAddress() + available,
                headers,
                null
        );
        jsonPath = response.getBody().jsonPath();

        ScenarioManager.writeLogInfo(response.getBody().asString());
    }

    @Then("^I should see a (\\d{3}) status code in the get response$")
    public void iShouldSeeAStatusCodeInTheGetResponse(int statusCode) {
        TestAssertService.checkEquals(response.statusCode(), statusCode, "Status code");
    }

    @And("^I should see a body that is not empty in the get response$")
    public void iShouldSeeABodyThatIsNotEmptyInTheGetResponse() {
        TestAssertService.checkTrue(!response.getBody().asString().isEmpty(), "The body is not empty");
    }

    @And("^I should see contains values (.*?) as number$")
    public void iShouldSeeContainsValueIdAsNumber(String key) {
        String body = jsonPath
                .getString(key)
                .replaceAll(Constants.REGEX_CORCHETTES, "");

        boolean allAreNumbers = Arrays
                .stream(body.split(","))
                .allMatch(id -> id.trim().matches(Constants.REGEX_INTEGER_NUMBER));

        TestAssertService.checkTrue(allAreNumbers, "All " + key + "s" + " are number values");
    }

    @And("^I should see contains values (.*?) as string$")
    public void iShouldSeeContainsValuesNameAsString(String key) {
        String body = jsonPath
                .getString(key)
                .replaceAll(Constants.REGEX_CORCHETTES, "");

        boolean allAreStrings = Arrays
                .stream(body.split(","))
                .allMatch(value -> value instanceof String);

        TestAssertService.checkTrue(allAreStrings, "All " + key + "s" + " are String values");
    }

    @And("^I should see contains available (.*?)$")
    public void iShouldSeeContainsAvailableStatus(String key) {
        String body = jsonPath
                .getString(key)
                .replaceAll(Constants.REGEX_CORCHETTES, "");

        boolean allAreAvailable = Arrays
                .stream(body.split(","))
                .allMatch(value -> value.trim().equalsIgnoreCase("available"));
        TestAssertService.checkTrue(allAreAvailable, "All " + key + "s" + " are available values");

    }

    @When("^I do a Post to add new (.*?) pet$")
    public void iDoAPostToAddNewAvailablePet(String available) {
        pet.setStatus(available);

        response = ApiService.performRequest(
                RequestType.POST.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress(),
                headers,
                ReadJson.setJsonValueInBodyPet(json, pet, category, tag)
        );
        ScenarioManager.writeLogInfo(response.getBody().asString());
    }

    @Then("^I should see a (\\d{3}) status code in the post response$")
    public void iShouldSeeAStatusCodeInThePostResponse(int statusCode) {
        TestAssertService.checkEquals(response.statusCode(), statusCode, "Status code 200 OK");
    }

    @And("^I should see a body that is not empty in the post response$")
    public void iShouldSeeABodyThatIsNotEmptyInThePostResponse() {
        TestAssertService.checkTrue(!response.getBody().asString().isEmpty(), "The body is not empty");
    }

    @And("^I verify that the pet has been added correctly$")
    public void iVerifyThatThePetHasBeenAddedCorrectly() {
        int petID = Integer.parseInt(response.jsonPath().getString("id"));
        TestAssertService.checkEquals(pet.getId(), petID, "Pet ID");

        Response response = ApiService.performRequest(
                RequestType.GET.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress() + "/" + petID,
                headers,
                null
        );

        TestAssertService.checkEquals(response.statusCode(), HttpURLConnection.HTTP_OK, "Status code");
        TestAssertService.checkTrue(!response.getBody().asString().isEmpty(), "The body is not empty");

        TestAssertService.checkEquals(pet.getId(), petID, "Pet ID");
        TestAssertService.checkEquals(Integer.parseInt(response.body().jsonPath().getString("id")), petID,
                "Pet ID");
        ScenarioManager.writeLogInfo(response.getBody().asString());
    }

    @When("^I do a Put to update the status of the pet to (.*?)$")
    public void iDoAPutToUpdateTheStatusOfThePetToSold(String sold) {
        pet.setStatus(sold);
        response = ApiService.performRequest(
                RequestType.PUT.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress(),
                headers,
                ReadJson.setJsonValueInBodyPet(json, pet, category, tag)
        );

        ScenarioManager.writeLogInfo(response.getBody().asString());
    }

    @Then("^I should see a (\\d{3}) status code in the put response$")
    public void iShouldSeeAStatusCodeInThePutResponse(int statusCode) {
        TestAssertService.checkEquals(response.statusCode(), statusCode, "Status code 200 OK");
    }

    @And("^I should see a body that is not empty in the put response$")
    public void iShouldSeeABodyThatIsNotEmptyInThePutResponse() {
        TestAssertService.checkTrue(!response.getBody().asString().isEmpty(), "The body is not empty");
    }

    @And("^I verify that the pet status has been updated to (.*?)")
    public void iVerifyThatThePetStatusHasBeenUpdatedToSold(String status) {
        Response response = ApiService.performRequest(
                RequestType.GET.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress() + "/" + pet.getId(),
                headers,
                null
        );

        TestAssertService.checkEquals(response.statusCode(), HttpURLConnection.HTTP_OK, "Status code 200 OK");
        TestAssertService.checkEquals(Integer.parseInt(response.body().jsonPath().getString("id")), pet.getId(),
                "Pet ID");
        TestAssertService.checkEquals(response.getBody().jsonPath().getString("status"), status, "Pet status");
        ScenarioManager.writeLogInfo(response.getBody().asString());
    }

    @When("^I do a delete to remove the pet$")
    public void iDoADeleteToRemoveThePet() {
        response = ApiService.performRequest(
                RequestType.DELETE.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress() + "/" + pet.getId(),
                headers,
                null
        );

        ScenarioManager.writeLogInfo(response.getBody().asString());
        resposeDeletePetId = Integer.parseInt(response.getBody().jsonPath().getString("message"));
        ScenarioManager.writeLogInfo("Pet with ID " + response + " was delete");
    }

    @Then("^I should see a (\\d{3}) status code in the delete response$")
    public void iShouldSeeAStatusCodeInTheDeleteResponse(int statusCode) {
        TestAssertService.checkEquals(response.statusCode(), statusCode, "Status code");
    }

    @And("^I should see a body that is not empty in the delete response$")
    public void iShouldSeeABodyThatIsNotEmptyInTheDeleteResponse() {
        TestAssertService.checkTrue(!response.getBody().asString().isEmpty(), "The body is not empty");
    }

    @And("^I verify that the pet has been removed$")
    public void iVerifyThatThePetHasBeenRemoved() {
        Response responseGet = ApiService.performRequest(
                RequestType.GET.getRequestType(),
                Webs.WEB_BASE_PET_STORE.getWeb() + ApiAddress.API_V2_PET.getApiAddress() + "/" + resposeDeletePetId,
                headers,
               null
        );
        TestAssertService.checkEquals(responseGet.statusCode(), HttpURLConnection.HTTP_NOT_FOUND,
                "Status code 404 not found");
        System.out.println(responseGet.getBody().asString());
        TestAssertService.checkTrue(!responseGet.getBody().asString().isEmpty(),"The body of the response not empty");
    }
}
