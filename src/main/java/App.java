import dao.UserDao;
import entity.User;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.LikedServlet;
import servlets.TestServlet;
import servlets.UserServlet;
import utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

/* function test() {
  console.log("test")
}

test();


function test1(callback) {
  callback();
}


test1(() => {
  console.log("test1")
}) */

/* function multiply(a) {
  return (b) => {
    return (c) => {
      return a * b * c;
    }
  }
}

const result = multiply(10)(5)(3)

console.log(result)

function map(value) {
        return (mapper) => {
        return mapper(value);
        }
        }

        const mapper = map(10);
        const result = mapper((v) => v * 10)
        const result1 = mapper((v) => v + 10)
        const result2 = mapper((v) => v - 10)

        console.log(result, result1, result2)

*/


public class App {

    public static void main(String[] args) throws Exception {
        int port = 8082;
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler();
        final Optional<Connection> connection = ConnectDB.get();
        UserDao userDao = new UserDao();


////добавление строки
//        connection.map(con -> {
//            try {
//                //1 шаг создание запроса
//                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO users (name, photo) VALUES (?, ?)");
//                //2 шаг добавление данных к запросу. Пропускаем пока.
//                preparedStatement.setString(1, "Violetta");
//                preparedStatement.setString(2, "");
//                preparedStatement.execute();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            return con;
//        });
////получение строк
//        connection.map(con -> {
//            try {
//                //1 шаг создание запроса
//                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users");
//                //2 шаг добавление данных к запросу. Пропускаем пока.
//                //3 шаг выполнение запроса
//                ResultSet resultSet = preparedStatement.executeQuery();
//                //4 шаг обработка данных
//                ArrayList<User> users = new ArrayList<>();
//                while (resultSet.next()) {
//                    users.add(User.resultSetToUser(resultSet));
//                }
//                System.out.println(users);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            return con;
//        });

        handler.addServlet(servlets.TestServlet.class, "/test");
        handler.addServlet(new ServletHolder(servlets.FileServlet.class), "/assets/*");
        handler.addServlet(new ServletHolder(new UserServlet(userDao)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(userDao)), "/liked");

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
