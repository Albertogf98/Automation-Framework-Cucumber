@Blaze
  Feature: Check the functionalities of the Blaze website

    Scenario:Navigate through the categories, add and delete a product from the cart and fill in the order form
      Given URL to go to blaze page without login confirmation
      Then Dashboard is displayed
      When Click on phone category
      Then Verify phone page is displayed
      When Click on laptops category
      Then Verify laptops page is displayed
      When Click on monitors category
      Then Verify monitors page is displayed
      When Click on laptops category
      Then Verify laptops page is displayed
      When Click on product Sony vaio i5
      Then Laptop page is displayed
      When Click add to cart button
      Then Accept pop up is confirmation displayed
      Then Click on laptops category
      When Click on product Dell i7 8gb
      Then Laptop page is displayed
      When Click add to cart button
      Then Accept pop up is confirmation displayed
      And Click on cart button
      Then Cart page is displayed
      When Click on delete button of the product Dell i7 8gb
      Then The product disappears from the list
      When Click on place order button
      Then Form page is displayed
      And Fill all the inputs
      Then Click on purchase button
      When Modal thank you purchase is displayed
      Then Capture and log purchase Id and Amount
      And Assert purchase amount equals expected
      When Click OK button
      Then The form disappears