package com.cledson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/listasdetarefas?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "minhasenha123@";

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