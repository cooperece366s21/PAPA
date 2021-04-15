package edu.cooper.ece366.store;


import edu.cooper.ece366.DBconnection.DBconnection;
import edu.cooper.ece366.categories.Restaurant;
import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.framework.UserBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserStoreImpl implements UserStore {

    public static final Map<String, User> USER_MAP;

    DataSource dbcp;
    Connection conn = null;

    static {
        USER_MAP =
                List.of(
                        new UserBuilder()
                                .ID("KollKid")
                                .nickname("KollKid")
                                .build(),
                        new UserBuilder()
                                .ID("Pablo")
                                .nickname("Pablo")
                                .build(),
                        new UserBuilder()
                                .ID("xXx_Sephiroth_xXx")
                                .nickname("xXx_Sephiroth_xXx")
                                .build()
                ).stream()
                .collect(Collectors.toMap(User::ID, Function.identity()));
    }

    @Override
    public User get(final String id) {
        return USER_MAP.get(id);
    }

    @Override
    public void update(final User user) {
        USER_MAP.put(user.ID(), user);
    }

    @Override
    public String getUserId(User user){
        return user.ID();
    }

    @Override
    public int storeToDB(DBconnection con_in, User user) throws SQLException {
        this.dbcp = DBconnection.getDataSource();
        try{
            conn = dbcp.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?);");
            stmt.setString(1, user.ID());
            stmt.setString(2, user.nickname());
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

}
