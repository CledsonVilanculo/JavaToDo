package com.cledson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // sim eu sei que isto nao deveria estar visivel para todo mundo, mas tanto faz isto e so um proejeto simples que fiz para aprender MySQL
    private static final String url = "jdbc:mysql://93qc9n.h.filess.io:3307/javatodo_havingoff?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String user = "javatodo_havingoff";
    private static final String password = "286278c0daf2f9603ddba279e4cf6115d6c718f5";

    public static Connection connect() {
        try {
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (SQLException a) {
            System.out.println("Erro ao conectar: " + a);
            return null;
        }
    }
}
