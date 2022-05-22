@Pets
Feature: As a tester I want to do GET, POST, PUT and DELETE requests on the pet shop

  Scenario: Get available pets, create available pet, update available pet to sold and delete pet
    Given Values to do the requests
    When I do a Get to available pets
    Then I should see a 200 status code in the get response
    And I should see a body that is not empty in the get response
    And I should see contains values id as number
    And I should see contains values name as string
    And I should see contains values photoUrls as string
    And I should see contains available status
    When I do a Post to add new available pet
    Then I should see a 200 status code in the post response
    And I should see a body that is not empty in the post response
    And I verify that the pet has been added correctly
    When I do a Put to update the status of the pet to sold
    Then I should see a 200 status code in the put response
    And I should see a body that is not empty in the put response
    And I verify that the pet status has been updated to sold
    When I do a delete to remove the pet
    Then I should see a 200 status code in the delete response
    And I should see a body that is not empty in the delete response
    And I verify that the pet has been removed