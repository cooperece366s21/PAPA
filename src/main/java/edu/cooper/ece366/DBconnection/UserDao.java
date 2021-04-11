package edu.cooper.ece366.DBconnection;

import edu.cooper.ece366.framework.User;
//import edu.cooper.ece366.model.User.Subscription;
import edu.cooper.ece366.DBconnection.PAPAJdbi.UserRowMapper;
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
//    @SqlQuery("SELECT * FROM user ORDER BY name")
//    @RegisterBeanMapper(User.class)
//    List<User> listUsers();
    @SqlQuery(
            "select id, name "
                    + "from users u "
            // change this line  + "left join user_preferred_genres upg "
            // this one too      + "on u.id = upg.user_id "
                    + "where u.id = :id "
                    + "group by id, name, subscription")
    @RegisterRowMapper(UserRowMapper.class)
    User getUser(@Bind("id") String id);

    @SqlUpdate("insert into users (id, name) values (:id, :name)")
    void insertUser(
            @Bind("id") String id,
            @Bind("name") String name);


//    @SqlUpdate("CREATE TABLE user (id INTEGER PRIMARY KEY, name VARCHAR)")
//    void createTable();
//
//    @SqlUpdate("INSERT INTO user(id, name) VALUES (?, ?)")
//    void insertPositional(int id, String name);
//
//    @SqlUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
//    void insertNamed(@Bind("id") int id, @Bind("name") String name);
//
//    @SqlUpdate("INSERT INTO user(id, name) VALUES (:id, :name)")
//    void insertBean(@BindBean User user);
//
//    @SqlQuery("SELECT * FROM user ORDER BY name")
//    @RegisterBeanMapper(User.class)
//    List<User> listUsers();



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