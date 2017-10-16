package com.company;

import javax.swing.*;
import java.awt.*;

public class LinesDrawingExample extends JFrame {

    public static final int SIZE = 500;
    public static final int LINE_WIDTH = 1;
    public static final float STEP = 0.05f;
    public static final float Z = 0.5f;

    private IsometricTransformer isometricTransformer = new IsometricTransformer(new Point2D(SIZE / 2, SIZE / 2), 100, -135, 60);


    public LinesDrawingExample() {
        super("Lab 2");

        setSize(SIZE, SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinesDrawingExample().setVisible(true);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(LINE_WIDTH));
        figure1(g2d);
    }

    private void figure1(Graphics2D g2d) {
        g2d.clearRect(0, 0, SIZE, SIZE);
        drawLine(g2d, new Point3D(3, 0, 0), new Point3D(0, 0, 0));
        drawLine(g2d, new Point3D(0, 3, 0), new Point3D(0, 0, 0));
        drawLine(g2d, new Point3D(0, 0, 3), new Point3D(0, 0, 0));
        for (float i = -1; i < 1; i += STEP) {

            float y1 = i;
            float y2 = y1 + STEP;
            float x1 = f(y1);
            float x2 = f(y2);
            drawLine(g2d, new Point3D(x1, y1, Z), new Point3D(x2, y2, Z));
            drawLine(g2d, new Point3D(x1, y1, -Z), new Point3D(x2, y2, -Z));
            drawLine(g2d, new Point3D(x1, y1, 0), new Point3D(x2, y2, 0));
//            drawLine(g2d, new Point3D(-x1, y1, Z), new Point3D(-x2, y2, Z));
//            drawLine(g2d, new Point3D(-x1, y1, -Z), new Point3D(-x2, y2, -Z));

//            drawLine(g2d, new Point3D(-x1, y1, -Z), new Point3D(-x1, y1, Z));
            drawLine(g2d, new Point3D(x1, y1, -Z), new Point3D(x1, y1, Z));
        }
    }

    private void figure2(Graphics2D g2d) {

    }

    private float f(float y) {
        return y * y;
//        return (float) Math.sqrt(1 + y * y);
    }

    private void drawLine(Graphics2D graphics, Point3D start3D, Point3D end3D) {
        Point2D start = isometricTransformer.transform(start3D);
        Point2D end = isometricTransformer.transform(end3D);
        graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    private void drawPoint(Graphics2D graphics, Point3D point3D) {
        drawLine(graphics, point3D, point3D);
    }
}
