package edu.cooper.ece366.DBconnection;

import edu.cooper.ece366.framework.UserBuilder;
import org.jdbi.v3.core.Jdbi;
import edu.cooper.ece366.framework.User;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.cooper.ece366.DBconnection.UserDao;

import java.util.List;

public class jdbitest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/PAPA";
        Jdbi jdbi = PAPAJdbi.create(jdbcUrl);
/*
        List<User> userNames = jdbi.withExtension(UserDao.class, dao -> {
            dao.createTable();

            dao.insertPositional(0, "Alice");
            dao.insertPositional(1, "Bob");
            dao.insertNamed(2, "Clarice");
            dao.insertBean(new UserBuilder().ID("3").nickname("David").build());

        return dao.userNames();
    });

//    assertThat(userNames).containsExactly(
//        new User(0, "Alice"),
//        new User(1, "Bob"),
//        new User(2, "Clarice"),
//        new User(3, "David"));
//    }
 */
    }
}
