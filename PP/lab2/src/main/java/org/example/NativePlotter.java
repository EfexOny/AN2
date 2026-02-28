package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;

public class NativePlotter {

    public static void drawPlot(int[] x, int[] y, double m, double c, String fileName) {
        int width = 800;
        int height = 600;
        int pad = 60;

        int minX = Arrays.stream(x).min().orElse(0) - 1;
        int maxX = Arrays.stream(x).max().orElse(20) + 1;
        int minY = Arrays.stream(y).min().orElse(0) - 5;
        int maxY = Arrays.stream(y).max().orElse(120) + 5;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i <= 10; i++) {
            int xLine = pad + i * (width - 2 * pad) / 10;
            int yLine = (height - pad) - i * (height - 2 * pad) / 10;
            g2.drawLine(xLine, pad, xLine, height - pad);
            g2.drawLine(pad, yLine, width - pad, yLine);
        }

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(pad, height - pad, width - pad, height - pad); // Axa X
        g2.drawLine(pad, pad, pad, height - pad);             // Axa Y

        g2.setColor(new Color(41, 128, 185)); // Albastru
        for (int i = 0; i < x.length; i++) {
            int px = scaleX(x[i], minX, maxX, width, pad);
            int py = scaleY(y[i], minY, maxY, height, pad);
            g2.fillOval(px - 5, py - 5, 10, 10);
        }

        g2.setColor(new Color(192, 57, 43)); // Roșu
        g2.setStroke(new BasicStroke(3));

        int xStart = minX;
        int yStart = (int) (m * xStart + c);
        int xEnd = maxX;
        int yEnd = (int) (m * xEnd + c);

        g2.drawLine(
                scaleX(xStart, minX, maxX, width, pad),
                scaleY(yStart, minY, maxY, height, pad),
                scaleX(xEnd, minX, maxX, width, pad),
                scaleY(yEnd, minY, maxY, height, pad)
        );

        g2.dispose();

        try {
            File outFile = new File(fileName + ".png");
            ImageIO.write(img, "png", outFile);
            System.out.println("Saved at: " + outFile.getAbsolutePath());
            Runtime.getRuntime().exec("xdg-open " + outFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    // Metode de scalare (Transformare liniară: Matematică -> Pixeli)
    private static int scaleX(double val, int min, int max, int w, int p) {
        return (int) (p + (val - min) * (w - 2 * p) / (max - min));
    }

    private static int scaleY(double val, int min, int max, int h, int p) {
        return (int) ((h - p) - (val - min) * (h - 2 * p) / (max - min));
    }
}