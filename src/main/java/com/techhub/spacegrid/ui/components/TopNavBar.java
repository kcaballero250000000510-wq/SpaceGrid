/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techhub.spacegrid.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.techhub.spacegrid.util.UIConstants;

import java.awt.*;

public class TopNavBar extends JPanel {

    public TopNavBar() {
        setLayout(new BorderLayout(20, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UIConstants.BORDER_COLOR),
                new EmptyBorder(14, 32, 14, 32)
        ));

      
        JLabel brandLabel = new JLabel("<html><span style='font-size:18px; font-weight:800; color:#0f172a;'>Space</span><span style='font-size:18px; font-weight:800; color:#4f46e5;'>Grid</span></html>");
        brandLabel.setPreferredSize(new Dimension(140, 36));
        add(brandLabel, BorderLayout.WEST);

      
        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setBackground(new Color(241, 245, 249));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                new EmptyBorder(4, 12, 4, 8)
        ));

        JTextField searchField = new JTextField("Search by property name, location, or owner...");
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        searchField.setForeground(UIConstants.TEXT_MUTED);
        searchField.setBorder(null);
        searchField.setOpaque(false);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(226, 232, 240));
        searchBtn.setForeground(UIConstants.TEXT_DARK);
        searchBtn.setFocusPainted(false);
        searchBtn.setFont(new Font("SansSerif", Font.BOLD, 12));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        add(searchPanel, BorderLayout.CENTER);

       
        JPanel rightActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        rightActions.setOpaque(false);

        JButton switchBtn = new JButton("⇄  Switch to Landowner");
        switchBtn.setBackground(new Color(238, 242, 255));
        switchBtn.setForeground(UIConstants.PRIMARY_BLUE);
        switchBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        switchBtn.setFocusPainted(false);
        switchBtn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));

        JLabel userAvatar = new JLabel("Maria S. ▾");
        userAvatar.setFont(new Font("SansSerif", Font.BOLD, 13));
        userAvatar.setForeground(UIConstants.TEXT_DARK);
        userAvatar.setIcon(createCircleIcon(new Color(199, 210, 254), 28));

        rightActions.add(switchBtn);
        rightActions.add(userAvatar);
        add(rightActions, BorderLayout.EAST);
    }

    private Icon createCircleIcon(Color color, int size) {
        return new Icon() {
            public int getIconWidth() { return size; }
            public int getIconHeight() { return size; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillOval(x, y, size, size);
                g2.dispose();
            }
        };
    }
}