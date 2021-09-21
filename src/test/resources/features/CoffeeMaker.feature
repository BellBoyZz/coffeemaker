Feature: Make a coffee
  The customers can buy a coffee if they have enough money and input the coffee recipe number correctly.

  Background: buy a coffee.
    Given Coffee maker is on service and ready to serve.

  Scenario: buy an invalid coffee.
    When The customer order an invalid coffee recipe.
    Then The customer order a coffee with a recipe number 0, with money 100, then change equal to 100.

  Scenario: buy a coffee with not enough money.
    When The customer order a coffee with not enough money.
    Then The customer order a coffee with a recipe number 1, with money 20, then change equal to 20.