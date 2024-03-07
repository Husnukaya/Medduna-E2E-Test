package stepdefinitions;

import io.cucumber.java.en.*;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepdefinitions.UI_StepDefs.fakeRoomNumber;

public class DB_StepDefs {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Given("connect to database")
    public void connect_to_database() throws SQLException {
        //Create connection
        connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2","select_user","Medunna_pass_@6");
        //Create statement
        statement = connection.createStatement();

    }
    @Then("read the room and validate")
    public void read_the_room_and_validate() throws SQLException {

        String query = "SELECT * FROM room WHERE room_number = "+fakeRoomNumber;
        //execute the query
        resultSet = statement.executeQuery(query);
        resultSet.next();//next() method will take the pointer to the next record if it has.

        //Read the data
        String actRoomNumber = resultSet.getString("room_number");
        String actRoomType = resultSet.getString("room_type");
        boolean actStatus = resultSet.getBoolean("status");
        String actPrice = resultSet.getString("price");
        String actDescription = resultSet.getString("description");

        assertEquals(fakeRoomNumber+"", actRoomNumber);
        assertEquals("DELUXE", actRoomType);
        assertTrue(actStatus);
        assertEquals("400.00", actPrice);
        assertEquals("Created for e2e test",actDescription);

    }

}