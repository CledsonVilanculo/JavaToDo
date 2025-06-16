import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import javax.swing.*;

public class App{
    public static void main(String[] args) throws Exception {
        String escolha = JOptionPane.showInputDialog(null,"1. Nova tarefa\n2. Ver tarefas\n3. Estou entediado\n4. Formatar tarefas\n5. Sair", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
        
        switch (escolha) {
            case "1": newTask();
            break;
            
            case "2": seeTasks();
            break;
            
            case "3": imBored();
            break;
            
            case "4": reset();
            break;
            
            case "5": sair();
            break;

            case null: break;

            default: JOptionPane.showMessageDialog(null, "Opção não encontrada!", "TO DO LIST", JOptionPane.WARNING_MESSAGE);
                main(args);
            break;
        }
    }

    public static void newTask() {
        String novaTarefa = JOptionPane.showInputDialog(null, "Digite a nova tarefa:", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
        boolean jaEsta = false;

        for (int i = 0; jaEsta == false; i ++) {
            try {
                int soma = i + 1;
                File novoTXT = new File("Tarefa " + soma);
                if (novoTXT.exists()) {

                } else {
                    String botaoCancel = Integer.toString(JOptionPane.CANCEL_OPTION);
                    String botaoSair = Integer.toString(JOptionPane.CLOSED_OPTION);

                    if (novaTarefa == botaoCancel || novaTarefa == botaoSair) {

                    } else {
                        switch (novaTarefa) {
                            case null: jaEsta = true;
                            break;

                            case "": JOptionPane.showMessageDialog(null, "A tarefa não pode estar em branco", "TO DO LIST", JOptionPane.WARNING_MESSAGE);
                                newTask();
                                jaEsta = true;
                            break;

                            default: novoTXT.createNewFile();
                                FileWriter escritor = new FileWriter(novoTXT);
                                escritor.write(i + 1 + " - " + novaTarefa);
                                escritor.close();
                                JOptionPane.showMessageDialog(null, "Tarefa guardada com sucesso!", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
                                jaEsta = true;
                            break;
                        }
                    }
                }
            } catch (IOException a) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar a tarefa\n" + a, "TO DO LIST", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void seeTasks() {
        boolean jaEsta = false;
        String[] listaDasTarefas = new String[100];
        int tentativas = 0;

        for (int i = 0; jaEsta == false;) {
            try {
                int soma = i + 1;
                listaDasTarefas[i] = new String(Files.readAllBytes(Paths.get("Tarefa " + soma)));
                i ++;
            } catch (IOException b) {
                tentativas ++;

                if (tentativas >= 3) {
                    jaEsta = true;

                    if (i == 0) {
                        JOptionPane.showMessageDialog(null, "Sem tarefas", "TO DO LIST", JOptionPane.WARNING_MESSAGE);
                        try {
                            main(null);
                        } catch (Exception a) {
                            JOptionPane.showMessageDialog(null, "Ocorreu um erro!\n" + a, "TO DO LIST", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, listaDasTarefas, "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
                        int apagar = JOptionPane.showConfirmDialog(null, "Apagar alguma tarefa?", "TO DO LIST", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (apagar == JOptionPane.YES_OPTION) {
                            String tarefaEscolhida = JOptionPane.showInputDialog(null, "Digite o numero da tarefa", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
            
                            try {
                                File tarefaParaApagar = new File("Tarefa " + tarefaEscolhida);
                
                                if (tarefaParaApagar.delete()) {
                                    JOptionPane.showMessageDialog(null, "Tarefa apagada com sucesso!", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Tarefa " + tarefaEscolhida + " nao foi encontrada", "TO DO LIST", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception a) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao apagar a tarefa" + a, "TO DO LIST", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            try {
                                main(null);
                            } catch (Exception a) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro!\n" + a, "TO DO LIST", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                } else {
                    jaEsta = false;
                }  
            }
        }
    }

    public static void imBored() {
        Random aleatorio = new Random();
        boolean jaEsta = false;
        int tentativas = 0;

        while (jaEsta == false) {
            int odd = aleatorio.nextInt(10);
            File tarefaSugerida = new File("Tarefa " + odd);

            if (tarefaSugerida.exists()) {
                try {
                    String tarefaDada = new String(Files.readAllBytes(Paths.get(tarefaSugerida + "")));
                    JOptionPane.showMessageDialog(null, "Tarefa sugerida:\n" + tarefaDada, "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
                    jaEsta = true;
                } catch (Exception d) {
                    
                }
            } else {
                tentativas ++;

                if (tentativas >= 10) {
                    JOptionPane.showMessageDialog(null, "Sem sugestões", "TO DO LIST", JOptionPane.WARNING_MESSAGE);
                    jaEsta = true;
                }
            }
        }
        int escolha = JOptionPane.showConfirmDialog(null, "Tentar denovo?", "TO DO LIST", JOptionPane.YES_NO_OPTION);

        if (escolha == JOptionPane.YES_OPTION) {
            imBored();
        } else {

        }
    }

    public static void reset() {
        int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza de que pretende apagar todas as tarefas?\nEsta acção não pode ser revertida!", "TO DO LIST", JOptionPane.YES_NO_OPTION);

        if (escolha == JOptionPane.YES_OPTION) {
            try {
                boolean jaEsta = false;
                int tentativas = 0;

                for (int i = 0; jaEsta == false; i ++) {
                    File tarefaParaApagar = new File("Tarefa " + i);

                    if (tarefaParaApagar.delete()) {

                    } else {
                        tentativas ++;

                        if (tentativas >= 5) {
                            jaEsta = true;
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Tarefas formatasas com sucesso!", "TO DO LIST", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, "Falha ao formatar as tarefas\n" + a, "TO DO LIST", JOptionPane.ERROR_MESSAGE);
            }
        } else {

        }
    }

    public static void sair() {
        int escolha = JOptionPane.showConfirmDialog(null, "Pretende sair?", "TO DO LIST", JOptionPane.WARNING_MESSAGE);

        switch (escolha) {
            case JOptionPane.YES_OPTION: System.exit(0);
            break;

            case JOptionPane.CANCEL_OPTION: try {
                    main(null);
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro!\n" + a, "TO DO LIST", JOptionPane.ERROR_MESSAGE);
                }
            break;

            case JOptionPane.CLOSED_OPTION: System.exit(0);
            break;

            default: System.exit(0);
            break;
        }
    }
}