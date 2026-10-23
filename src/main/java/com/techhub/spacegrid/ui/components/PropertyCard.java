/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techhub.spacegrid.ui.components;

import com.techhub.spacegrid.model.Property;
import com.techhub.spacegrid.util.UIConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PropertyCard extends JPanel {

    public PropertyCard(Property property) {
        setLayout(new BorderLayout());
        setBackground(UIConstants.CARD_BG);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.BORDER_COLOR, 1, true),
                new EmptyBorder(0, 0, 14, 0)
        ));

    
        JPanel imgBanner = new JPanel(new BorderLayout());
        imgBanner.setPreferredSize(new Dimension(280, 140));
        imgBanner.setBackground(new Color(203, 213, 225));
        imgBanner.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel catBadge = new JLabel(" " + property.getCategory() + " ");
        catBadge.setOpaque(true);
        catBadge.setBackground(Color.WHITE);
        catBadge.setFont(new Font("SansSerif", Font.BOLD, 10));
        catBadge.setForeground(UIConstants.TEXT_DARK);

        JLabel statusBadge = new JLabel(" ● " + property.getStatusText() + " ");
        statusBadge.setOpaque(true);
        statusBadge.setBackground(property.getStatusColor());
        statusBadge.setForeground(Color.WHITE);
        statusBadge.setFont(new Font("SansSerif", Font.BOLD, 10));

        imgBanner.add(catBadge, BorderLayout.WEST);
        imgBanner.add(statusBadge, BorderLayout.EAST);
        add(imgBanner, BorderLayout.NORTH);

       
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(12, 14, 10, 14));

        JLabel blocksLbl = new JLabel("● " + property.getAvailableBlocks());
        blocksLbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        blocksLbl.setForeground(property.getStatusColor());
        infoPanel.add(blocksLbl);
        infoPanel.add(Box.createVerticalStrut(4));

        JLabel titleLbl = new JLabel(property.getTitle());
        titleLbl.setFont(UIConstants.FONT_TITLE);
        titleLbl.setForeground(UIConstants.TEXT_DARK);
        infoPanel.add(titleLbl);

        JLabel subtitleLbl = new JLabel(property.getLocation());
        subtitleLbl.setFont(UIConstants.FONT_BODY);
        subtitleLbl.setForeground(UIConstants.TEXT_MUTED);
        infoPanel.add(subtitleLbl);
        infoPanel.add(Box.createVerticalStrut(10));

        JLabel priceLbl = new JLabel("<html><span style='font-size:15px; font-weight:800; color:#0f172a;'>" 
                + property.getPriceRange() + "</span> <span style='font-size:11px; color:#64748b;'>" 
                + property.getPriceUnit() + "</span></html>");
        infoPanel.add(priceLbl);

        add(infoPanel, BorderLayout.CENTER);

     
        JPanel btnWrapper = new JPanel(new BorderLayout());
        btnWrapper.setOpaque(false);
        btnWrapper.setBorder(new EmptyBorder(0, 14, 0, 14));

        JButton detailsBtn = new JButton("View Space Details");
        detailsBtn.setBackground(new Color(241, 245, 249));
        detailsBtn.setForeground(UIConstants.TEXT_DARK);
        detailsBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        detailsBtn.setFocusPainted(false);
        detailsBtn.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        btnWrapper.add(detailsBtn, BorderLayout.CENTER);

        add(btnWrapper, BorderLayout.SOUTH);
    }
}