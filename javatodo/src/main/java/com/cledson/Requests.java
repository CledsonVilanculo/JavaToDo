package com.cledson;

import java.sql.*;

import javax.swing.JOptionPane;

public class Requests {
    public static boolean requestNewAccount(String nome, String senha) {
        Connection connection = Conexao.connect();
        String comandoSql = "INSERT INTO listas (nome, senha) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, nome); // 1 significa que o nome deve ser colocado no primeiro "?"
            preparedStatement.setString(2, senha); // mesma coisa aqui, so que no segundo "?"
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLIntegrityConstraintViolationException a) {
            // quando o a pessoa tenta criar uma conta com um nome qeu ja existe
            if (a.toString().contains("Duplicate entry '" + nome + "' for key 'listas.PRIMARY")) {
                Main.fraseErro.setForeground(java.awt.Color.RED);
                Main.fraseErro.setText("<html><p style='text-align: center'>Já existe uma conta com esse nome <br>Dois usuários não podem usar o mesmo nome</p></html>");
            }
            return false;
        } catch (Exception a) {
            System.out.println("Ocorreu um erro no requestNewAccount\n" + a);
            return false;
        }
    }

    public static boolean checkAccount(String nome) {
        Connection connection = Conexao.connect();
        String comandoSql = "SELECT nome FROM listas WHERE nome = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            String nomeEncontrado = "";

            while (resultSet.next()) {
                nomeEncontrado = resultSet.getString("nome");
            }

            if (nomeEncontrado.equals(nome)) {
                preparedStatement.close();
                return true;
            } else {
                preparedStatement.close();
                return false;
            }
        } catch (Exception a) {
            System.out.println("Ocorreu um erro no checkAccount\n" + a);
            return false;
        }
    }

    public static boolean checkPassword(String nome, String senha) {
        Connection connection = Conexao.connect();
        String comandoSql = "SELECT senha FROM listas WHERE nome = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            String senhaEncontrada = null;
            
            while (resultSet.next()) {
                senhaEncontrada = resultSet.getString("senha");
            }

            if (senhaEncontrada.equals(senha)) {
                preparedStatement.close();
                return true;
            } else {
                preparedStatement.close();
                return false;
            }
        } catch (Exception a) {
            System.out.println("Ocorreu um erro no checkPassword\n" + a);
            return false;
        }
    }

    public static String[] getTasks(String nome) {
        Connection connection = Conexao.connect();
        String comandoSql = "SELECT tarefas FROM listas WHERE nome = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            String tarefas = null;

            while (resultSet.next()) {
                tarefas = resultSet.getString("tarefas");
            }

            if (tarefas != null) {
                String[] listaDeTarefas = tarefas.split("%");
                for (int i = 0; i < listaDeTarefas.length; i ++) {
                    listaDeTarefas[i] = listaDeTarefas[i];
                }

                return listaDeTarefas;
            } else {
                return null;
            }
        } catch (Exception a) {
            System.out.println("Erro no getTasks\n" + a);
            return null;
        }
    }

    public static boolean addTask(String nome, String novaTarefa) {
        Connection connection = Conexao.connect();
        String comandoSql = "UPDATE listas SET tarefas = ? WHERE nome = ?";
        String[] tarefasAntigas = getTasks(nome); // Para evitar que a nova tarefa subscreva as tarefas antigas
        String tarefasAntigasFormatadas = "";

        if (tarefasAntigas != null) {
            for (String tarefaAntiga : tarefasAntigas) {
                tarefasAntigasFormatadas += tarefaAntiga + "%";
            }

            tarefasAntigasFormatadas += novaTarefa;
        } else {
            tarefasAntigasFormatadas = novaTarefa + "%";
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, tarefasAntigasFormatadas);
            preparedStatement.setString(2, nome);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception a) {
            System.out.println("Erro no addTasks\n" + a);
            return false;
        }
    }

    public static boolean deleteTask(String nome, String tarefas) {
        Connection connection = Conexao.connect();
        String comandoSql = "UPDATE listas SET tarefas = ? WHERE nome = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, tarefas);
            preparedStatement.setString(2, nome);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception a) {
            System.out.println("Erro no deleteTask\n" + a);
            return false;
        }
    }

    public static boolean passwordChanged(String nome, String novaSenha) {
        Connection connection = Conexao.connect();
        String comandoSql = "UPDATE listas SET senha = ? WHERE nome = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql);
            preparedStatement.setString(1, novaSenha);
            preparedStatement.setString(2, nome);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception _) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar a sua senha", null, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}