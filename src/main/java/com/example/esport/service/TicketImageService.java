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
        int width = 600;
        int height = 300;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        g.drawString("Nom: " + nameUser, 20, 50);
        g.drawString("Tarif: " + price + "€", 20, 90);
        g.drawString(isMultipass ? "Multipass" : "Réservé pour le : " + reservationDate, 20, 130);

        String dataUrl = "https://youtu.be/dQw4w9WgXcQ?si=f_nLKI5lKkxIRSo8&t=" + ticketId;
        String encodedData = URLEncoder.encode(dataUrl, StandardCharsets.UTF_8);
        BufferedImage qrCode = ImageIO.read(new URL("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + encodedData));

        g.drawImage(qrCode, width - 170, height / 2 - 75, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
