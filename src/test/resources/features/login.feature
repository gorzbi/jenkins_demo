Feature: Login

  Background:
    Given Login page

  @first
  Scenario Outline: Login with wrong credentials

    When Enter wrong credentials <user>,<pass>
    Then Check wrong credentials message
    Examples:
      | user  | pass  |  |
      | jeden | jeden |  |
      | dwa   | dwa     |  |


  @second
  Scenario: Login with blocked user

    When Enter blocked user credentials
    Then Check blocked user message

  @third
  Scenario: Login with manual credentials

    When Enter good manual credentials
    Then Check after good login

  @fourth
  Scenario Outline: Login auto with good credentials

    When Enter good credentials <user>,<pass>
    Then Check after good login
    Examples:
      | user | pass |  |
      | elo  | elo  |  |

  @bug
  Scenario: There is no such element

    When Click no element
    Then Show message