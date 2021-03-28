package edu.cooper.ece366.store;

//import edu.cooper.ece366.model.User;
//import edu.cooper.ece366.model.User.Subscription;
//import edu.cooper.ece366.store.CoopflixJdbi.UserRowMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface UserDao {

    // data access object
    // can write queries in here and use annotations
    // see https://jdbi.org/#_sql_objects

  /*
    example from terminal
  mysql> use coopflix;
  Reading table information for completion of table and column names
  You can turn off this feature to get a quicker startup with -A
  Database changed
  mysql> select id, name, subscription, group_concat(genre) genres from users u left join user_preferred_genres upg on u.id = upg.user_id group by id, name, subscription;
  +--------------------------------------+-------+--------------+---------------+
  | id                                   | name  | subscription | genres        |
  +--------------------------------------+-------+--------------+---------------+
  | 0c6d7ffd-7717-4e2f-b769-238154906e6b | ethan | family_basic | comedy,horror |
  +--------------------------------------+-------+--------------+---------------+
     */

    // mysql
//    @SqlQuery(
//            "select id, name, subscription, group_concat(genre) genres "
//                    + "from users u "
//                    + "left join user_preferred_genres upg "
//                    + "on u.id = upg.user_id "
//                    + "where u.id = :id "
//                    + "group by id, name, subscription")
//    @RegisterRowMapper(UserRowMapper.class)
//    User getUser(@Bind("id") String id);

    // "returning *" only works in postgres :(
    //  @SqlQuery(
    //      "insert into users (id, name, subscription) values (:id, :name, :subscription) returning
    // *")
    //  @RegisterArgumentFactory(SubscriptionArgumentFactory.class)
    //  @RegisterRowMapper(UserRowMapper.class)

//    @SqlUpdate("insert into users (id, name, subscription) values (:id, :name, :subscription)")
//    void insertUser(
//            @Bind("id") String id,
//            @Bind("name") String name,
//            @Bind("subscription") Subscription subscription);
}