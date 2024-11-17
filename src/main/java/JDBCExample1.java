import java.sql.*;


import java.sql.*;

public class JDBCExample1 {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/libliblib";
        String username = "root";
        String password = "Tausend1308";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Connected to the database!");

            // Создаем таблицы
            String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100))";

            String createOrdersTable = "CREATE TABLE IF NOT EXISTS Orders (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT, " +
                    "order_date DATE, " +
                    "amount DOUBLE, " +
                    "FOREIGN KEY (user_id) REFERENCES Users(id))";

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createUsersTable);
                stmt.execute(createOrdersTable);
                System.out.println("Tables created successfully!");
            }

            // Вставляем данные
            String insertUser = "INSERT INTO Users (name, email) VALUES (?, ?)";
            String insertOrder = "INSERT INTO Orders (user_id, order_date, amount) VALUES (?, ?, ?)";

            try (PreparedStatement userStmt = connection.prepareStatement(insertUser);
                 PreparedStatement orderStmt = connection.prepareStatement(insertOrder)) {

                // Вставляем пользователей
                userStmt.setString(1, "Alice");
                userStmt.setString(2, "alice@yahoo.com");
//                    userStmt.setString(1, "Alex");
//                    userStmt.setString(2, "alex@yahoo.com");
                userStmt.executeUpdate();

                userStmt.setString(1, "Bob");
                userStmt.setString(2, "bob@example.com");
//                    userStmt.setString(1, "John");
//                    userStmt.setString(2, "john@yahoo.com");
                userStmt.executeUpdate();

                // Вставляем заказы
                orderStmt.setInt(1, 1); // ID пользователя Alice
                orderStmt.setDate(2, Date.valueOf("2024-11-15"));
                orderStmt.setDouble(3, 250.0);
                orderStmt.executeUpdate();

                orderStmt.setInt(1, 2); // ID пользователя Bob
                orderStmt.setDate(2, Date.valueOf("2024-11-16"));
                orderStmt.setDouble(3, 450.0);
                orderStmt.executeUpdate();

//                    orderStmt.setInt(10, 3); // ID пользователя Alex
//                    orderStmt.setDate(15, Date.valueOf("2024-10-15"));
//                    orderStmt.setDouble(3, 200.0);
//                    orderStmt.executeUpdate();

//                    orderStmt.setInt(11, 4); // ID пользователя John
//                    orderStmt.setDate(16, Date.valueOf("2024-10-15"));
//                    orderStmt.setDouble(3, 210.0);
//                    orderStmt.executeUpdate();

                System.out.println("Data inserted successfully!");
            }

            // Выполняем SELECT с JOIN
            String selectQuery = "SELECT Users.name, Users.email, Orders.order_date, Orders.amount " +
                    "FROM Users " +
                    "INNER JOIN Orders ON Users.id = Orders.user_id";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date orderDate = resultSet.getDate("order_date");
                    double amount = resultSet.getDouble("amount");

                    System.out.printf("Name: %s, Email: %s, Order Date: %s, Amount: %.2f%n",
                            name, email, orderDate, amount);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}