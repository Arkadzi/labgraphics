package com.company;

public class IsometricTransformer {
//
//    private final double angle, scale;
//    private final double[][] transformation;
    private Point2D center;
    private int scale;

    //
    public IsometricTransformer(Point2D center, int scale, double angleFi, double angleTeta) {
        this.center = center;
        this.scale = scale;
        setFi(angleFi);
        setTeta(angleTeta);

//        angle = -Math.PI / 6;
//        scale = 100;
//        transformation = new double[][]{
//                {
//                        scale * Math.cos(angle),
//                        scale * Math.sin(angle)
//                },
//                {
//                        scale * Math.cos(Math.PI - angle),
//                        scale * Math.sin(Math.PI - angle)}
//        };
//
    }
//
//    public Point2D transform(Point3D point) {
//        return new Point2D(
//                (float) (center.x + point.x * transformation[0][0] + point.y * transformation[1][0]),
//                (float) (center.y - point.x * transformation[0][1] - point.y * transformation[1][1] - (point.z * scale))
//        );
//
//    }

    private double angleFi;
    private double angleTeta;
    private double fi = angleFi * Math.PI / 180.0;
    private double teta = angleTeta * Math.PI / 180.0;
    public float[][] fiMatrix = new float[][]{
            {(float) Math.cos(fi), (float) -Math.sin(fi), 0},
            {(float) Math.sin(fi), (float) Math.cos(fi), 0},
            {0, 0, 1}
    };
    public float[][] tetaMatrix = new float[][]{
            {1, 0, 0},
            {0, (float) Math.cos(teta), (float) Math.sin(teta)},
            {0, (float) -Math.sin(teta), (float) Math.cos(teta)}
    };
    public float[][] changeArray;
    private float[][] oneArray = new float[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 0}};

    public Point2D transform(Point3D points3D) {

        float[][] tempPoint = new float[3][1];

        tempPoint[0][0] = points3D.x;
        tempPoint[1][0] = points3D.y;
        tempPoint[2][0] = points3D.z;

        tempPoint = multipleMatrix(changeArray, tempPoint);
        tempPoint = multipleMatrix(oneArray, tempPoint);

        return new Point2D(center.x + tempPoint[0][0] * scale, center.y - tempPoint[1][0] * scale/*, tempPoint[2][0]*/);
    }

    public double getAngleFi() {
        return angleFi;
    }

    public double getAngleTeta() {
        return angleTeta;
    }

    public static float[][] multipleMatrix(float[][] matrixA, float[][] matrixB) {
        int aHeight = matrixA.length;
        int aWidth = matrixA[0].length;
        int bWidth = matrixB[0].length;
        float[][] result = new float[aHeight][bWidth];
        float sum;

        for (int i = 0; i < aHeight; i++) {
            for (int j = 0; j < bWidth; j++) {
                sum = 0;
                for (int k = 0; k < aWidth; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public void setFi(double angleFi) {
        this.angleFi = angleFi;
        fi = angleFi * Math.PI / 180.0;
        fiMatrix = new float[][]{
                {(float) Math.cos(fi), (float) -Math.sin(fi), 0},
                {(float) Math.sin(fi), (float) Math.cos(fi), 0},
                {0, 0, 1}
        };

        changeArray = multipleMatrix(tetaMatrix, fiMatrix);
    }

    public void setTeta(double angleTeta) {
        this.angleTeta = angleTeta;
        teta = angleTeta * Math.PI / 180.0;
        tetaMatrix = new float[][]{
                {1, 0, 0},
                {0, (float) Math.cos(teta), (float) Math.sin(teta)},
                {0, (float) -Math.sin(teta), (float) Math.cos(teta)}
        };
        changeArray = multipleMatrix(tetaMatrix, fiMatrix);
    }

}
