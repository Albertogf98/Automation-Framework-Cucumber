@PlaceOrder
Feature: As a tester, check that when you click on the purchase button a form is displayed,
  fill in the fields andcheck that the price is the same as the one shown in the cart

  Scenario: Click place order fill in all web form fields click purchase and check the amount equals total price
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
    And Click on cart button
    Then Cart page is displayed
    When Click on place order button
    Then Form page is displayed
    And Fill all the inputs
    Then Click on purchase button
    When Modal thank you purchase is displayed
    Then Capture and log purchase Id and Amount
    And Assert purchase amount equals expected
    When Click OK button
    Then Cart page is displayed