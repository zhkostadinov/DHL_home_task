@Feature
Feature: Checking Europe calculator functionality
  Background:
    Given User is on "europeTransitCalculatorPage" page
    And I consent data privacy

  Scenario Outline: Checking calculator functionality for proper input
    When I put "<orgCountry>" and "<orgPostCode>" into original country
    And I put "<destCountry>" and "<destPostCode>" into destination country
    And I press calculate button
    Then I should see "<messageOne>" and "<messageTwo>"

    Examples:
      | orgCountry | orgPostCode | destCountry | destPostCode | messageOne            | messageTwo          |
      | Bulgaria   | 1000        | Sweden      | 31420        | Committed delivery on |Estimated delivery on|
      | Sweden     | 31420       | Sweden      | 31420        | Committed delivery on |Estimated delivery on|