package edu.cooper.ece366.DBconnection;

//import edu.cooper.ece366.model.User;
import java.sql.SQLException;
import java.util.UUID;

import edu.cooper.ece366.framework.User;
import edu.cooper.ece366.store.UserStore;
import edu.cooper.ece366.DBconnection.PAPAJdbi;
import edu.cooper.ece366.DBconnection.UserDao;
import org.jdbi.v3.core.Jdbi;

public class UserStoreMySQL implements UserStore {

    private final Jdbi jdbi;

    public UserStoreMySQL(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    // for testing purposes
    @Override
    public User get(String id) {
        return jdbi.withHandle(handle -> handle.attach(UserDao.class).getUser(id));
    }

    public User add(String name) {
        String id = UUID.randomUUID().toString();
        jdbi.useHandle(
                handle -> {
                    handle.attach(UserDao.class).insertUser(id, name);
                });
        return get(id);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public String getUserId(User user) { return null;}

    @Override
    public int storeToDB(DBconnection com_in, User user) throws SQLException {
        return 0;
    }

    ;

//    @Override
//    public User add(final String name, final Subscription subscription) {
//        String id = UUID.randomUUID().toString();
//        jdbi.useHandle(
//                handle -> {
//                    handle.attach(UserDao.class).insertUser(id, name, subscription);
//                });
//        return get(id);
//    }
//    public static void main(String[] args) {
//        UserStoreMysql userStoreMysql =
//                new UserStoreMysql(
//                        CoopflixJdbi.create("jdbc:mysql://localhost:3306/coopflix?serverTimezone=UTC"));
//
//        //    String userId = UUID.randomUUID().toString();
//        //    User user = userStoreMysql.get(userId);
//        //    System.out.println(user);
//
//        User yeet = userStoreMysql.add("yeet", Subscription.FAMILY_HD);
//
//        User userAfterWrite = userStoreMysql.get(yeet.id());
//        System.out.println(userAfterWrite);
//    }
//
//    private final Jdbi jdbi;
//
//    public UserStoreMysql(final Jdbi jdbi) {
//        this.jdbi = jdbi;
//    }
//
//    @Override
//    public User get(final String id) {
//        return jdbi.withHandle(handle -> handle.attach(UserDao.class).getUser(id));
//    }
//
//    @Override
//    public User add(final String name, final Subscription subscription) {
//        String id = UUID.randomUUID().toString();
//        jdbi.useHandle(
//                handle -> {
//                    handle.attach(UserDao.class).insertUser(id, name, subscription);
//                });
//        return get(id);
//    }
}