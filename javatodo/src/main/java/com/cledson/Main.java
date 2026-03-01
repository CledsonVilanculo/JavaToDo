package com.cledson;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    Color laranjaPrincipal = new Color(226, 103, 20);
    Color laranjaPalido = new Color(242, 155, 96);
    JFrame tela;
    Font fontePlana12 = new Font("Arial", Font.PLAIN, 12);
    Font fontePlana14 = new Font("Arial", Font.PLAIN, 14);
    JLabel fraseTitulo = new JLabel();
    JLabel nomesDosInputs = new JLabel();
    static JLabel fraseErro = new JLabel(); // este e estatico para poder ser acessado em Requests.java:28
    JLabel fraseSemConta = new JLabel();
    JLabel fraseSaudacao = new JLabel();
    JLabel userIcon = new JLabel();
    JLabel listaDeTarefas = new JLabel();
    JLabel bigUserIcon = new JLabel();
    JLabel userInfo = new JLabel();
    JLabel projetoCriadoPor = new JLabel("<html><strong>Este projeto foi criado por</strong></html>");
    JLabel devPic = new JLabel();
    JLabel devInfo = new JLabel("<html><strong>Cledson Vilanculo</strong><br>Estudante de TI, programador Java e web front-end apaixonado por tecnologia!</html>");
    JTextField nomeField = new JTextField();
    JTextField senhaField = new JTextField();
    JTextField confirmarSenhaField = new JTextField();
    JButton loginButton = new JButton();
    JButton alterarSenha = new JButton("Alterar senha");
    JButton meuGitHub = new JButton("Meu GitHub");
    JButton[] butoesTarefas = {
        new JButton("Nova tarefa"),
        new JButton("Editar tarefa"),
        new JButton("Apagar tarefa")
    };
    Border bordaBlack1 = BorderFactory.createLineBorder(Color.BLACK, 1);
    MouseAdapter criarContaAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            createCreateAccountScreen();
        }
    };
    MouseAdapter loginAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            createLoginScreen();
        }
    };
    MouseAdapter infoScreenAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            createInfoScreen();
        }
    };
    MouseAdapter mainScreenAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            createMainScreen();
        }
    };
    ActionListener createAccountListener;
    ActionListener loginListener;
    ActionListener trocarSenhaListener;
    ActionListener abrirGitHubListener;
    static String userName = null;
    JLayeredPane header;

    public static void main(String[] args) {
        Main main = new Main();
        main.tela = new JFrame("Java To Do v2");
        main.tela.setSize(640, 480);
        main.tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.tela.setResizable(false);
        main.tela.setLocationRelativeTo(null);
        main.tela.setLayout(null);
        main.tela.getContentPane().setBackground(Color.WHITE);
        main.tela.setIconImage(new ImageIcon(main.getClass().getResource("/icon.png")).getImage());

        main.header = new JLayeredPane();
        main.header.setOpaque(true);
        main.header.setBackground(main.laranjaPrincipal);
        main.header.setBounds(0, 0, main.tela.getWidth(), 60);

        JLabel titulo = new JLabel("<html><a style='font-size: 14pt; font-weight: bold'>Java To Do</a><br>Gerenciador de tarefas</html>");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(main.fontePlana12);
        titulo.setBounds(10, 0, 200, main.header.getHeight());
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        main.header.add(titulo);

        main.tela.add(main.header);
        main.tela.repaint();
        main.tela.revalidate();
        main.tela.setVisible(true);
        main.createLoginScreen();
    }

    public void createLoginScreen() {
        this.fraseTitulo.setText("Entrar na conta");
        this.fraseTitulo.setBounds(245, 100, 150, 20);
        this.fraseTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        this.fraseTitulo.setForeground(Color.BLACK);

        this.nomeField.setBounds(240, 130, 150, 25);
        this.nomeField.setFont(this.fontePlana14);
        this.nomeField.setForeground(Color.BLACK);
        this.nomeField.setBorder(this.bordaBlack1);

        this.senhaField.setBounds(240, 169, 150, 25);
        this.senhaField.setFont(this.fontePlana14);
        this.senhaField.setForeground(Color.BLACK);
        this.senhaField.setBorder(this.bordaBlack1);

        this.nomesDosInputs.setText("<html><p style='text-align: right'>Nome<br><br><br>Senha</p></html>");
        this.nomesDosInputs.setForeground(Color.BLACK);
        this.nomesDosInputs.setFont(this.fontePlana12);
        this.nomesDosInputs.setHorizontalAlignment(SwingConstants.LEFT);
        this.nomesDosInputs.setVerticalAlignment(SwingConstants.TOP);
        this.nomesDosInputs.setBounds(190, 130, 50, 60);

        this.loginButton.setText("Entrar na conta");
        this.loginButton.setForeground(Color.BLACK);
        this.loginButton.setBackground(this.laranjaPalido);
        this.loginButton.setBounds(252, 208, 125, 24);
        this.loginButton.setFocusPainted(false);
        this.loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.loginButton.setBorder(null);
        this.loginButton.setFont(this.fontePlana12);

        fraseErro.setText(null);
        fraseErro.setFont(this.fontePlana12);
        fraseErro.setBounds(195, 285, 250, 20);
        fraseErro.setHorizontalAlignment(SwingConstants.CENTER);

        this.createAccountListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomeField.getText().length() >= 3 && senhaField.getText().length() >= 3) {
                    if (Requests.checkAccount(nomeField.getText())) {
                        if (Requests.checkPassword(nomeField.getText(), senhaField.getText())) {
                            fraseErro.setForeground(Color.BLACK);
                            fraseErro.setText(null);

                            // Remover toda a tela de login
                            tela.remove(fraseErro);
                            tela.remove(fraseSemConta);
                            tela.remove(loginButton);
                            tela.remove(nomesDosInputs);
                            tela.remove(senhaField);
                            tela.remove(nomeField);
                            tela.remove(fraseTitulo);
                            tela.repaint();
                            tela.revalidate();

                            userName = nomeField.getText();
                            createMainScreen();
                        } else {
                            fraseErro.setForeground(Color.RED);
                            fraseErro.setText("<html><p style='text-align: center'>A senha está incorreta</p></html>");
                        }
                    } else {
                        fraseErro.setForeground(Color.RED);
                        fraseErro.setText("<html><p style='text-align: center'>A conta não foi encontrada</p></html>");
                    }
                } else if (nomeField.getText().length() < 3) {
                    fraseErro.setForeground(Color.RED);
                    fraseErro.setText("O nome deve ter pelo menos 3 caracteres!");
                } else if (senhaField.getText().length() < 3) {
                    fraseErro.setForeground(Color.RED);
                    fraseErro.setText("A senha deve ter pelo menos 3 caracteres");
                }
            }
        };
        this.loginButton.removeActionListener(this.loginListener);
        this.loginButton.addActionListener(this.createAccountListener);

        this.fraseSemConta.setText("<html><p style='text-align: center'>Ainda não tem uma conta?<br><a style='text-decoration: underline'>Clique aqui</a></p></html>");
        this.fraseSemConta.setForeground(Color.BLACK);
        this.fraseSemConta.setFont(this.fontePlana12);
        this.fraseSemConta.setBounds(245, 240, 150, 40);
        this.fraseSemConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.fraseSemConta.removeMouseListener(this.loginAdapter);
        this.fraseSemConta.addMouseListener(this.criarContaAdapter);

        this.tela.remove(this.confirmarSenhaField);
        this.tela.add(fraseErro);
        this.tela.add(this.fraseSemConta);
        this.tela.add(this.loginButton);
        this.tela.add(this.nomesDosInputs);
        this.tela.add(this.senhaField);
        this.tela.add(this.nomeField);
        this.tela.add(this.fraseTitulo);
        this.tela.repaint();
        this.tela.revalidate();
    }

    public void createCreateAccountScreen() {
        this.fraseTitulo.setText("Criar conta");
        this.fraseTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        this.nomesDosInputs.setBounds(this.nomesDosInputs.getX() - 60, this.nomesDosInputs.getY(), this.nomesDosInputs.getWidth() + 50, this.nomesDosInputs.getHeight() + 50);
        this.nomesDosInputs.setText("<html><p style='text-align: right'>Nome<br><br><br>Senha<br><br><br>Confirme a senha</p></html>");

        this.fraseSemConta.setHorizontalAlignment(SwingConstants.CENTER);
        this.fraseSemConta.setText("<html><p style='text-align: center'>Já tem uma conta?<br><a style='text-decoration: underline'>Clique aqui</a></p></html>");
        this.fraseSemConta.setLocation(this.fraseSemConta.getX(), this.fraseSemConta.getY() + 40);
        this.fraseSemConta.removeMouseListener(this.criarContaAdapter);
        this.fraseSemConta.addMouseListener(this.loginAdapter);

        this.confirmarSenhaField.setFont(this.fontePlana14);
        this.confirmarSenhaField.setBorder(this.bordaBlack1);
        this.confirmarSenhaField.setBounds(240, 208, 150, 25);

        fraseErro.setSize(fraseErro.getWidth(), 40);
        this.loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomeField.getText().length() >= 3 && senhaField.getText().length() >= 3 && confirmarSenhaField.getText().length() >= 3) {
                    if (senhaField.getText().equals(confirmarSenhaField.getText())) {
                                        boolean contaCriada = Requests.requestNewAccount(nomeField.getText(), senhaField.getText());

                        if (contaCriada) {
                            JOptionPane.showMessageDialog(tela, "Conta criada com sucesso! Agora pode fazer login");
                            createLoginScreen();
                        } else {
                            if (!fraseErro.getText().contains("Já existe uma conta")) { // para o caso de dar o catch em Requests.java:25
                                fraseErro.setForeground(Color.RED);
                                fraseErro.setText("<html><p style='text-align: center'>Erro ao criar a conta<br>Por favor tente novamente</p></html>");
                            }
                        }
                    } else {
                        fraseErro.setForeground(Color.RED);
                        fraseErro.setText("As senhas digitadas devem ser iguais!");
                    }
                } else if (nomeField.getText().length() < 3) {
                    fraseErro.setForeground(Color.RED);
                    fraseErro.setText("<html><p style='text-align: center'>O nome deve ter pelo menos<br>3 caracteres</p></html>");
                } else if (senhaField.getText().length() < 3) {
                    fraseErro.setForeground(Color.RED);
                    fraseErro.setText("<html><p style='text-align: center'>A senha deve ter pelo menos<br>3 caracteres</p></html>");
                } else if (confirmarSenhaField.getText().length() < 3) {
                    fraseErro.setForeground(Color.RED);
                    fraseErro.setText("Por favor confirme a sua senha");
                }
            }
        };
        this.loginButton.setText("Criar conta");
        this.loginButton.setLocation(this.loginButton.getX(), this.loginButton.getY() + 40);
        this.loginButton.removeActionListener(this.createAccountListener);
        this.loginButton.addActionListener(this.loginListener);

        fraseErro.setText(null);
        fraseErro.setLocation(fraseErro.getX(), fraseErro.getY() + 50);

        this.tela.add(this.confirmarSenhaField);
        this.tela.repaint();
        this.tela.revalidate();
    }

    public void createMainScreen() {
        // limpar a tela de infoScreen caso ela ja tenha sido criada
        this.alterarSenha.removeActionListener(this.trocarSenhaListener);
        this.meuGitHub.removeActionListener(this.abrirGitHubListener);
        
        this.tela.remove(this.meuGitHub);
        this.tela.remove(this.devInfo);
        this.tela.remove(this.projetoCriadoPor);
        this.tela.remove(this.devPic);
        this.tela.remove(this.alterarSenha);
        this.tela.remove(this.userInfo);
        this.tela.remove(this.bigUserIcon);

        this.fraseSaudacao.setText("<html>" + App.getDayPeriod() + "<strong>" + Main.userName + "</strong></html>");
        this.fraseSaudacao.setBounds(this.header.getWidth() - 285, 0, 200, this.header.getHeight());
        this.fraseSaudacao.setForeground(Color.WHITE);
        this.fraseSaudacao.setFont(fontePlana12);
        this.fraseSaudacao.setVerticalAlignment(SwingConstants.CENTER);
        this.fraseSaudacao.setHorizontalAlignment(SwingConstants.RIGHT);

        this.userIcon.setBounds(this.header.getWidth() - 80, 0, 40, this.header.getHeight());
        this.userIcon.setIcon(new ImageIcon(this.getClass().getResource("/images/userIcon.png")));
        this.userIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.userIcon.removeMouseListener(this.infoScreenAdapter);
        this.userIcon.addMouseListener(this.infoScreenAdapter);

        this.listaDeTarefas.setText(App.getFormatedTasks());
        this.listaDeTarefas.setBounds(10, this.header.getHeight() + 5, this.tela.getWidth() - 180, this.tela.getHeight() - this.header.getHeight() - 60);
        this.listaDeTarefas.setFont(this.fontePlana12);
        this.listaDeTarefas.setForeground(Color.BLACK);
        this.listaDeTarefas.setVerticalAlignment(SwingConstants.TOP);

        // aqueles butoes alinhados de apaga, editar e criar nova tarefa
        for (int i = 0; i < this.butoesTarefas.length; i ++) {
            this.butoesTarefas[i].setBounds(this.tela.getWidth() - 160, this.header.getHeight() + 10 + (i * 32), 130, 25);
            this.butoesTarefas[i].setBackground(Color.WHITE);
            this.butoesTarefas[i].setForeground(Color.BLACK);
            this.butoesTarefas[i].setBorder(bordaBlack1);
            this.butoesTarefas[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.butoesTarefas[i].setFont(this.fontePlana12);
            this.butoesTarefas[i].setFocusPainted(false);
            this.tela.add(this.butoesTarefas[i]);
        }

        if (this.butoesTarefas[0].getActionListeners().length == 0) { // para evitar que adicione varios listeners
            this.butoesTarefas[0].addActionListener(_ -> newTask());
            this.butoesTarefas[1].addActionListener(_ -> editTask());
            this.butoesTarefas[2].addActionListener(_ -> deleteTask());
        }

        if (!this.header.isAncestorOf(this.userIcon)) {
            this.header.add(this.userIcon);
            this.header.add(this.fraseSaudacao);
        }

        this.tela.add(this.listaDeTarefas);
        this.tela.repaint();
        this.tela.revalidate();
    }


    public void newTask() {
        // o this.butoesTarefas[0] serve para ficar perto do butao de adicionar tarefa
        String novaTarefa = JOptionPane.showInputDialog(this.butoesTarefas[0], "Digite a nova tarefa");

        if (novaTarefa != null) {
            boolean tarefaAdicionada = Requests.addTask(Main.userName, novaTarefa);

            if (tarefaAdicionada) {
                JOptionPane.showMessageDialog(this.butoesTarefas[0], "Tarefa adicionada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this.butoesTarefas[0], "Ocorreu um erro ao adiiconar a tarefa");
            }

            this.listaDeTarefas.setText(App.getFormatedTasks());
        }
    }

    public void editTask() {
        try {
            String tarefaParaEditar = JOptionPane.showInputDialog(this.butoesTarefas[1], "Digite o numero da tarefa que deseja editar");

            if (tarefaParaEditar != null) {
                String tarefaAtualizada = JOptionPane.showInputDialog(this.butoesTarefas[1], "Digite o novo conteudo da tarefa escolhida");
                if (tarefaAtualizada != null) {
                    int tarefaEscolhida = Integer.parseInt(tarefaParaEditar);
                    String[] listaDeTarefas = Requests.getTasks(Main.userName);
                    listaDeTarefas[tarefaEscolhida - 1] = tarefaAtualizada;
                    String tarefasFormatadas = "";

                    for (String tarefa : listaDeTarefas) {
                        tarefasFormatadas += tarefa + "%"; // formatando para guardar na base de dados
                    }

                    boolean tarefaEditada = Requests.deleteTask(Main.userName, tarefasFormatadas);
                    if (tarefaEditada) {
                        JOptionPane.showMessageDialog(this.butoesTarefas[1], "Tarefa editada com sucesso!");
                        this.listaDeTarefas.setText(App.getFormatedTasks());
                    } else {
                        JOptionPane.showMessageDialog(this.butoesTarefas[1], "Ocorreu um erro ao editar a tarefa");
                    }
                }
            }
        } catch (NumberFormatException _) {
            // para o caso do usuario digitar algo que nao e um numero na linha 345
            JOptionPane.showMessageDialog(this.butoesTarefas[1], "Digite um numero válido", null, JOptionPane.WARNING_MESSAGE);
        } catch (NullPointerException _) {
            // para o caso de listaDeTarefas ser null na linha 349
            JOptionPane.showMessageDialog(this.butoesTarefas[1], "A sua lista de tarefas está vazia");
        } catch (ArrayIndexOutOfBoundsException _) {
            // para o caso do usuario digitar um numero que nao esta na lista na linha 350
            JOptionPane.showMessageDialog(this.butoesTarefas[1], "A tarefa escolhida não foi encontrada");
        }
    }

    public void deleteTask() {
        try {
                int tarefaParaApagar = Integer.parseInt(JOptionPane.showInputDialog(this.butoesTarefas[2], "Digite o número da tarefa para apagar:"));
            String[] listaDeTarefas = Requests.getTasks(Main.userName);

            String tarefas = "";
            for (int i = 0; i < listaDeTarefas.length; i ++) {
                if (i != tarefaParaApagar - 1) {
                    tarefas += listaDeTarefas[i] + "%";
                }
            }

            boolean tarefaApagada = Requests.deleteTask(Main.userName, tarefas);

            if (tarefaApagada) {
                JOptionPane.showMessageDialog(this.butoesTarefas[2], "Tarefa apagada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this.butoesTarefas[2], "Ocorreu um erro ao apagar a tarefa");
            }

            this.listaDeTarefas.setText(App.getFormatedTasks());
            this.tela.repaint();
            this.tela.revalidate();
        } catch (NumberFormatException _) {
            JOptionPane.showMessageDialog(this.butoesTarefas[2], "Por favor digite um número válido");
        }
    }

    public void createInfoScreen() {
        // tirar os butoes de criar, editar e apagar tarefa
        for (JButton butaoTarefa : this.butoesTarefas) {
            this.tela.remove(butaoTarefa);
        }


        this.userIcon.removeMouseListener(this.infoScreenAdapter);
        this.userIcon.addMouseListener(this.mainScreenAdapter);
        this.userIcon.setIcon(new ImageIcon(this.getClass().getResource("/images/tasksIcon.png")));

        this.bigUserIcon.setIcon(new ImageIcon(this.getClass().getResource("/images/bigUserIcon.png")));
        this.bigUserIcon.setBounds(10, this.header.getHeight() + 10, 80, 80);

        this.userInfo.setText("<html><strong>" + Main.userName + "</strong><br>" + App.getNumberOfTasks() + "</html>");
        this.userInfo.setBounds(this.bigUserIcon.getX() + this.bigUserIcon.getWidth() + 10, this.bigUserIcon.getY(), 250, this.bigUserIcon.getHeight());
        this.userInfo.setFont(this.fontePlana12);
        this.userInfo.setForeground(Color.BLACK);

        this.alterarSenha.setBounds(10, this.userInfo.getY() + this.userInfo.getHeight() + 10, 100, 25);
        this.alterarSenha.setForeground(Color.BLACK);
        this.alterarSenha.setBackground(this.laranjaPalido);
        this.alterarSenha.setFont(this.fontePlana12);
        this.alterarSenha.setFocusPainted(false);
        this.alterarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.alterarSenha.setBorder(null);
        this.trocarSenhaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaSenha = JOptionPane.showInputDialog(alterarSenha, "Digite a senha que deseja usar a partir de agora", "Alterar senha", JOptionPane.INFORMATION_MESSAGE);

                if (novaSenha != null) {
                    if (novaSenha.length() >= 3) {
                        boolean senhaTrocada = Requests.passwordChanged(userName, novaSenha);

                        if (senhaTrocada) {
                            JOptionPane.showMessageDialog(alterarSenha, "Senha trocada com sucesso! A sua nova senha é: " + novaSenha);
                        }
                    } else {
                        JOptionPane.showMessageDialog(alterarSenha, "A senha deve ter pelo menos 3 caracteres!");
                    }
                }
            }
        };
        this.alterarSenha.addActionListener(this.trocarSenhaListener);

        this.devPic.setIcon(new ImageIcon(this.getClass().getResource("/images/devPic.png")));
        this.devPic.setBounds(10, this.tela.getHeight() - 130, 60, 60);

        this.projetoCriadoPor.setBounds(10, this.devPic.getY() - 30, 200, 20);
        this.projetoCriadoPor.setFont(this.fontePlana14);
        this.projetoCriadoPor.setForeground(Color.BLACK);

        this.devInfo.setBounds(this.devPic.getX() + this.devPic.getWidth() + 10, this.devPic.getY() + 10, 250, 40);
        this.devInfo.setFont(this.fontePlana12);
        this.devInfo.setForeground(Color.BLACK);

        this.meuGitHub.setBounds(this.devInfo.getX() + this.devInfo.getWidth() + 10, this.devInfo.getY() + 10, 100, 25);
        this.meuGitHub.setFont(this.fontePlana12);
        this.meuGitHub.setBackground(this.laranjaPalido);
        this.meuGitHub.setBorder(null);
        this.meuGitHub.setFocusPainted(false);
        this.meuGitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.meuGitHub.setForeground(Color.BLACK);
        this.abrirGitHubListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.openGitHub();
            }
        };
        this.meuGitHub.addActionListener(this.abrirGitHubListener);

        this.tela.add(this.meuGitHub);
        this.tela.add(this.devInfo);
        this.tela.add(this.projetoCriadoPor);
        this.tela.add(this.devPic);
        this.tela.add(this.alterarSenha);
        this.tela.add(this.userInfo);
        this.tela.add(this.bigUserIcon);
        this.tela.remove(this.listaDeTarefas);
        this.tela.repaint();
        this.tela.revalidate();
    }
}