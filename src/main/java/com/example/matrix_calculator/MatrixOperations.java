package com.example.matrix_calculator;

public class MatrixOperations {

    public static double[][] add(double[][] matrix1, double[][] matrix2) throws IllegalArgumentException {
        int rows = matrix1.length;
        int cols = matrix1[0].length;

        if (rows != matrix2.length || cols != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    public static double[][] subtract(double[][] matrix1, double[][] matrix2) throws IllegalArgumentException {
        int rows = matrix1.length;
        int cols = matrix1[0].length;

        if (rows != matrix2.length || cols != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must be the same size.");
        }

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }

    public static double[][] multiply(double[][] matrix1, double[][] matrix2) throws IllegalArgumentException {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException("Cannot multiply matrices: number of columns and rows must equal");
        }

        double[][] result = new double[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                double sum = 0;
                for (int k = 0; k < cols1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    public static double[][] inverse(double[][] matrix) throws IllegalArgumentException {
        int n = matrix.length;

        // Перевірка, що матриця квадратна
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        // Створення розширеної матриці, де права частина - одинична матриця
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1;
        }

        // Перетворення матриці у верхньотрикутну форму
        for (int i = 0; i < n; i++) {
            if (augmentedMatrix[i][i] == 0) {
                // Якщо елемент на діагоналі дорівнює нулю, потрібно знайти інший рядок для заміни
                boolean found = false;
                for (int j = i + 1; j < n; j++) {
                    if (augmentedMatrix[j][i] != 0) {
                        double[] temp = augmentedMatrix[i];
                        augmentedMatrix[i] = augmentedMatrix[j];
                        augmentedMatrix[j] = temp;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    throw new IllegalArgumentException("Matrix is not invertible.");
                }
            }
            double pivot = augmentedMatrix[i][i];
            for (int j = i; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                double factor = augmentedMatrix[j][i];
                for (int k = i; k < 2 * n; k++) {
                    augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                }
            }
        }

        // Отримання оберненої матриці з розширеної матриці
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, inverse[i], 0, n);
        }
        return inverse;
    }

    public static double[][] multiplyByInverse(double[][] matrix1, double[][] matrix2) throws IllegalArgumentException {
        // find the inverse of matrix2
        double[][] inverseMatrix2 = inverse(matrix2);

        // multiply matrix1 by the inverse of matrix2

        return multiply(matrix1, inverseMatrix2);
    }

    public static double determinant(double[][] matrix) {
        int n = matrix.length;
        double det;

        if (n == 1) {
            det = matrix[0][0];
        } else if (n == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            det = 0;
            for (int j1 = 0; j1 < n; j1++) {
                double[][] m = new double[n - 1][];
                for (int k = 0; k < n - 1; k++) {
                    m[k] = new double[n - 1];
                }
                for (int i = 1; i < n; i++) {
                    int j2 = 0;
                    for (int j = 0; j < n; j++) {
                        if (j == j1) {
                            continue;
                        }
                        m[i - 1][j2] = matrix[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0 + j1 + 1.0) * matrix[0][j1] * determinant(m);
            }
        }

        return det;
    }

    public static double[][] divide(double[][] matrix1, double[][] matrix2) throws IllegalArgumentException {
        // Знайдемо обернену матрицю matrix2
        double[][] inverseMatrix2 = inverse(matrix2);

        // Перевіримо чи існує обернена матриця matrix2
        if (inverseMatrix2 == null) {
            throw new IllegalArgumentException("Division is not possible. The second matrix is not invertible.");
        }

        // Перевіримо розміри матриць
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2) {
            throw new IllegalArgumentException("Cannot divide matrices: number of columns of the first matrix must equal number of rows of the second matrix.");
        }

        // Перемножимо matrix1 на обернену matrix2
        return multiply(matrix1, inverseMatrix2);
    }
}