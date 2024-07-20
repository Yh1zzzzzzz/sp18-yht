import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture currentPicture;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        currentPicture = picture;
        width = picture.width();
        height = picture.height();
    }
    public Picture picture() {
        return currentPicture;
    }                     // current picture
    public     int width() {
        return width;
    }                     // width of current picture
    public     int height() {
        return height;
    }                       // height of current picture
    public  double energy(int x, int y) {
        if (x >= width || y >= height) {
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
        int[][] returnArray = new int[width][height];
        for (int i = 0; i < width; i += 1) {
            returnArray[i][0] = i;
        }
        int index = 1;
        while (index < height) {
            for (int i = 0; i < width; i += 1) {
                //加入子序列的最小一个
                if (i == 0) {
                    if (Math.min(energy(i, index), energy(i + 1, index)) == energy(i, index)) {
                        returnArray[i][index] = i;
                    } else {
                        returnArray[i][index] = i + 1;
                    }
                } else if (i == width - 1) {
                    if (Math.min(energy(i, index), energy(i - 1, index)) == energy(i, index)) {
                        returnArray[i][index] = i;
                    } else {
                        returnArray[i][index] = i - 1;
                    }
                } else {
                    if (Math.min(Math.min(energy(i, index),
                            energy(i - 1, index)), energy(i + 1, index)) == energy(i, index)) {
                        returnArray[i][index] = i;
                    } else if (Math.min(Math.min(energy(i, index), energy(i - 1, index)),
                            energy(i + 1, index)) == energy(i + 1, index)) {
                        returnArray[i][index] = i + 1;
                    } else {
                        returnArray[i][index] = i - 1;
                    }

                }
            }
            index += 1;
        }
        return returnArray[smallestCost(width, height)];

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
    private int smallestCost(int w, int h) {
        double[] cost = new double[w];
        for (int i = 0; i < w; i += 1) {
            cost[i] += energy(i, 0);
        }
        int index = 1;
        while (index < h) {
            for (int i = 0; i < w; i += 1) {
                //加入子序列的最小一个
                if (i == 0) {
                    cost[i] += Math.min(energy(i, index), energy(i + 1, index));
                } else if (i == width - 1) {
                    cost[i] += Math.min(energy(i, index), energy(i - 1, index));
                } else {
                    cost[i] += Math.min(Math.min(energy(i, index), energy(i - 1, index)), energy(i + 1, index));
                }
            }
            index += 1;
        }
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
