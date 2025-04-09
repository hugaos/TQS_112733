Feature: Reserve a meal and view it in the "Minhas Reservas" list

  Scenario: Reserve a meal and verify it appears in My Reservations
    Given I am on the homepage 
    When I reserve the meal "Prato Normal - Frango assado com mostarda e massa esparguete com molho de tomate"
    When I go to Minhas Reservas
    And I should see "Prato Normal - Frango assado com mostarda e massa esparguete com molho de tomate" in my reservations list
    