package com.maonamassa.visualsystem.firstinteraction;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.maonamassa.visualsystem.profileandsearch.InsideScreen;

public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;

    public MainFrame() {

        setTitle("Nome do App");
        setSize(1500, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Maximiza a janela (sem ser tela cheia)
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Isso maximiza a janela para ocupar toda a tela
        setVisible(true);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Adiciona as telas ao CardLayout
        add(new FirstScreen(this), "FirstScreen");
        add(new RegisterScreen(this), "RegisterScreen");
        add(new LoginScreen(this), "LoginScreen");
        add(new InsideScreen(this), "InsideScreen");  // Tela interna após login

        // Exibe a primeira tela
        cardLayout.show(getContentPane(), "FirstScreen");

        // Botão de alternância para tela cheia
        JButton fullScreenButton = new JButton("Tela Cheia");
        fullScreenButton.addActionListener(e -> toggleFullScreen());

        // Adiciona o botão à barra de título (ou outro painel, se preferir)
        add(fullScreenButton, "North");
    }

    // Método para trocar telas
    public void showScreen(String screenName) {
        cardLayout.show(getContentPane(), screenName);
    }

    // Alterna entre modo janela e tela cheia
    private void toggleFullScreen() {
        // Este método pode ser mantido se você quiser permitir que o usuário entre em modo tela cheia
        // Se não, você pode removê-lo.
    }
}
