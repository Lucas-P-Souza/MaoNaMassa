package com.maonamassa.visualsystem.insideframes;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

import com.maonamassa.usersystem.Sessao;
import com.maonamassa.visualsystem.firstinteraction.MainFrame;
import com.maonamassa.visualsystem.insideframes.searchside.SearchPanel;
import com.maonamassa.visualsystem.insideframes.userside.UserInfoPanel;
import com.maonamassa.visualsystem.firstinteraction.LoginScreen;

public class InsideScreen extends JPanel {  

    private static final double DIVIDER_RATIO = 0.70; // Proporção do painel esquerdo

    public InsideScreen(MainFrame mainFrame) {

        Sessao sessao = LoginScreen.getSessao();  

        if (sessao == null) {
            System.out.println("Erro: Sessão está nula!");
            return;
        }

        System.out.println("InsideScreen: Sessão válida, nome do usuário: " + sessao.getNome());

        setLayout(null); // Usando layout absoluto

        boolean isProfessional = sessao.getIsProfissional();

        SearchPanel searchPanel = new SearchPanel(isProfessional);
        UserInfoPanel userInfoPanel = new UserInfoPanel(mainFrame, sessao);

        // Inicializa os tamanhos
        adjustPanelSizes(mainFrame, searchPanel, userInfoPanel);

        // Adiciona os painéis
        add(searchPanel);
        add(userInfoPanel);

        // Listener para redimensionamento
        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustPanelSizes(mainFrame, searchPanel, userInfoPanel);
            }
        });

        setVisible(true);
    }

    private void adjustPanelSizes(MainFrame mainFrame, JPanel searchPanel, JPanel userInfoPanel) {
        int frameWidth = mainFrame.getWidth();
        int frameHeight = mainFrame.getHeight();

        int leftPanelWidth = (int) (frameWidth * DIVIDER_RATIO);
        int rightPanelWidth = frameWidth - leftPanelWidth;

        // Atualiza tamanhos e posições
        searchPanel.setBounds(0, 0, leftPanelWidth, frameHeight);
        userInfoPanel.setBounds(leftPanelWidth, 0, rightPanelWidth, frameHeight);
    }

    // main para teste visual somente da tela InsideScreen
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.showScreen("InsideScreen");
    }
}
