# Task
Build a stand-alone web application using Java/J2EE that will authenticate a user by validating their username & password. 
For this exercise store the correct list of username (an email address), password & first name in a local file and validate against it.

# Requirements

1. Username can be a valid email address

1. Password needs to have at least
  
    *    8-30 characters
    *    One upper case letter
    *    One lower case letter
    *    One number
    *    No spaces
    *    And cannot be the same as email address
	
1. Upon successful authentication display "Good [Morning/Afternoon/Evening/Night] ${first_name}". You last logged in at ${last_login_time}
 
    *    06:00-12:00 Morning
    *    12:00-18:00 Afternoon
    *    18:00-22:00 Evening
    *    22:00-06:00 Night
  
1. Display a button/link to “log-out” if the user is logged-in. On click of log-out, log the user out and take them to the login page.
1. Upon unsuccessful login attempt display - "Please try again" 
1. After 3 unsuccessful login attempts against the same username, block the user for 15 mins and display "Account Locked" to the user during this period.
    *    Also log this event in a log file that the account has been locked as a result of a possible malicious attempt. 
1. Do NOT allow more than 5 authentication attempts from the same IP within a period of 10 mins
    *    For this exercise read X-forwarded-for header as the IP of the user. 
    *    Log this event in the log file with IP of the user stating that some suspicious requests are coming in from this IP. 

# Expectations
* This exercise must be submitted within 3 hrs even if it is partially complete
* Write unit tests to cover all the scenarios mentioned above
* Note all assumptions and reasoning using comments in your code
* Engineering best practices should be followed where appropriate. We value security, performance, scalability, maintainability. 
* The final maven artifact can be an executable JAR file or a WAR file
* Please provide necessary steps to build, deploy & launch the application

# Considerations
* Please feel free to use any Java/J2EE framework for completing this exercise
* The POM file sets the JDK version to 1.8 but please feel to use a higher version if you want
* We understand with the given timescales it will not be possible to implement a full production-ready system
* We leave it to you to decide how to submit data to the server
* In the interest of time you can keep the UI as simple as possible.     
* Work in small iterative changes
* Consider the efficiency of your implementation 


