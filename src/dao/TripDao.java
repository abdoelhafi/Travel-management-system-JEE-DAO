package dao;

import models.Place;
import models.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TripDao implements ITripDao{

    @Override
    public Long creatTrip(Trip t) {
        Connection connection = SingletonConnection.getConnection();
        try {
            //CREATE STATEMENTS
            PreparedStatement cs = connection.prepareStatement("INSERT INTO TRIPS (DEPARTURE,DESTINATION,PRICE) VALUES (?,?,?,?)");
            PreparedStatement ms = connection.prepareStatement("SELECT MAX(ID) AS MID FROM TRIPS");
            // EXECUTE QUERIES
            cs.setObject(1,t.getDeparture());
            cs.setObject(2,t.getDestination());
            cs.setDouble(3,t.getPrice());
            cs.executeUpdate();

            ResultSet resultSet = ms.executeQuery();
            if(resultSet.next()){
                t.setId(resultSet.getLong("MID"));
            }
            cs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t.getId();
    }

    @Override
    public Trip findTripById(Long id) {

        Connection connection = SingletonConnection.getConnection();
        try {
            //CREATE STATEMENTS
            PreparedStatement fs = connection.prepareStatement("SELECT * FROM TRIPS WHERE ID = (?)");
            // EXECUTE QUERIES
            fs.setLong(1,id);
            ResultSet resultSet = fs.executeQuery();
            Trip t = new Trip();

            if(resultSet.next()){
                t.setId(id);
                t.setDeparture((Place) resultSet.getObject("DEPARTURE"));
            }
            fs.close();
            return t;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;    }

    @Override
    public boolean removeTrip(Trip t) {
        Connection connection = SingletonConnection.getConnection();
        try {
            int res;
            //CREATE STATEMENTS
            PreparedStatement rs = connection.prepareStatement("DELETE FROM TRIPS WHERE ID = ?");
            // EXECUTE QUERIES
            rs.setLong(1,t.getId());
            res=rs.executeUpdate();
            rs.close();
            return res==1 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
