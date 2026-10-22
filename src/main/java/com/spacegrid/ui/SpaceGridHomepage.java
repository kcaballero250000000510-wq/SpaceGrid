/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spacegrid.ui;

import com.spacegrid.model.Property;
import com.spacegrid.ui.components.PropertyCard;
import com.spacegrid.ui.components.SidebarFilter;
import com.spacegrid.ui.components.TopNavBar;
import com.spacegrid.util.UIConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceGridHomepage extends JFrame {

    public SpaceGridHomepage() {
        setTitle("SpaceGrid - Public Discovery Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1380, 920);
        setMinimumSize(new Dimension(1100, 750));
        setLocationRelativeTo(null);
        getContentPane().setBackground(UIConstants.BG_LIGHT);
        setLayout(new BorderLayout());

     
        add(new TopNavBar(), BorderLayout.NORTH);

        
        JPanel mainContainer = new JPanel(new BorderLayout(24, 0));
        mainContainer.setBackground(UIConstants.BG_LIGHT);
        mainContainer.setBorder(new EmptyBorder(24, 32, 24, 32));

       
        mainContainer.add(new SidebarFilter(), BorderLayout.WEST);

       
        mainContainer.add(createListingContentArea(), BorderLayout.CENTER);

        add(mainContainer, BorderLayout.CENTER);
    }

    private JPanel createListingContentArea() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 16));
        wrapper.setOpaque(false);

      
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel mainHeading = new JLabel("<html><span style='font-size:22px; font-weight:800; color:#0f172a;'>Available properties</span><br><span style='font-size:12px; color:#64748b;'>matching your criteria • <b>6 spaces ready for reservation</b></span></html>");
        headerPanel.add(mainHeading, BorderLayout.WEST);

        JPanel tagPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        tagPanel.setOpaque(false);
        tagPanel.add(createPill("Type: Commercial & Night Market"));
        tagPanel.add(createPill("Blocks: ≥ 45"));
        headerPanel.add(tagPanel, BorderLayout.EAST);

        wrapper.add(headerPanel, BorderLayout.NORTH);

      
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 18, 18));
        gridPanel.setOpaque(false);

       
        List<Property> properties = getMockProperties();
        for (Property prop : properties) {
            gridPanel.add(new PropertyCard(prop));
        }

        wrapper.add(gridPanel, BorderLayout.CENTER);

       
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(12, 0, 0, 0));

        JLabel countText = new JLabel("Showing 1 to 6 of 24 properties");
        countText.setForeground(UIConstants.TEXT_MUTED);
        footer.add(countText, BorderLayout.WEST);

        JPanel pagination = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        pagination.setOpaque(false);
        pagination.add(new JButton("Previous"));
        JButton page1 = new JButton("1");
        page1.setBackground(UIConstants.PRIMARY_BLUE);
        page1.setForeground(Color.WHITE);
        pagination.add(page1);
        pagination.add(new JButton("2"));
        pagination.add(new JButton("3"));
        pagination.add(new JButton("Next"));
        footer.add(pagination, BorderLayout.EAST);

        wrapper.add(footer, BorderLayout.SOUTH);

        return wrapper;
    }

    private JLabel createPill(String text) {
        JLabel pill = new JLabel(text);
        pill.setFont(new Font("SansSerif", Font.PLAIN, 11));
        pill.setForeground(UIConstants.TEXT_MUTED);
        pill.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.BORDER_COLOR, 1, true),
                new EmptyBorder(4, 10, 4, 10)
        ));
        return pill;
    }

    private List<Property> getMockProperties() {
        List<Property> list = new ArrayList<>();
        list.add(new Property("Night Market", "Roxas Night Market – Section C", "Metro Commercial Zone, District 4", "48 Blocks Available", "₱90 - ₱150", "/ sq.m daily", UIConstants.GREEN_BADGE, "Open"));
        list.add(new Property("Commercial Lot", "Metro Commercial Plaza – Wing B", "Ortigas Center, Pasig City", "65 Blocks Available", "₱110 - ₱180", "/ sq.m daily", UIConstants.GREEN_BADGE, "Open"));
        list.add(new Property("Night Market", "Ayala Central Hub – Food Hall", "Makati Commercial Corridor", "46 Blocks Available", "₱95 - ₱140", "/ sq.m daily", new Color(245, 158, 11), "Few Left"));
        list.add(new Property("Commercial Lot", "Poblacion Outdoor Bazaar", "Poblacion, Makati City", "52 Blocks Available", "₱90 - ₱130", "/ sq.m daily", UIConstants.GREEN_BADGE, "Open"));
        list.add(new Property("Building Unit", "Northgate Tech Park Center", "Filinvest City, Alabang", "80 Blocks Available", "₱120 - ₱190", "/ sq.m daily", UIConstants.GREEN_BADGE, "Open"));
        list.add(new Property("Commercial Lot", "Greenhills Annex Commercial", "Greenhills, San Juan City", "50 Blocks Available", "₱90 - ₱150", "/ sq.m daily", UIConstants.GREEN_BADGE, "Open"));
        return list;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new SpaceGridHomepage().setVisible(true);
        });
    }
}