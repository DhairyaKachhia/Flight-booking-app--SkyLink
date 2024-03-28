# Project Readme

## User Profile Update Functionality

In the second iteration of our project, we encountered significant challenges with the User Profile Update Functionality. The primary issue stemmed from our inability to finalize the design for critical user information fields, such as address, city, province, phone number, date of birth, and gender. Due to time constraints, we were forced to implement these fields as basic text boxes. This approach lacked any form of logic validation, permitting the input of various data types. Such a design flaw allowed for the potential entry of incorrect or irrelevant data, thereby compromising the integrity and utility of our system. Additionally, we encountered issues with duplicate validation logic, which was inconsistently applied across different layers of the application, particularly within the presentation layer.

### Improvements Implemented

1. **Consolidation of Validation Logic**: We centralized all user information-related validation logic into a single class. This strategic consolidation ensures that validation occurs uniformly across the application, particularly within the logic layer. Such an approach simplifies the maintenance and scalability of our validation mechanisms.

2. **Enhanced Validation Techniques**: We refined our validation logic to include more sophisticated checks, such as proper date formatting. This enhancement is pivotal in preventing data integrity issues when saving information to the database.

3. **Error Handling and User Feedback**: We improved error handling by leveraging exceptions to convey error messages. These messages are now displayed as toast notifications, providing immediate and clear feedback to users upon encountering validation errors.

4. **Improved User Interface for Profile Updates**: We addressed the issue of displaying user information on the profile update screen. Now, when users navigate to this section, their current information is accurately displayed, facilitating a smoother and more intuitive update process.

##Velocity chart
![Velocity](docs/Velocity.png)

