package com.maonamassa.visualsystem.insideframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.visualsystem.insideframes.searchside.SearchPanel;
import com.maonamassa.visualsystem.insideframes.userside.UserInfoPanel;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;

public class InsideScreen extends JPanel {

    private static final double DIVIDER_RATIO = 0.70; // Proporção do painel esquerdo
    private JSplitPane splitPane; // Divisor responsivo entre os dois painéis

    public InsideScreen(MainFrame mainFrame) {
        Sessao sessao = LoginScreen.getSessao();

        if (sessao == null) {
            System.out.println("Erro: Sessão está nula!");
            return;
        }

        System.out.println("InsideScreen: Sessão válida, nome do usuário: " + sessao.getNome());

        setLayout(new BorderLayout()); // Layout principal responsivo

        boolean isProfessional = sessao.getIsProfissional();

        // Painéis
        SearchPanel searchPanel = new SearchPanel(isProfessional);
        UserInfoPanel userInfoPanel = new UserInfoPanel(mainFrame, sessao);

        // Configuração do JSplitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(searchPanel);
        splitPane.setRightComponent(userInfoPanel);
        splitPane.setResizeWeight(DIVIDER_RATIO); // Define a proporção inicial
        splitPane.setDividerSize(8); // Espessura do divisor
        splitPane.setContinuousLayout(true);

        // Adiciona o divisor à tela principal
        add(splitPane, BorderLayout.CENTER);

        // Listener para ajustar tamanhos ao redimensionar
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustDividerPosition();
            }
        });

        setVisible(true);
    }

    private void adjustDividerPosition() {
        int frameWidth = getWidth();
        int dividerPosition = (int) (frameWidth * DIVIDER_RATIO);
        splitPane.setDividerLocation(dividerPosition); // Recalcula a posição do divisor
    }

    // main para teste visual somente da tela InsideScreen
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.showScreen("InsideScreen");
    }
}
