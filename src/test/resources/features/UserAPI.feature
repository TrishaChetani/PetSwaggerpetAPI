Feature: petstore swagger

  Scenario Outline: POST request to create user record.
    When I request service to create user detail
      | username  | <name>        |
      | firstName | <firstName>   |
      | lastName  | <lastName>    |
      |  email    |  <email>      |
      | password  |  <password>   |
      | phone     |  <phone>      |
      | userStatus|  <userStatus> |
    Then the service returns the status code <code>
    Examples:
      | name   | firstName    | lastName | email          | password |phone     | userStatus|code |
      | Trisha | trisha       | chetani  | test@test.com  |1234      |1234567890|1          |200  |
      |        |              |          |                |          |          |           |200  |
      | Trisha | trisha       | chetani  | test@test.com  |1234      |1234567890|0          |200  |
      | 1      |              | chetani  |                |1234      |1234567890|0          |200  |



  Scenario Outline: DELETE request to delete user record by username
    When I request service to remove user detail by username
      | username | <username> |
    Then the service returns the status code <code>
    Examples:
      | username | code    |
      | 1        | 200     |
      | Trisha   | 200     |
      | </html>  | 404     |
      |          | 405     |




