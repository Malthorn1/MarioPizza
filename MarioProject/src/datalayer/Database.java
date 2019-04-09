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
import java.util.Scanner;

public class Database implements DB {

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
        Date date = Date.valueOf(LocalDate.now());
        Connection connection = connector();
        pizza = getPizza(pizza.getPizzaNummer());
        Statement stat = connection.createStatement();
        
        try {

            int ORDRENUMMER = 1;
            //Laver et SQL date object vi kan sætte ind
            ResultSet rs = stat.executeQuery("SELECT TIMESTAMP(NOW()) as timestamp");
            Timestamp ts = rs.getTimestamp("timestamp");
            String sql = "INSERT INTO aktiveordrer(ORDRENUMMER, FÆRDIG, DATOOPRETTET, DATOFÆRDIG, PIZZANUMMER)VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ORDRENUMMER + 1);
            statement.setBoolean(2, false);
            statement.setDate(3, date);
            statement.setNull(4, 0);
            statement.setInt(5, pizza.getPizzaNummer());
            statement.executeUpdate();

        } catch (SQLException e) {

        }
    }
//        Scanner scanner = new Scanner(System.in);
//            try {
//                Connection connection = connector();
//                Statement statement = connection.createStatement();
//                int PIZZANUMMER=scanner.nextInt();
//                statement.executeUpdate("insert into AKTIVEORDRER value('"+PIZZANUMMER+"')");
//            } catch (SQLException ex) {
//                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            //https://www.youtube.com/watch?v=c139qtuK2_s 
//        }
//    

    @Override
    public void fjernBestilling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
