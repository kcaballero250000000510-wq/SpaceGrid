package com.techhub.spacegrid.ui;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;

public class ImageUtils {

    public static ImageIcon getPropertyImage(int propertyId, int width, int height) {
        String path = "src/main/resources/images/properties/" + propertyId + ".jpg";
        File imageFile = new File(path);

        // Use placeholder if specific property image is missing
        if (!imageFile.exists()) {
            path = "src/main/resources/images/properties/placeholder.jpg";
        }

        // Scale image smoothly to fit card size
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        return new ImageIcon(scaledImage);
    }
}