@NavigateCategories
Feature: As a tester navigate all the categories

    Scenario: Navigate through product categories: Phones, Laptops and Monitors
      Given URL to go to blaze page without login confirmation
      Then Dashboard is displayed
      When Click on phone category
      Then Phone category is displayed
      When Click on Laptops category
      Then Verify Laptops category is displayed
      When Click on monitors category
      Then Verify monitors category is displayed