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

public class DBFacade implements Facade {

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
        DBFacade db = new DBFacade();
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
        DBFacade db = new DBFacade();
        Connection connection = db.connector();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from aktiveordrer");
            if (rs.next()) {
                boolean færdig = rs.getBoolean("FÆRDIG");
                Timestamp timestamp = rs.getTimestamp("DATOOPRETTET");
                java.sql.Date dt = rs.getDate("DATOFÆRDIG");
                int pizzanummer = pizza.getPizzaNummer();
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

    //Skal tage en integer, som så skal sammenlignes med ordrenummeret i SQL databasen, hvis det matcher
    //Fjerner den den linje
    @Override
    public void fjernBestilling(Integer ordrenummer) {
        DBFacade db = new DBFacade();
        Connection connection = db.connector();
        try {
            PreparedStatement st = connection.prepareStatement("DELETE FROM aktiveordrer WHERE ordrenummer = ?");
            st.setInt(1, ordrenummer);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Skal tage et ordrenummer som der skrives til ordrehistorikken, og derefter bliver fjernet fra aktive ordrer
    @Override
    public void færdiggørBestilling(Integer ordrenummer) throws SQLException {
        DBFacade db = new DBFacade();
        Connection connection = db.connector();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from ordrehistorik");
            if(rs.next()){
                boolean færdig = rs.getBoolean("FÆRDIG");
                Timestamp timestamp = rs.getTimestamp("DATO");
                String sql = "INSERT INTO ordrehistorik(ORDRENUMMER, FÆRDIG, DATO)VALUES(?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, ordrenummer);
                statement.setBoolean(2, færdig);
                statement.setTimestamp(3, timestamp);
                fjernBestilling(ordrenummer);
                statement.executeUpdate();
                
                
            }

        } catch (SQLException e){
            System.out.println(e);
        }
    }
    
    @Override
    public void visOrdrehistorik() throws SQLException {
        Connection connection = connector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM ordrehistorik");
        while (result.next()) {
            int ordrenummer = result.getInt(1);
            boolean færdig = result.getBoolean("FÆRDIG");
            Timestamp ts = result.getTimestamp("DATO");
            System.out.print("Ordrenummer: " +ordrenummer + ", ");
            System.out.print("Færdig: "+færdig + ", ");
            System.out.print("Dato oprettet: "+ts + "\n");

        }
    }
    
        @Override
        public void visAktiveOrdre() throws SQLException {
        Connection connection = connector();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM AKTIVEORDRER");
        while (result.next()) {
            int ordrenummer = result.getInt("ORDRENUMMER");
            boolean færdig = result.getBoolean("FÆRDIG");
            Timestamp ts = result.getTimestamp("DATOOPRETTET");
            int pizzanummer = result.getInt("PIZZANUMMER");
            System.out.print("Ordrenummer: " +ordrenummer + ", ");
            System.out.print("Færdig: "+færdig + ", ");
            System.out.print("Dato oprettet: "+ts + "\n");
            System.out.println("Pizzza nummer: "+ pizzanummer + "\n");

        }
    }
}
