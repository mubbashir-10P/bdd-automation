Feature: Verification of Sauce demo website's product filtering, add to cart checkout and logout
  Background:
    Given Sam is on Sauce demo application
    And   Title page "Swag Labs" of the application is visible
    When  Sam enters username "standard_user"
    And   Sam enters password "secret_sauce"
    And   Click on Submit button
    Then  Login should be successful
    And   Product listing should be visible

    @product,@smoke
  Scenario: verify sam is able to filter, add to cart checkout and logout to website
    When sam filter the product from "Price (low to high)"
    Then filter should be applied with "Sauce Labs Onesie" on top

    When sam select product "Sauce Labs Backpack"
    Then product "Sauce Labs Backpack" should be open
    And  Add to cart button should be visible

    When Sam clicks on Add to cart button
    Then Cart Icon should show 1 product in cart
    And  Click on cart icon
    Then "Your Cart" page should be open
    And  Product "Sauce Labs Backpack" should present with description

    When Sam clicks on "checkout" button
    Then "Checkout: Your Information" page should be open
    And  fill user information first name "Syed" last name "Saad" zip code "74500"

    When Sam clicks on "continue" button
    Then "Checkout: Overview" page should be open
    And  Product "Sauce Labs Backpack" should present with description

    When Sam clicks on "finish" button
    Then "Thank you for your order!" success message should be visible