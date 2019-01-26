package com.company;

import javax.swing.plaf.synth.SynthTreeUI;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    public final String url = "jdbc:postgresql://localhost:5432/postgres";
    public final String user = "postgres";
    public final String password = "0555010965a";

    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the Database");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }


    public boolean insertCity(City city) {
        String SQL = "insert into cities1 (id,name) values (?,?);";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, city.id);
            stmt.setString(2, city.name);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();
        String SQL = "select * from cities;";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                City city = new City(rs.getInt("city_id"), rs.getString("city_name"));
                cities.add(city);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }
}
