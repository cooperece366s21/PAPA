package edu.cooper.ece366.store;


import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserStoreImpl implements UserStore {

    DataSource dbcp;
    Connection conn = null;

    // Update with user builder
    @Override
    public User get(DBconnection com_in, final String name) throws SQLException{
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        String returnUserID = null, returnUsername = null, returnUserpassword = null;
        try{
            //conn = dbcp.getConnection();
            PreparedStatement getUsername = conn.prepareStatement("SELECT * FROM users WHERE name=?;");
            getUsername.setString(1, name);
            try {
                ResultSet rs = getUsername.executeQuery();
                while(rs.next()){
                    returnUserID = rs.getString("ID");
                    returnUsername = rs.getString("name");
                    returnUserpassword = rs.getString("password");
                }
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return null;
        }
        finally {
            conn.close();
        }
        User user = new UserBuilder().ID(returnUserID)
                .name(returnUsername)
                .password(returnUserpassword)
                .build();
        return user;
    }

    @Override
    public int updateDB(DBconnection com_in, final String ID,  final String name) throws SQLException{
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement getUsername = conn.prepareStatement("UPDATE users SET name=? WHERE ID=?;");
            getUsername.setString(1, name);
            getUsername.setString(2, ID);
            try {
                ResultSet rs = getUsername.executeQuery();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return -1;
        }
        finally {
            conn.close();
        }
        return 0;
    }

    @Override
    public int storeToDB(DBconnection com_in, String userID, String username, String password) throws SQLException {
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, name, password) VALUES (?, ?, ?);");
            stmt.setString(1, userID);
            stmt.setString(2, username);
            stmt.setString(3, password);
            try{
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return -1;
        }
        finally {
            conn.close();
        }
        return  0;
    }

    @Override
    public User newUser(DBconnection com_in, String name, String password) throws SQLException {
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();

        String userID = UUID.randomUUID().toString();

        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (ID, name, password) VALUES (?, ?, ?);");
            stmt.setString(1, userID);
            stmt.setString(2, name);
            stmt.setString(3, password);
            try{
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
        }
        finally {
            conn.close();
        }

        return new UserBuilder().ID(userID).name(name).password(password).build();
    }

    @Override
    public User getUserbyID(DBconnection com_in, String userID) throws SQLException {
        this.dbcp = com_in.getDataSource();
        this.conn = dbcp.getConnection();
        String returnUserID = null, returnUsername = null, returnUserpassword = null;
        try{
            //conn = dbcp.getConnection();
            PreparedStatement getUsername = conn.prepareStatement("SELECT * FROM users WHERE ID=?;");
            getUsername.setString(1, userID);
            try {
                ResultSet rs = getUsername.executeQuery();
                while(rs.next()){
                    returnUserID = rs.getString("ID");
                    returnUsername = rs.getString("name");
                    returnUserpassword = rs.getString("password");
                }
            } catch (SQLException throwables) {
                System.err.println("Error when executing SQL command.");
                throwables.printStackTrace();
            }
        } catch (SQLException err) {
            System.err.println("Error when connecting to database.");
            err.printStackTrace();
            return null;
        }
        finally {
            conn.close();
        }
        User user = new UserBuilder().ID(returnUserID)
                .name(returnUsername)
                .password(returnUserpassword)
                .build();
        return user;
    }

}
