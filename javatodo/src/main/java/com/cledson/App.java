package com.cledson;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class App {
        public static String getDayPeriod() {
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String horaFormatada = hora.format(formatter);

        if (Integer.parseInt(horaFormatada) < 12) {
            return "Bom dia, ";
        } else if (Integer.parseInt(horaFormatada) < 18) {
            return "Boa tarde, ";
        } else {
            return "Boa noite, ";
        }
    }

    // a lista de tarefas ja com numeros que aparece no JLabel listaDeTarefas em createMainSceen()
    public static String getFormatedTasks() {
        String[] arrayTarefas = Requests.getTasks(Main.userName);
        String listaDeTarefas = "<html><p style='font-weight: bold; font-size: 14px'>Suas tarefas</p><br>";

        try {
            if (arrayTarefas.length <= 1 && arrayTarefas[0].equals("")) {
                // isto e para quando a lista de tarefas so tem "%" na base de dados, entao volta vazio, e raro mas acontece
                return "<html><p style='font-weight: bold; font-size: 14px'>Sem tarefas...</p><br>Adicione tarefas para começar</html>";
            }
            
            int a = 1;
            for (int i = 0; i < arrayTarefas.length; i ++) {
                if (!arrayTarefas[i].equals("")) { // para o caso do ususario apagar a primeira tarefa e a lista comcar com %, ai fica 1. (vazio) 2. tarefas....
                    listaDeTarefas += a + ". " + arrayTarefas[i] + "<br>";
                    a ++;
                }
            }

            // quando o usuario apaga todas as tarefas uma de cada vez, dai o JLabel buga e fica em branco, entao tem que falar sem tarefas, EU TO FICANDO MALUCO!!!
            if (listaDeTarefas.equals("<html><p style='font-weight: bold; font-size: 14px'>Suas tarefas</p><br>")) {
                return "<html><p style='font-weight: bold; font-size: 14px'>Sem tarefas...</p><br>Adicione tarefas para começar</html>";
            }

            return listaDeTarefas + "</html>";
        } catch (NullPointerException _) {
            // NullPointerException e a unica Exception possivel (eu acho), por causa do arrayTarefas que pode ser null, se o usuario nao tiver tarefas
            return "<html><p style='font-weight: bold; font-size: 14px'>Sem tarefas...</p><br>Adicione tarefas para começar</html>";
        }
    }

    public static String getNumberOfTasks() {
        try {
            int numeroDeTarefas = Requests.getTasks(Main.userName).length;

            if (numeroDeTarefas == 0) {
                return "Sem tarefas por fazer";
            } else if (numeroDeTarefas == 1) {
                return numeroDeTarefas + " tarefa por fazer";
            } else {
                return numeroDeTarefas + " tarefas por fazer";
            }
        } catch (NullPointerException _) {
            return "Sem tarefas por fazer";
        }
    }

    public static void openGitHub() {
        try {
            URI url = new URI("https://github.com/CledsonVilanculo");
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(url);
        } catch (UnsupportedOperationException _) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o link, a operação não é compatível com o seu sistema operativo", null, JOptionPane.ERROR_MESSAGE);
        } catch (IOException _) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o link, nenhum navegador encontrado", null, JOptionPane.ERROR_MESSAGE); 
        } catch (Exception _) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro desconhecido ao abrir o link", null, JOptionPane.ERROR_MESSAGE); 
        }
    }
}
