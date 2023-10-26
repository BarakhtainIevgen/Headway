Feature: Daily microlearning session

  Background: User is Logged In
    Given I navigate to the login page
    When I submit username and password
    Then Home page should be displayed

    Scenario:
      Then microlearning items count is 10
      When I select firts item in Daily microlearning session
      Then item is opened
      And content is displayed
      And card view is visible
      And following button is present on screen
        |Share   |
        |Remember|
      When I click on close button
      Then Discover page is opened

