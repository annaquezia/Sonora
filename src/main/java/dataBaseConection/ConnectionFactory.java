package dataBaseConection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static String status = "Não conectou...";

    public ConnectionFactory() {}

    public static java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String serverName = "localhost";
            String myDataBase = "gerenciadorMusica";

            String url = "jdbc:mysql://" + serverName + ":3306/" + myDataBase
                    + "?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true";

            String username = "";
            String password = "";

            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado!", e);
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao conectar no banco MySQL. Verifique URL, usuário, senha e se o MySQL está rodando.",
                    e
            );
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean closecConnection() {
        try {
            ConnectionFactory.getConnection().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
