Feature: Login

  Background:
    Given Login page

  @first
  Scenario: Login with wrong credentials

    When Enter wrong credentials
    Then Check wrong credentials message

  @second
  Scenario: Login with blocked user

    When Enter blocked user credentials
    Then Check blocked user message

  @third
  Scenario Outline: Login with manual credentials

    When Enter good manual credentials <username>,<password>
    Then Check after good login
    Examples:
      | username      | password     |  |
      | standard_user | secret_sauce |  |

  @fourth
  Scenario: Login auto with good credentials

    When Enter good credentials
    Then Check after good login