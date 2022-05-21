@AddLaptopToCart
Feature: As a tester check that when adding a product a pop up is shown saying that the product was added

  Scenario: Navigate to laptop and add Sony vaio i5 and Dell i7 8gb. Accept pop up confirmation.
    Given URL to go to blaze page without login confirmation
    Then Dashboard is displayed
    When Click on Laptops category
    Then Verify Laptops page is displayed
    When Click on product Sony vaio i5
    Then Laptop page is displayed
    When Click add to cart button
    Then Accept pop up is confirmation displayed
    When Click home button
    Then Click on Laptops category
    When Click on product Dell i7 8gb
    Then Laptop page is displayed
    When Click add to cart button
    Then Accept pop up is confirmation displayed