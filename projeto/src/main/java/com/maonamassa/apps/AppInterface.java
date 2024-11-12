package com.maonamassa.apps;

import javax.swing.SwingUtilities;

import com.maonamassa.visualsystem.firstinteraction.MainFrame;

public class AppInterface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
