Feature: Petstore swagger pet API

c
  Scenario Outline: Delete request about pet information
    When I request service to delete the pet information
      | petId    | <petId>         |
    Then the service returns the status <code>
    Examples:
      | petId    | code |
      | -1       | 404  |
      | a        | 404  |
#     | 1        | 200  |

  Scenario Outline: Get request to fetch pet information
    When I request service to fetch the pet information
      | status    | <status>         |
    Then the service returns the status <code>
    Examples:
      | status    | code|
      | available | 200 |
      | sold      | 200 |
      | pending   | 200 |

  Scenario Outline: Put request to update pet information
    When I request service to update the pet information
      | id        | <id>             |
      | status    | <status>         |
      | name      | <name>           |
      | photoUrls | <photoUrls>       |
    Then the service returns the status <code>
    Examples:
      | id                  | status    | name| photoUrls                        |  code |
      |  1                  | available | tod |src/test/resources/photos/pet.jpeg|  200  |
      |  2                  | sold      | tom |src/test/resources/photos/pet1.jpeg| 200  |
      |  4                  | pending   | tob |src/test/resources/photos/pet1.jpeg| 200  |
    #  |983212450992312343563| pending   | toe |  pet1.jpeg                        | 200  |

  Scenario Outline: Post request to upload image
    When I request service to create to upload image
      | file | <file>   |
    Then the service returns the status <code>
    Examples:
      | file                                  | code|
      |  src/test/resources/photos/pet.jpeg   | 200 |
      |  src/test/resources/photos/pet1.jpeg  | 200 |

