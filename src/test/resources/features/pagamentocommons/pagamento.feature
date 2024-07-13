Feature: Producao Controller

  Scenario: Update an order status
    Given I have an order with ID 1
    When I send a request to update the order status to "DELIVERED"
    Then the order status should be updated successfully
