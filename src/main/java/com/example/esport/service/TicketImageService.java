package com.example.esport.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;

public class TicketImageService {

    public static byte[] generateTicketImage(String nameUser, boolean isMultipass, String reservationDate, float price, int ticketId) throws IOException {
        // Dimensions de l'image
        int width = 600;
        int height = 300;

        System.out.println("Generating ticket image for user: " + nameUser);

        // Crée l'image de fond
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Configure le style de texte
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        // Ajoute le texte sur l'image
        g.drawString("Nom: " + nameUser, 20, 50);
        g.drawString("Tarif: " + price + "€", 20, 90);
        g.drawString(isMultipass ? "Multipass" : "Réservé le : " + reservationDate, 20, 130);

        // Génère l'URL du QR code (donnée encodée)
        String dataUrl = "https://tonapp.com/validate?ticket=" + ticketId;
        BufferedImage qrCode;
        try {
            String encodedData = URLEncoder.encode(dataUrl, StandardCharsets.UTF_8);
            qrCode = ImageIO.read(new URL("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + encodedData));
        } catch (IOException e) {
            System.err.println("QR code generation failed: " + e.getMessage());
            qrCode = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB); // Fallback image
            Graphics2D g2 = qrCode.createGraphics();
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(0, 0, 150, 150);
            g2.drawString("QR ERROR", 20, 75);
            g2.dispose();
        }

        // Dessine le QR code à droite
        g.drawImage(qrCode, width - 170, height / 2 - 75, null);
        g.dispose();

        // Retourne l'image sous forme de tableau d'octets
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        System.out.println("Image size: " + baos.toByteArray().length + " bytes");

        baos.flush();
        return baos.toByteArray(); // Retourne l'image en mémoire
    }
}
