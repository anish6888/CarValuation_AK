Feature: The features check the value of the car

  Scenario: To Validate car details from registration numbers
    Given I read the input file "car_input.txt"
    And I search for each registration number on the car valuation website
    Then I compare the results with the output file "car_output.txt"