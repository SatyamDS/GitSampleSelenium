Feature: serviceNow login functionality

Background:
Given open the chrome browser and maximize the window
And load the service now application

Scenario: TC001_Login with positive credential 
Given enter username as 'admin'
And enter password as 'India@123'
When click login button
Then Enter 'incident' in the filter navigator
Then Click All under Incident
When Click New button 
Then Read the incident number from Number and store it in variable for verification
When  Click on look up icon for Caller and Search 'Abel' in the lookup window
Then Enter the Short_description in previous window 
When Click on Submit 
Then use that saved incident number in search box in ALL incident and search