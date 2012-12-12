Feature:                                                                                                                                                     
In order to store values
As a user
I want to put items on the stack

Scenario: Push to an empty stack
Given an empty stack with capacity 2
When I push "first" to a stack
Then "first" should be on top of the stack

Scenario: Push to a full stack
Given an empty stack with capacity 2
And "first" pushed on the stack
And "second" pushed on the stack
When I push "third" to a stack
Then FullStackException should be thrown

Scenario: Push to nonempty and non full stack
Given an empty stack with capacity 3
And "first" pushed on the stack
And "second" pushed on the stack
When I push "third" to a stack
Then "third" should be on top of the stack
And the stack should be full