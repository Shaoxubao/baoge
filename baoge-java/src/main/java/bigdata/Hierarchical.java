package bigdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Hierarchical {
    private double[][] matrix;
    private int dimension;// 数据维度

    private class Node {
        double[] attributes;

        public Node() {
            attributes = new double[100];
        }
    }

    private ArrayList<Node> arraylist;

    private class Model {
        int x = 0;
        int y = 0;
        double value = 0;
    }

    private Model minModel = new Model();

    private double getDistance(Node one, Node two) {// 计算两点间的欧氏距离
        double val = 0;
        for (int i = 0; i < dimension; ++i) {
            val += (one.attributes[i] - two.attributes[i]) * (one.attributes[i] - two.attributes[i]);
        }
        return Math.sqrt(val);
    }

    private void loadMatrix() {// 将输入数据转化为矩阵
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = i + 1; j < matrix.length; ++j) {
                double distance = getDistance(arraylist.get(i), arraylist.get(j));
                matrix[i][j] = distance;
            }
        }
    }

    private Model findMinValueOfMatrix(double[][] matrix) {// 找出矩阵中距离最近的两个簇
        Model model = new Model();
        double min = 0x7fffffff;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = i + 1; j < matrix.length; ++j) {
                if (min > matrix[i][j] && matrix[i][j] != 0) {
                    min = matrix[i][j];
                    model.x = i;
                    model.y = j;
                    model.value = matrix[i][j];
                }
            }
        }
        return model;
    }

    private void processHierarchical(String path) {
        try {
            PrintStream out = new PrintStream(path);
            while (true) {// 凝聚层次聚类迭代
                out.println("Matrix update as below: ");
                for (int i = 0; i < matrix.length; ++i) {// 输出每次迭代更新的矩阵
                    for (int j = 0; j < matrix.length - 1; ++j) {
                        out.print(new DecimalFormat("#.00").format(matrix[i][j]) + " ");
                    }
                    out.println(new DecimalFormat("#.00").format(matrix[i][matrix.length - 1]));
                }
                out.println();
                minModel = findMinValueOfMatrix(matrix);
                if (minModel.value == 0) {// 当找不出距离最近的两个簇时，迭代结束
                    break;
                }
                out.println("Combine " + (minModel.x + 1) + " " + (minModel.y + 1));
                out.println("The distance is: " + minModel.value);

                matrix[minModel.x][minModel.y] = 0;// 更新矩阵
                for (int i = 0; i < matrix.length; ++i) {// 如果合并了点 p1 与 p2，则只保留 p1,p2 其中之一与其他点的距离，取较小值
                    if (matrix[i][minModel.x] <= matrix[i][minModel.y]) {
                        matrix[i][minModel.y] = 0;
                    } else {
                        matrix[i][minModel.x] = 0;
                    }
                    if (matrix[minModel.x][i] <= matrix[minModel.y][i]) {
                        matrix[minModel.y][i] = 0;
                    } else {
                        matrix[minModel.x][i] = 0;
                    }
                }
            }

            out.close();
            System.out.println("Please check results in: " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInput(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String str;
            String[] strArray;
            arraylist = new ArrayList<Node>();
            while ((str = br.readLine()) != null) {
                strArray = str.split(",");
                dimension = strArray.length;
                Node node = new Node();
                for (int i = 0; i < dimension; ++i) {
                    node.attributes[i] = Double.parseDouble(strArray[i]);
                }
                arraylist.add(node);
            }
            matrix = new double[arraylist.size()][arraylist.size()];
            loadMatrix();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printOutput(String path) {
        processHierarchical(path);
    }

    public static void main(String[] args) {
        Hierarchical hi = new Hierarchical();
        hi.setInput("e:/hierarchical.txt");
        hi.printOutput("e:/hierarchical_results.txt");
    }
}
