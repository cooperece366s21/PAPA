package edu.cooper.ece366.store;

//import edu.cooper.ece366.model.Content.Genre;
//import edu.cooper.ece366.model.User;
//import edu.cooper.ece366.model.User.Subscription;
//import edu.cooper.ece366.model.UserBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class PAPAJdbi {

    public static Jdbi create(String jdbcUrl) {
        Jdbi jdbi = Jdbi.create(jdbcUrl, "root", "password");
        jdbi.installPlugin(new SqlObjectPlugin());
        // you can register row mappers here or you can use @RegisterRowMapper annotation on each Dao
        // method
        //    jdbi.registerRowMapper(new UserMapper());
        return jdbi;
    }

//    public static class UserRowMapper implements RowMapper<User> {
//        @Override
//        public User map(final ResultSet rs, final StatementContext ctx) throws SQLException {
//            String id = rs.getString("id");
//            String name = rs.getString("name");
//            String subscription = rs.getString("subscription");
//            // this would work for postgres, which supports array types at the jdbc driver layer
//            //      List<Genre> genres =
//            //          Arrays.stream((String[]) rs.getArray("genres").getArray())
//            //              .map(Genre::fromDbValue)
//            //              .collect(Collectors.toList());
//            List<Genre> genres =
//                    Optional.ofNullable(rs.getString("genres"))
//                            .map(
//                                    genreString ->
//                                            Arrays.stream(genreString.split(",", -1))
//                                                    .map(Genre::fromDbValue)
//                                                    .collect(Collectors.toList()))
//                            .orElse(List.of());
//            return new UserBuilder()
//                    .id(id)
//                    .name(name)
//                    .subscription(Subscription.fromDbValue(subscription))
//                    .preferredGenres(genres)
//                    .build();
//        }
//    }

    /*
    You don't need to use this for simple enums, which will use Enum.name() to grab the db value
     */
//    public static class SubscriptionArgumentFactory extends AbstractArgumentFactory<Subscription> {
//
//        protected SubscriptionArgumentFactory() {
//            super(Types.VARCHAR);
//        }
//
//        @Override
//        protected Argument build(final Subscription subscription, final ConfigRegistry config) {
//            return (position, statement, ctx) -> statement.setString(position, subscription.name());
//        }
//    }
}