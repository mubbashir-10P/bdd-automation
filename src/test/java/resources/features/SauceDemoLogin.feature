Feature: To Verify Login Functionality Of A Sauce Demo Website

  Background:
    Given Sam is on Sauce demo application
    And   Title page "Swag Labs" of the application is visible

    @login,@smoke
  Scenario: Verify Login to Application with correct credentials

    When  Sam enters username "standard_user"
    And   Sam enters password "secret_sauce"
    And   Click on Submit button
    Then  Login should be successful
    And   Product listing should be visible

    @login,@smoke
  Scenario: Verify Login to Application with wrong credentials

    When  Sam enters username "wrong_credentials"
    And   Sam enters password "wrong_password"
    And   Click on Submit button
    Then  Error Message "Epic sadface: Username and password do not match any user in this service" should be visible