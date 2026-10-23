/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techhub.spacegrid.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.techhub.spacegrid.util.UIConstants;

import java.awt.*;

public class SidebarFilter extends JPanel {

    public SidebarFilter() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIConstants.CARD_BG);
        setPreferredSize(new Dimension(270, 700));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.BORDER_COLOR, 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

     
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("FILTER & SORT");
        title.setFont(new Font("SansSerif", Font.BOLD, 12));
        title.setForeground(UIConstants.TEXT_DARK);

        JLabel resetBtn = new JLabel("Reset All");
        resetBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        resetBtn.setForeground(UIConstants.PRIMARY_BLUE);
        resetBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(title, BorderLayout.WEST);
        header.add(resetBtn, BorderLayout.EAST);
        add(header);
        add(Box.createVerticalStrut(18));

     
        JLabel sortLabel = new JLabel("SORT BY");
        sortLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        sortLabel.setForeground(UIConstants.TEXT_MUTED);
        add(sortLabel);
        add(Box.createVerticalStrut(6));

        String[] sortOptions = {"Recommended", "Price: Low to High", "Price: High to Low", "Availability", "Newest"};
        JComboBox<String> sortCombo = new JComboBox<>(sortOptions);
        sortCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        sortCombo.setBackground(Color.WHITE);
        add(sortCombo);
        add(Box.createVerticalStrut(20));

      
        JLabel typeLabel = new JLabel("SPACE TYPE");
        typeLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        typeLabel.setForeground(UIConstants.TEXT_MUTED);
        add(typeLabel);
        add(Box.createVerticalStrut(8));

        add(createCheckboxRow("Commercial Lot", "12", true));
        add(createCheckboxRow("Night Market", "8", true));
        add(createCheckboxRow("Building / Complex", "5", false));
        add(createCheckboxRow("Open Space / Plaza", "7", false));
        add(Box.createVerticalStrut(20));

        
        JPanel sliderHeader = new JPanel(new BorderLayout());
        sliderHeader.setOpaque(false);
        JLabel sliderTitle = new JLabel("MIN. AVAIL BLOCKS");
        sliderTitle.setFont(new Font("SansSerif", Font.BOLD, 11));
        sliderTitle.setForeground(UIConstants.TEXT_MUTED);
        JLabel badgeVal = new JLabel(" 45 / 100 ");
        badgeVal.setFont(new Font("SansSerif", Font.BOLD, 11));
        badgeVal.setOpaque(true);
        badgeVal.setBackground(new Color(238, 242, 255));
        badgeVal.setForeground(UIConstants.PRIMARY_BLUE);

        sliderHeader.add(sliderTitle, BorderLayout.WEST);
        sliderHeader.add(badgeVal, BorderLayout.EAST);
        add(sliderHeader);
        add(Box.createVerticalStrut(6));

        JSlider slider = new JSlider(0, 100, 45);
        slider.setBackground(UIConstants.CARD_BG);
        slider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        add(slider);
        add(Box.createVerticalStrut(20));

       
        JPanel toggleRow = new JPanel(new BorderLayout());
        toggleRow.setOpaque(false);
        JLabel toggleLbl = new JLabel("<html><b>COMMERCIAL READY</b><br><small style='color:gray;'>Equipped with permits & power</small></html>");
        JCheckBox toggleSwitch = new JCheckBox();
        toggleSwitch.setSelected(true);
        toggleSwitch.setOpaque(false);
        toggleRow.add(toggleLbl, BorderLayout.WEST);
        toggleRow.add(toggleSwitch, BorderLayout.EAST);
        add(toggleRow);

        add(Box.createVerticalGlue());

    
        JButton applyBtn = new JButton("Apply Filters");
        applyBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        applyBtn.setBackground(UIConstants.PRIMARY_BLUE);
        applyBtn.setForeground(Color.WHITE);
        applyBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        applyBtn.setFocusPainted(false);
        applyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(applyBtn);
    }

    private JPanel createCheckboxRow(String label, String count, boolean checked) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        JCheckBox cb = new JCheckBox(label, checked);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cb.setForeground(UIConstants.TEXT_DARK);
        cb.setOpaque(false);

        JLabel countBadge = new JLabel(count);
        countBadge.setFont(new Font("SansSerif", Font.PLAIN, 12));
        countBadge.setForeground(UIConstants.TEXT_MUTED);

        row.add(cb, BorderLayout.WEST);
        row.add(countBadge, BorderLayout.EAST);
        return row;
    }
}