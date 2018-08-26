@tag
Feature: Validation of the workplace form
  I want to test the validation of the form for adding a new workplace with missing a required input 

  @tag1
  Scenario: Validation of the form for adding a new workplace
    Given user logged in with a Facebook account
    And user goes to his profile
    When user clicks in the About section
    And user selects work and education
    And user clicks on the add icon
    And user clicks the save changes button
    Then an error massage displayed