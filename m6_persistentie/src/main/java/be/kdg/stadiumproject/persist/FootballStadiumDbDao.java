package be.kdg.stadiumproject.persist;

import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.RoofType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FootballStadiumDbDao implements FootballStadiumDao{
    private static final Logger logger = Logger.getLogger("be.kdg.model.FootballStadiumDbDao");


    private Connection connection = null;

    public FootballStadiumDbDao(String path)  {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:"+path,"sa", "");
            System.out.println("SQL connection successful");
        } catch (SQLException ex){
            logger.log(Level.SEVERE, "Cant make connection with DB" + ex.getMessage());
        }

        createTable();
    }

    public void close(){

        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            System.out.println("\nDatabase gesloten");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Issues arose closing the connection: " + e.getMessage());
        }
    }

    private void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS stadiumtable");
            statement.execute("CREATE TABLE stadiumtable" +
                    "(id INTEGER IDENTITY," +
                    "name VARCHAR(30)," +
                    "city VARCHAR(30)," +
                    "opened DATE," +
                    "rooftype VARCHAR(30)," +
                    "cost  DOUBLE," +
                    "capacity INTEGER)"
            );
            System.out.println("DB created");
        }catch (SQLException e){
            logger.log(Level.SEVERE, "Error creating table: " + e.getMessage());
        }
    }
    @Override
    public boolean insert(FootballStadium footballStadium) {
        if (footballStadium.getId() >= 0) return false; //student heeft al PK dus bestaat al in database
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO stadiumtable VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, footballStadium.getName());
            preparedStatement.setString(2, footballStadium.getCity());
            preparedStatement.setDate(3, Date.valueOf(footballStadium.getOpened()));
            preparedStatement.setString(4, footballStadium.getRoofType().name());
            preparedStatement.setDouble(5, footballStadium.getCost());
            preparedStatement.setInt(6, footballStadium.getCapacity());
            int rowsAffected = preparedStatement.executeUpdate();
            boolean result = rowsAffected == 1;
            preparedStatement.close();
            return result;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when insertinge: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(String naam) {
        try {
            Statement statement = connection.createStatement();
            String query;
            if (naam.equals("*")){
                query = "DELETE FROM stadiumtable";
            } else {
                query = "DELETE FROM stadiumtable where name = '"+ naam +"'";
            }

            int rowsaffected = statement.executeUpdate(query);
            return rowsaffected != 0;
        }catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
            return false;
        }

    }

    @Override
    public boolean update(FootballStadium footballStadium) {
        try{
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE stadiumtable set " +
                    "name = '" + footballStadium.getName() + "'," +
                    "city = '" + footballStadium.getCity() + "'," +
                    "opened = '" + Date.valueOf(footballStadium.getOpened()) + "'," +
                    "rooftype = '" + footballStadium.getRoofType().name() + "'," +
                    "cost = " + footballStadium.getCost() + "," +
                    "capacity = " + footballStadium.getCapacity() +
                    " where id = " + footballStadium.getId()
            );

            Boolean res = rowsaffected == 1;
            statement.close();
            return res;
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "Error udpating stadium: " + e.getMessage());
        }

        return false;
    }

    @Override
    public FootballStadium retrieve(String naam) {
        FootballStadium toRetrieveStadium = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM stadiumtable where name = '" + naam + "'");
            if (rs.next()){
            toRetrieveStadium = new FootballStadium(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("city"),
                    rs.getDate("opened").toLocalDate(),
                    RoofType.valueOf(rs.getString("rooftype")),
                    rs.getDouble("cost"),
                    rs.getInt("capacity")
                    );}
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error when retrieving STADIUM: " + e.getMessage());
        }
        return toRetrieveStadium;

    }

    @Override
    public List<FootballStadium> sortedOn(String query) {
        List<FootballStadium> sortedList= new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM stadiumtable ORDER BY " + query);
            while (rs.next()) {
                sortedList.add(
                         new FootballStadium(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("city"),
                                rs.getDate("opened").toLocalDate(),
                                RoofType.valueOf(rs.getString("rooftype")),
                                rs.getDouble("cost"),
                                rs.getInt("capacity")
                        )
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving sql sorted: " + e);
        }
//        sortedList.forEach(System.out::println);
        return sortedList;
    }


}
