@NavigateToCartAndDeleteProduct
Feature: As tester check that a product can be deleted from the cart

  Scenario: Navigate to cart and delete dell i7 8gb from cart
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
    When Click on delete button of the product Dell i7 8gb
    Then The product disappears from the list