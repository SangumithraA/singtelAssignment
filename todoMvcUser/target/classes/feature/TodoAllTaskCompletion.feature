Feature: Launching Todo application to complete all tasks

Scenario: Positive case of Todo Application
Given  Open the browser
And Load the application URL 'http://todomvc.com/examples/vue/'


Scenario Outline: 
Given Enter the Todoitem as <todoitem>

Examples:
|todoitem| 
|'Requirement Analysis Phase'|
|'High Level design document creation Phase'|
|'Low Level design document creation Phase'|
|'Coding Phase'|
|'Review Phase'| 
|'Test Execution Phase'|
|'Maintanence Phase'| 
  

Scenario: Verify All button 
When Click on the All button
Then Verify All todoitems passed in examples are added under All tasks

Scenario: Complete all assigned tasks
Given Click on the Radio button to complete the task

Scenario: Verify the completed items
When Click on completed button
Then Verify all the selected items moved under completed 

Scenario: Check Active button after completing all the tasks
When Click Active button to check any pending task
Then ActiveList should be empty

Scenario: Check Clear completed button after completing the tasks
When Clear Completed button is Clicked
Then Check whether All tasks are completed







