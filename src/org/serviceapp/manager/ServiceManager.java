package org.serviceapp.manager;

import org.serviceapp.App;
import org.serviceapp.entity.ServiceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    // Работает
    public static ServiceEntity insert(ServiceEntity e) throws SQLException {
        try(Connection c = App.getConnection()){

            String sql = "insert into service" +
                    "(Title, Cost, DurationInSeconds, Description, Discount, MainImagePath) values" +
                    "(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, e.getTitle());
            ps.setDouble(2, e.getCost());
            ps.setInt(3, e.getDuration());
            ps.setString(4, e.getDescription());
            ps.setDouble(5, e.getDiscount());
            ps.setString(6, e.getMainImagePath());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                e.setID(rs.getInt(1));
                return e;
            }

            return null;
        }
    }

    // Работает
    public static void update(ServiceEntity e) throws SQLException{
        try(Connection c = App.getConnection()){

            String sql = "update service set " +
                    "Title=?, Cost=?, DurationInSeconds=?, Description=?, Discount=?, MainImagePath=? where id=?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, e.getTitle());
            ps.setDouble(2, e.getCost());
            ps.setInt(3, e.getDuration());
            ps.setString(4, e.getDescription());
            ps.setDouble(5, e.getDiscount());
            ps.setString(6, e.getMainImagePath());
            ps.setInt(7, e.getID());

            ps.executeUpdate();
        }
    }

    // Работает
    public static void delete(int id) throws SQLException{
        try(Connection c = App.getConnection()){

            String sql = "delete from service where id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    // работает
    public static List<ServiceEntity> selectAll() throws SQLException{
        try(Connection c = App.getConnection()){

            String sql = "select * from service";
            ResultSet rs = c.createStatement().executeQuery(sql);

            List<ServiceEntity> entities = new ArrayList<>();

            while(rs.next()){
                entities.add(new ServiceEntity(
                   rs.getInt("ID"),
                   rs.getString("Title"),
                   rs.getDouble("Cost"),
                   rs.getInt("DurationInSeconds"),
                   rs.getString("Description"),
                   rs.getDouble("Discount"),
                   rs.getString("MainImagePath")
                ));
            }

            return entities;
        }
    }

    public static ServiceEntity selectByID(int id) throws SQLException{
        try(Connection c = App.getConnection()){

            String sql = "select * from service where id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            ServiceEntity entity = null;

            if(rs.next()){
                entity = new ServiceEntity(
                        rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getDouble("Cost"),
                        rs.getInt("DurationInSeconds"),
                        rs.getString("Description"),
                        rs.getDouble("Discount"),
                        rs.getString("MainImagePath")
                );
            }

            return entity;
        }
    }

}
