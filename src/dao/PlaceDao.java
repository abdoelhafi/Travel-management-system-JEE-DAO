package dao;

import models.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceDao implements IPlaceDao {
    @Override
    public Long createPlace(Place p) {
        Connection connection = SingletonConnection.getConnection();
        try {
            //CREATE STATEMENTS
            PreparedStatement cs = connection.prepareStatement("INSERT INTO PLACES (NAME) VALUES (?)");
            PreparedStatement ms = connection.prepareStatement("SELECT MAX(ID) AS MID FROM PLACES");
            // EXECUTE QUERIES
            System.out.println(p.getName());
            cs.setString(1,p.getName());
            cs.executeUpdate();

            ResultSet resultSet = ms.executeQuery();
            if(resultSet.next()){
                p.setId(resultSet.getLong("MID"));
            }
            cs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p.getId();
    }

    @Override
    public Place findPlaceById(Long id) {
        Connection connection = SingletonConnection.getConnection();
        try {
            //CREATE STATEMENTS
            PreparedStatement fs = connection.prepareStatement("SELECT * FROM PLACES WHERE ID = (?)");
            // EXECUTE QUERIES
            fs.setLong(1,id);
            ResultSet resultSet = fs.executeQuery();
            Place p = new Place();
            if(resultSet.next()){
                p.setId(id);
                p.setName(resultSet.getString("NAME"));
            }
            System.out.println(p.getName() + p.getId());
            fs.close();
            return p;



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updatePlace(Place p) {
        Connection connection = SingletonConnection.getConnection();
        try {
            int res;
            //CREATE STATEMENTS
            PreparedStatement us = connection.prepareStatement("UPDATE PLACES SET  NAME = ? WHERE ID = ?");
            // EXECUTE QUERIES
            us.setString(1,p.getName());
            us.setLong(2,p.getId());
            res = us.executeUpdate();
            us.close();
            return res==1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removePlace(Place p) {
        Connection connection = SingletonConnection.getConnection();
        try {
            int res;
            //CREATE STATEMENTS
            PreparedStatement rs = connection.prepareStatement("DELETE FROM PLACES WHERE ID = ?");
            // EXECUTE QUERIES
            rs.setLong(1,p.getId());
            res=rs.executeUpdate();
            rs.close();
            return res==1 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
