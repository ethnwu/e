# Phase 1, 2 - README
## Andrew
### FileEditor.java
- Class that can read and write (both Strings and String ArrayLists) to .txt files, used for data persistence
- Has the ability to remove lines from .txt files as well
- Will be used to organize necessary data (such as Usernames/Passwords, ticket reservations, etc.) into respective files
- Includes JUnit test (FileEditorJUnit.java) and interface (FileEditorInterface.java)
#### Notes for running JUnit test
- Ran using JUnit 4.13.1
- Tests ran successfully if "Excellent - Test ran successfully" is printed
  - Otherwise, errors are printed (showing expected and actual results if applicable)
### Client.java
- Class that can connect to server and display what the server sends (command list, gathered information from database)
- Sends user-inputted information to server for processing
- Currently works through terminal, will be changed to work through GUI in next phase
#### Worked on program control flow with Krish, added to Server.java and ServerThread.java for features
## Ethan
### User.java
The user class represents indvidual accounts in the system, where it can store the username, password, role, and activity of a user. It also provides getter and setter methods for the aforementioned fields and also serves as the core data structure which is managed by UserDatabase. To test, sample users are created to confirm correct data storage and updates.
### UserRole.java
The UserRole enum defines the different permission levels, and enforces consistent access control throughout the project and is also used/referenced by User and UserDatabase to determine user priveleges. 
### UserDatabaseInterface.java
The UserDatabaseInterface interface outlines all of the operations which are required to manage a user account. It includes the creation, deletion, authentication, as well as updates of roles, and makes sure that any classes (UserDatabase) which implement it follow a consistent track.
### UserDatabase.java
The UserDatabase class implements the UserDatabaseInterface interface in order to manage all User objects. Using an internal array and synchronized methods for thread-safe operations, it allows creating, deleting authenticating and management of roles.
### Server.java
The Server class handles connections to clients and creates a new thread for each (each thread runs an instance of ServerThread.java), while also loading database information from .txt files and updating user database accordingly.
### ServerThread.java
The ServerThread class implements runnable and processes all requests given to it by a client from Server.java, writing to users.txt and bookings.txt (synchronized) when needed. This includes functions such as logins, bookings, creating/deleting accounts and bookings, viewing bookings, etc.
## Ryan
- The RunLocalTest class can be ran to test User.java and UserDatabase.java. This class tests every public method in User.java and UserDatabase.java and demonstrates that the code ran successfully by printing "Excellent - Test ran successfully".
- The RunLocalTestPhaseTwo class tests the server and client functionality by testing all constructors and methods and prints that all tests ran successfully.
## Krish
### Theatre.java
This is the class that creates the theatre with fields     
`private String theatreName,
private Movie movie,
Seat [][] seating,
int rows,
int columns,
double price, and
public final static double basePrice` with value of 20.

The constructor takes in rows,columns, movie and a theatreName, sets the initial price and initializes the seats.

It has getters for theatreName,movie,rows,columns,price and setters for theatreName,movie, price. Price of
the ticket increases with the demand of the seat. There are also methods to get a specific seat, total seats 
and  number of seats remaining.The methods print to the terminal if something is wrong, as this will be helpful when 
we code the server. The print statements may be changed in the final submission of the project.

### Movie.java
This is the class which creates a movie with the fields
`private String title,
private Time time,
private double duration,
private double rating`

The constructor takes in these fields, validates them and then initializes a movie.

Each of these fields has a getter and a setter.The methods print to the terminal if something is wrong, as this will be helpful when
we code the server. The print statements may be changed in the final submission of the project.

### Seat.java
This is the class which creates the seats which go in the theatre with the fields
`private int row,
private int col,
private double price,
private Theatre theatre,
private boolean booked`

The constructor takes in a theatre, row and column, validates them, sets the price to the base price defined 
in the theatre class and initializes a seat.

The methods are getters,setters for the fields as well as a toString method for the seat and an equal method
to compare to seats if they have the same fields.The methods print to the terminal if something is wrong, as this will be helpful when
we code the server. The print statements may be changed in the final submission of the project.


### Ticket.java
This is the class which will make the ticket that will be displayed to the user with fields
`private String name,
private String totalTime in minutes,
private Theatre theatre,
private String movieName,
private String startTime,
private String endTime,
int row,
int col`

The constructor takes all the fields as parameters, validates them and initializes a ticket

Each field has its own getter and setter. There is a toString method which I plan to use to display the ticket
to the user.The methods print to the terminal if something is wrong, as this will be helpful when
we code the server. The print statements may be changed in the final submission of the project.

### Time.java
This is the class which has the time when the movie is showing with fields
`private int day,
private int month,
private int year,
private String startTime,
private String endTime`
with the strings written in xx:xx format using 24-hour clock.
This will be useful when we are going to  see if the user can buy a ticket.

The constructor takes in all the fields as parameters, validates them and then initializes the time class.

All the fields have getters and setters, and there is a toString method for the time. 
The methods print to the terminal if something is wrong, as this will be helpful when
we code the server. The print statements may be changed in the final submission of the project.

### Booking.java (Edited by Andrew)
This is the class that keeps tracks of the bookings a user will make with fields
`ArrayList<String> seatsReserved,
ArrayList<Ticket> tickets`

The constructor takes in no parameters, and creates a new ArrayList for seatsReserved

There are getters for the fields. There are also methods for adding Reservations, canceling reservations, add
and remove tickets and to get a specific seat in seatsReserved.The methods print to the terminal if something is wrong, as this will be helpful when
we code the server. The print statements may be changed in the final submission of the project.

### JUnit TestCases

There are test cases for each of the methods of every class.
The testcases for Movie, Theatre, Seat, Time, Ticket class were made by Krish and the test cases for booking class
by Andrew. 
Note: When you run a test case some of them might show invalid input, that is by design if an input
is invalid for a certain method and does not mean the test failed.
The test failed if the failures are printed out to the terminal.

### Phase 2
- Designed server and client outline, control flow
- Created and update interfaces
- Created Testcases for payment info
- Fixed phase one errors
## Landon
The PaymentInfo.java will just hold the important things from the card. Such as the cvv, the card number, and the expiary number.
They are getting passed as parameters then set and get.
These creating the methods and basic ideas for them. They will be further expanded upon in phase two.
It will need to be checked more in phase two. 
# Notes:
- File names that contain "JUnit" or "Test" are JUnit test files.
- Server and Client should work together by starting Server.java first, then Client.java, and using the terminal within the Client class.
This will be updated to have a GUI in phase 3.
