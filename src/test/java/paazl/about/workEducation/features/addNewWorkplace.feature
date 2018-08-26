@tag
Feature: Add new workplace in Work and Education subsection
  I want to add a new workplace in the work and education subsection

  @tag1
  Scenario: Add new workplace
    Given user logged in with a Facebook account
    And user goes to his profile
    When user clicks in the About section
    And user selects work and education
    And user clicks on the add icon
    And user adds information for workplace
    And user clicks the save changes button
    Then new workplace added with success