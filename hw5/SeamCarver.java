import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture currentPicture;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        currentPicture = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }
    public Picture picture() {
        return new Picture(currentPicture);
    }                     // current picture
    public     int width() {
        return width;
    }                     // width of current picture
    public     int height() {
        return height;
    }                       // height of current picture
    public  double energy(int x, int y) {
        if (x >= width || y >= height || x < 0 || y < 0) {
            throw new IndexOutOfBoundsException();
        }
        Color up;
        Color down;
        Color left;
        Color right;
        if (x == 0) {
            left = currentPicture.get(width - 1, y);
            right = currentPicture.get(x + 1, y);
        } else if (x == width - 1) {
            right = currentPicture.get(0, y);
            left = currentPicture.get(x - 1, y);
        } else {
            right = currentPicture.get(x + 1, y);
            left = currentPicture.get(x - 1, y);
        }

        if (y == 0) {
            up = currentPicture.get(x, height - 1);
            down = currentPicture.get(x, y + 1);
        } else if (y == height - 1) {
            up = currentPicture.get(x, y - 1);
            down = currentPicture.get(x, 0);
        } else {
            up = currentPicture.get(x, y - 1);
            down = currentPicture.get(x, y + 1);
        }

        return Math.pow(left.getBlue() - right.getBlue(), 2)
                + Math.pow(left.getGreen() - right.getGreen(), 2)
                + Math.pow(left.getRed() - right.getRed(), 2)
                + Math.pow(up.getBlue() - down.getBlue(), 2)
                + Math.pow(up.getGreen() - down.getGreen(), 2)
                + Math.pow(up.getRed() - down.getRed(), 2);
    }          // energy of pixel at column x and row y
    public   int[] findHorizontalSeam() {
        transposePicture();
        int[] item = findVerticalSeam();
        transposePicture();
        return item;
    }            // sequence of indices for horizontal seam
    public   int[] findVerticalSeam() {
        double[][] energyMatrix = new double[height][width];
        double[][] distTo = new double[height][width];
        int[][] edgeTo = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                energyMatrix[y][x] = energy(x, y);
                if (y == 0) {
                    distTo[y][x] = energy(x, y);
                } else {
                    distTo[y][x] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                for (int i = -1; i <= 1; i++) {
                    if (x + i >= 0 && x + i < width && distTo[y + 1][x + i] > distTo[y][x] + energyMatrix[y + 1][x + i]) {
                        distTo[y + 1][x + i] = distTo[y][x] + energyMatrix[y + 1][x + i];
                        edgeTo[y + 1][x + i] = x;
                    }
                }
            }
        }
        double minDist = Double.POSITIVE_INFINITY;
        int minIndex = -1;
        for (int x = 0; x < width; x++) {
            if (distTo[height - 1][x] < minDist) {
                minDist = distTo[height - 1][x];
                minIndex = x;
            }
        }
        int[] seam = new int[height];
        for (int y = height - 1; y >= 0; y--) {
            seam[y] = minIndex;
            minIndex = edgeTo[y][minIndex];
        }

        return seam;
    }              // sequence of indices for vertical seam
    public    void removeHorizontalSeam(int[] seam) {
        if (seam.length != width) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i += 1) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
        currentPicture = SeamRemover.removeHorizontalSeam(currentPicture, seam);
    }  // remove horizontal seam from picture
    public    void removeVerticalSeam(int[] seam) {
        if (seam.length != height) {
            throw new IllegalArgumentException();
        }
        for (int i = 1; i < seam.length; i += 1) {
            if (Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
        currentPicture = SeamRemover.removeVerticalSeam(currentPicture, seam);
    }    // remove vertical seam from picture

    /*
    * 返回最小列的列标
    * */
    private int smallestCost(double[] cost) {
        double min = cost[0];
        int ind = 0;
        for (int i = 0; i < cost.length; i += 1) {
            if (cost[i] < min) {
                min = cost[i];
                ind = i;
            }
        }
        return ind;
    }
    private void transposePicture() {
        Picture transposed = new Picture(height, width);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                transposed.set(y, x, currentPicture.get(x, y));
            }
        }
        currentPicture = transposed;
        int temp = width;
        width = height;
        height = temp;
    }

}
