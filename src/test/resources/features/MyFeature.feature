Feature: Simple feature

  Scenario: Import not empty file
    Given : input entities
  | firstName | name |
  | firstName1 | name1 |
  | firstName2 | name2 |
    When : run job
    Then : result entities
  | firstName | name |
  | firstName1 | name1 |
  | firstName2 | name2 |
