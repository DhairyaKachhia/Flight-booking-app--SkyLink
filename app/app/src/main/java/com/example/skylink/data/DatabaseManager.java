package com.example.skylink.data;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.github.javafaker.Faker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.database.sqlite.SQLiteStatement;
import android.content.Context;


public class DatabaseManager  {

    private SQLiteDatabase db;
    private Faker faker ;

    public DatabaseManager(SQLiteDatabase db) {
        this.db = db;
        this.faker = new Faker();
    }


    public void insertAirport(Context context) {
        try {
            // Open the CSV file for reading
            InputStream inputStream = context.getAssets().open("airports_data.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Skip the header line
            reader.readLine();

            // Prepare the SQL statement for insertion
            String insertQuery = "INSERT INTO Airport (Airport_ID, ICAO, Airport_Name) VALUES (?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(insertQuery);

            // Read each line from the CSV file
            String line;
            while ((line = reader.readLine()) != null) {
                // Extract data from the CSV line
                String[] values = line.split(",");
                int airportID = Integer.parseInt(values[0]);
                String icao = values[1];
                String airportName = values[2];

                // Bind values to the statement
                statement.bindLong(1, airportID);
                statement.bindString(2, icao);
                statement.bindString(3, airportName);

                // Execute the statement
                statement.execute();

                // Reset the statement for the next iteration
                statement.clearBindings();
            }

            // Close the readers
            reader.close();
            inputStream.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }



    public void  setTables(){
        db.execSQL("CREATE TABLE IF NOT EXISTS Card_Company (" +
                "Card_ID INTEGER PRIMARY KEY, " +
                "Card_Number TEXT, " +
                "Card_CVV TEXT, " +
                "Card_Expiry TEXT, " +
                "Card_Amount REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Customer_Feedback (" +
                "Feedback_ID INTEGER PRIMARY KEY, " +
                "User_ID INTEGER, " +
                "Feedback_Details TEXT, " +
                "Report_Date DATE, " +
                "FOREIGN KEY(User_ID) REFERENCES User_Profile(User_ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS User_Profile (" +
                "User_ID INTEGER PRIMARY KEY, " +
                "First_Name TEXT, " +
                "Last_Name TEXT, " +
                "Email TEXT, " +
                "Password TEXT, " +
                "Profile_Picture_URL TEXT, " +
                "DOB DATE)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Payment (" +
                "Payment_ID INTEGER PRIMARY KEY, " +
                "User_ID INTEGER, " +
                "Service BOOLEAN, " +
                "Service_Type INTEGER, " +
                "Payment_Amount REAL, " +
                "Payment_Date DATE, " +
                "Payment_Status TEXT, " +
                "FOREIGN KEY(User_ID) REFERENCES User_Profile(User_ID), " +
                "FOREIGN KEY(Service_Type) REFERENCES Add_On_Type(Add_On_Type_ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS Booking (" +
                "Booking_ID INTEGER PRIMARY KEY, " +
                "User_ID INTEGER, " +
                "Trip_ID INTEGER, " +
                "Way BOOLEAN, " +
                "FOREIGN KEY(User_ID) REFERENCES User_Profile(User_ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS Passenger_Takes (" +
                "Trip_ID INTEGER PRIMARY KEY, " +
                "Flight_ID INTEGER, " +
                "Seat_Number TEXT, " +
                "Class_ID INTEGER, " +
                "Flexible_Departure TEXT, " +
                "Flexible_Arrival TEXT, " +
                "Confirmed_Departure TEXT, " +
                "Confirmed_Arrival TEXT, " +
                "Checked_in BOOLEAN, " +
                "Boarding_Pass_URL TEXT, " +
                "FOREIGN KEY(Flight_ID) REFERENCES Available_Flight(Flight_ID), " +
                "FOREIGN KEY(Class_ID) REFERENCES Class(Class_ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS Class (" +
                "Class_ID INTEGER PRIMARY KEY, " +
                "Class_Type TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Available_Flight (" +
                "Flight_ID INTEGER PRIMARY KEY, " +
                "Airplane_ID INTEGER, " +
                "Departure_ICAO TEXT, " +
                "TakeOff_Date DATETIME, " +
                "Arrival_ICAO TEXT, " +
                "Arrival_Date DATETIME, " +
                "Price REAL, " +
                "FOREIGN KEY(Airplane_ID) REFERENCES Airplane(Airplane_ID), " +
                "FOREIGN KEY(Departure_ICAO) REFERENCES Airport(ICAO), " +
                "FOREIGN KEY(Arrival_ICAO) REFERENCES Airport(ICAO))");

        db.execSQL("CREATE TABLE IF NOT EXISTS Airplane (" +
                "Airplane_ID INTEGER PRIMARY KEY, " +
                "Airplane_Type TEXT, " +
                "Number_of_seats INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Airport (" +
                "Airport_ID INTEGER PRIMARY KEY, " +
                "ICAO TEXT, " +
                "Airport_Name TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Add_On (" +
                "Add_On_ID INTEGER PRIMARY KEY, " +
                "Add_On_Type_ID INTEGER, " +
                "Quantity INTEGER, " +
                "Description TEXT, " +
                "Trip_ID INTEGER, " +
                "FOREIGN KEY(Add_On_Type_ID) REFERENCES Add_On_Type(Add_On_Type_ID), " +
                "FOREIGN KEY(Trip_ID) REFERENCES Passenger_Takes(Trip_ID))");

        db.execSQL("CREATE TABLE IF NOT EXISTS Add_On_Type (" +
                "Add_On_Type_ID INTEGER PRIMARY KEY, " +
                "Add_On_Type TEXT, " +
                "Add_On_Price REAL)");

    }



}
