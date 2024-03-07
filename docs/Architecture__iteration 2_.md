# Group 6 Skyline

This is the document of our project "Skylink".


# Iteration 2 functions 

Our app can work in both **online** and **offline** mode. We have moved some of our features and user stories to later iterations. Most of our codes are under **app/app/src/main/java/com/example/skylink**. Below are features we have done for iteration 2.

## Flight Searching

We have created our real database using HSQLDB for data storage. We also improved our methods about searching for available flights from the database, it was implemented in the java file " AirportPath." We also have unit tests and integration tests for our business/logic layer.
## Seat Selection
This feature enables users to select seats before they proceed to payment, users can view both avaliable and unavaliable seats in the app, and can confirm their prederred seat selection in the app.
## Payment System
We now have a fully functional payment system, firstly, after seat selection, there will be a page that takes the user's credit card information and billing information, after the user submit the payment, the payment successful page will pop-up and it will show the details of the payment. At the bottom, there is a button that will take the user back to the flight searching page.
## User Authentication
It includes sign-in page and sign-up page,if the user already has an account, the ueser can enter the email and password and log in to the app. If the user does not have an account, then the sign-up page will enable the user to create an account,it will collect various information about the user including phone number, billing name,email, and other information that are needed for account management and user authentication.


### Presentation layer:

**FlightDisplay**
  - It is the page that takes users’ search parameters to display the resulting flights on the next activity.

**FlightSearch**
- It displays resulting flights based on users’ input on a ListView using CustomFlightAdaptor. It also displays sorting filters that user can select to update the flight result list.

**CreditCardPaymentActivity and PaymentSuccessfulActivity**
- These are for the payment system , credit card payment activity will take the user's payment information, after the payment was successful made, it will take the user to the payment successful page.
**CustomFlightAdaptor**
- The scrollable view in the Flight_search activity displays the resulting flights.
**Seat Select**
- After a user has chosen the flight and entered the passenger information, the app will bring the user to the seat selection page. The user can view seats and make selections based on preference.

**User_info**
- The display asks travelers to fill in the information needed for flight booking. It also enables users to create and manage accounts.


## Business Logic:

**AirportPath**
- Responsible for searching and returning the list of flights based on destination, date, and other parameters the user has selected.

**BookingManager**
- Takes user information for booking flight.

**FlightSorting**
- Sorts resulting flights based on the option user has selected from spinner.

**PaymentHandler**
- Collect payment information and displays on the screen.

**PlaneConfiguration**
- This is to provide the infrmation about the planes such as number of seats per row.

**Session**
- We use sessions to get different information when the app runs and store temperory data.

**User Handler**
- This is used for managing user actions including creating profiles, verifying user information, and logging in.

### Data:

**FlightHSQLDB**
- Stores flight route in the database.

**BookingDatabase**
- Stores user entered data for flight booking.

**CitiesRepository**
- Stores cities in the database

**PaymentHSQLDB**
- Process data related to payments.

**BookingStub, FlightStub,PaymentStub,UserStub**
- These are all for unit tests, and they have similar functions of the real database.

### Domain Specific Object:

**Flight**
- An object that stores information about a single flight like origin, destination, time, etc.

**Flights**
- An object that stores the list of flights available based on user input.

**FlightSearch**
- 

**Aircraft**
- It contains the name of the aircraft and the seat configuration information of business class and economy class.

**City**
- It contains the city names and code, and provides a way to obtain city information.

**FlightSearch**
- Flight search information, including the departure place, destination, departure date, return date, total number of passengers and whether it is a one-way flight.

**PassengerData**
- The code implements a class that represents passenger information, including the passenger's title, first name, last name, phone number and email address, and provides corresponding get and set methods.

**TripInvoice**
- The code implements a class that represents a trip invoice, containing the user ID and total amount.

**UserProperties**
- This class contains information such as the user's name, email, password, gender, address, phone number, date of birth, and country/region, and provides a method to verify this information.

**User Stories**
Finished:
- Creating and Managing User Profile
- Interactive Seat Map
Moved to iteration 3:
- Add-ons

