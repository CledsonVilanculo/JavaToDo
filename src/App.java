import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class App{
    public static void main(String[] args) throws Exception {
        System.out.println("========== TO DO LIST ==========");
        System.out.println("1. Nova tarefa\n2. Ver tarefas\n3. Estou entediado\n4. Formatar tarefas");

        Scanner escanor = new Scanner(System.in);
        String escolha = escanor.nextLine();
        
        switch (escolha) {
            case "1": newTask();
                break;
            
            case "2": seeTasks();
                break;
            
            case "3": imBored();
                break;
            
            case "4": reset();
                break;
            
            default: System.out.println("Opcao nao encontrada!");
        }
        escanor.close();
    }

    public static void newTask() {
        System.out.println("\nDigite a nova tarefa:");
        Scanner escanor = new Scanner(System.in);
        String novaTarefa = escanor.nextLine();

        boolean jaEsta = false;
        for (int i = 0; jaEsta == false; i ++) {
            try {
                File novoTXT = new File("Tarefa " + i);
                if (novoTXT.exists()) {

                } else {
                    if (novoTXT.createNewFile()) {
                        jaEsta = true;
                        FileWriter escritor = new FileWriter(novoTXT);
                        escritor.write(novaTarefa);
                        escritor.close();
                    }
                }
            } catch (IOException a) {
                System.out.println("Ocorreu um erro ao criar a tarefa\n" + a);
            }
        }
        System.out.println("Tarefa guardada com sucesso!");
        escanor.close();
    }

    public static void seeTasks() {
        System.out.println("\nAs tuas tarefas:");
        boolean jaEsta = false;
        String listaDasTarefas = "SEM TAREFAS";
        int tentativas = 0;

        for (int i = 0; jaEsta == false; i ++) {
            try {
                listaDasTarefas = new String(Files.readAllBytes(Paths.get("Tarefa " + i)));
                System.out.println(i + " - " + listaDasTarefas);
            } catch (IOException b) {
                tentativas ++;

                if (tentativas >= 3) {
                    jaEsta = true;
                } else {
                    jaEsta = false;

                    if (i == 0) {
                        System.out.println("SEM TAREFAS!");
                    }
                }  
            }
        }

        System.out.println("\nApagar alguma tarefa?(S / N)");
        Scanner escanor = new Scanner(System.in);
        String escolha = escanor.nextLine();

        switch (escolha.toUpperCase()) {
            case "S": System.out.println("Digite o numero da tarefa");
                String tarefaEscolhida = escanor.nextLine();

                try {
                    File tarefaParaApagar = new File("Tarefa " + tarefaEscolhida);
                    if (tarefaParaApagar.delete()) {
                        System.out.println("\nTarefa apagada com sucesso!");
                    } else {
                        System.out.println("\nOcorreu um erro ao apagar a tarefa escolhida (" + tarefaEscolhida + ")");
                    }
                } catch (Exception a) {
                    System.out.println("Ocorreu um erro ao apagar a tarefa\n" + a);
                }
                break;
                    
            case "N": break;

            default: System.out.println("Opcao nao encontrada");
        }
        escanor.close();
    }

    public static void imBored() {
        System.out.println("\nTarefa sugerida: ");
        Random aleatorio = new Random();
        boolean jaEsta = false;

        while (jaEsta == false) {
            int odd = aleatorio.nextInt(100);
            File tarefaSugerida = new File("Tarefa " + odd);
            if (tarefaSugerida.exists()) {
                try {
                    String tarefaDada = new String(Files.readAllBytes(Paths.get(tarefaSugerida + "")));
                    System.out.println(tarefaDada);
                    jaEsta = true;
                } catch (Exception d) {

                }
            }
        }
        System.out.println("\nTentar denovo? (S / N)");

        Scanner escanor = new Scanner(System.in);
        String escolha = escanor.nextLine();

        switch (escolha.toUpperCase()) {
            case "S": imBored();
                break;
            
            case "N": break;

            default: break;
        }
        escanor.close();
    }

    public static void reset() {
        System.out.println("\nTem certeza de que pretende apagar todas as tarefas? (S / N)");
        Scanner escanor = new Scanner(System.in);
        String escolha = escanor.nextLine();

        switch (escolha.toUpperCase()) {
            case "S": try {
                boolean jaEsta = false;
                int tentativas = 0;

                for (int i = 0; jaEsta == false; i ++) {
                    File tarefaParaApagar = new File("Tarefa " + i);
                    
                    if (tarefaParaApagar.delete()) {

                    } else {
                        tentativas ++;
                        
                        if (tentativas >= 3) {
                            jaEsta = true;
                        }
                    }
                }

                System.out.println("\nTarefas formatadas com sucesso!");
            } catch (Exception c) {
                System.out.println("\nFalha ao formatar as tarefas\n" + c);
            }
            break;

            case "N": break;

            default: break;
        }
        escanor.close();
    }
}