package datalayer;

import businesslogic.Pizza;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Database implements DB {
//    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
//    Calendar date = Calendar.getInstance(); 
//    long t=date.getTimeInMillis(); 
//    Date AddingTenMins=new Date(t + (10 *ONE_MINUTE_IN_MILLIS));

    public Connection connector() {
        Connection connection = null;
        try {
            String user = "root";
            String password = "rootprejler";
            String IP = "localhost";
            String PORT = "3306";
            String DATABASE = "mario";
            String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE
                    + "?useUnicode=true&useJDBCcompliantTimezoneShift=true&"
                    + "useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC";

            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
        }
        //Returnerer en connection vi kan bruge hver gang vi laver et metode kald
        return connection;
    }

    @Override
    public void printMenukort() throws SQLException {
        Connection connection = connector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM pizzaer");
        while (result.next()) {
            int pizzanummer = result.getInt(1);
            String pizzanavn = result.getNString(2);
            int pizzapris = result.getInt(3);
            System.out.print(pizzanummer + " ");
            System.out.print(pizzanavn + " ");
            System.out.print(pizzapris + "\n");

        }
    }

    // Med denne metode laver vi et pizza object fra databasen som vi kan bruge 
    // til at lave ordrer med
    public Pizza getPizza(int pizzaNummer) throws SQLException {
        Database db = new Database();
        Connection connection = db.connector();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM pizzaer WHERE PIZZANUMMER"
                    + "=" + pizzaNummer);
            if (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setPizzaNummer(rs.getInt("PIZZANUMMER"));
                pizza.setPizzaNavn(rs.getString("PIZZANAVN"));
                pizza.setPris(rs.getInt("PIZZAPRIS"));
                return pizza;
            }

        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public void opretBestilling(Pizza pizza) throws SQLException {
        //Skal indlæse alle værdier fra aktive ordrer og derefter skrive til aktiveordrer med det pizza objekt. 
        //Man er nød til at hente SQL typerne fra datbasen før man kan skrive dem ind igen i Java
        Database db = new Database();
        Connection connection = db.connector();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from aktiveordrer");
            if (rs.next()) {
                boolean færdig = rs.getBoolean("FÆRDIG");
                System.out.println(færdig);
                Timestamp timestamp = rs.getTimestamp("DATOOPRETTET");
                System.out.println(timestamp);
                java.sql.Date dt = rs.getDate("DATOFÆRDIG");
                System.out.println(dt);
                int pizzanummer = pizza.getPizzaNummer();
                System.out.println(pizzanummer);
                String sql = "INSERT INTO aktiveordrer(FÆRDIG, DATOOPRETTET, DATOFÆRDIG, PIZZANUMMER)VALUES(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                //Vi springer første kolonne over "ORDRENUMMER" da den er sat til AUTO_INCREMENT i SQL, så SQL sørger selv for at incremente den hver gang der bliver
                //Skrevet noget ind
                statement.setBoolean(1, færdig);
                statement.setTimestamp(2, timestamp);
                statement.setDate(3, dt);
                statement.setInt(4, pizzanummer);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    //GAMLE METODE IN CASE OF EMERGENCY
//    @Override
//    public void opretBestilling(Pizza pizza) throws SQLException {
//        Database db = new Database();
//        Connection connection = db.connector();
//
//        try {
//            pizza = getPizza(pizza.getPizzaNummer());
//            Statement stat = connection.createStatement();
//            int ORDRENUMMER = 1;
//            ResultSet rs = stat.executeQuery("SELECT TIMESTAMP(NOW()) as timestamp");
//            if (rs.next()) {
//                Timestamp ts = rs.getTimestamp("timestamp");
//                Date dnow = new Date(System.currentTimeMillis()+5*60*1000);
//                String sql = "INSERT INTO aktiveordrer(ORDRENUMMER, FÆRDIG, DATOOPRETTET, DATOFÆRDIG, PIZZANUMMER)VALUES(?,?,?,?,?)";
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.setInt(1, ORDRENUMMER + 1);
//                statement.setBoolean(2, false);
//                statement.setTimestamp(3, ts);
//                statement.setDate(4, dnow);
//                statement.setInt(5, pizza.getPizzaNummer());
//                statement.execute();
//                statement.close();
//                               
//            }
//
//        } catch (SQLException e) {
//        }
//    }
    @Override
    public void fjernBestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
