package com.example.matrix_calculator;

import javax.swing.*;
import java.awt.*;

public class MatrixCalculator {

    public JFrame frame;
    public JPanel mainPanel;
    public JPanel matrix1Panel;
    public JPanel matrix2Panel;
    public JPanel resultMatrixPanel;
    public JTextField[][] matrix1Fields;
    public JTextField[][] matrix2Fields;
    public JTextField[][] resultMatrixFields;
    public JButton addButton;
    public JButton subtractButton;
    public JButton multiplyButton;
    public JLabel errorLabel;
    public int rows;
    public int cols;

    public int rows2;
    public int cols2;
    public MatrixCalculator() {
        Menu();







    }
    private void clearMatrixFields(JTextField[][] matrixFields) {
        for (JTextField[] matrixField : matrixFields) {
            for (JTextField jTextField : matrixField) {
                jTextField.setText("");
            }
        }
    }

    private void setMatrixValues(JTextField[][] resultMatrixFields, double[][] result) {
        int rows = result.length;
        int cols = result[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrixFields[i][j].setText(Double.toString(result[i][j]));
            }
        }
    }

    private double[][] getMatrixValues(JTextField[][] matrix2Fields) {
        int rows = matrix2Fields.length;
        int cols = matrix2Fields[0].length;
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Double.parseDouble(matrix2Fields[i][j].getText());
            }
        }

        return matrix;
    }

    public void  variant1(){
        int rows = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows:"));
        int cols = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns:"));

        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive integers.");
        }

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the matrix 1 panel
        JPanel matrix1Panel = new JPanel(new GridLayout(rows, cols));
        matrix1Panel.setBorder(BorderFactory.createTitledBorder("Matrix 1"));

        JTextField[][] matrix1Fields = new JTextField[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix1Fields[i][j] = new JTextField(5);
                matrix1Panel.add(matrix1Fields[i][j]);
            }
        }

        // Create the scalar panel
        JPanel scalarPanel = new JPanel(new FlowLayout());
        scalarPanel.setBorder(BorderFactory.createTitledBorder("Scalar"));

        JTextField scalarField = new JTextField(5);
        scalarPanel.add(scalarField);

        // Create the operation panel
        JPanel jFrame = new JPanel(new GridLayout(3, 1));

        // Create the scalar multiplication button
        JButton multiButton = new JButton("Multiply by Scalar");
        multiButton.addActionListener(e -> {
            // Get the scalar value
            double scalar = Double.parseDouble(scalarField.getText());

            // Multiply the matrix by the scalar
            double[][] resultMatrix = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    double value = Double.parseDouble(matrix1Fields[i][j].getText());
                    value *= scalar;
                    resultMatrix[i][j] = value;
                }
            }

            // Create the result panel
            JPanel resultPanel = new JPanel(new GridLayout(rows, cols));
            resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));

            // Add the result matrix to the result panel
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultPanel.add(new JLabel(Double.toString(resultMatrix[i][j])));
                }
            }

            // Show the result panel in a dialog
            JOptionPane.showMessageDialog(null, resultPanel, "Result", JOptionPane.PLAIN_MESSAGE);
        });
        jFrame.add(multiButton);

        // Create the matrix addition button
        JButton addButton = new JButton("Add Matrices");
        addButton.addActionListener(e -> {
            int rows2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows for matrix 2:"));
            int cols2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns for matrix 2:"));

            if (rows != rows2 || cols != cols2) {
                throw new IllegalArgumentException("Matrix dimensions do not match.");
            }

            // Create the matrix 2 panel
            JPanel matrix2Panel = new JPanel(new GridLayout(rows, cols));
            matrix2Panel.setBorder(BorderFactory.createTitledBorder("Matrix 2"));

            JTextField[][] matrix2Fields = new JTextField[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix2Fields[i][j] = new JTextField(5);
                    matrix2Panel.add(matrix2Fields[i][j]);
                }
            }
            // Create the matrix addition button
            JButton addButton1 = new JButton("Add Matrices");
            addButton1.addActionListener(e1 -> {
                int rows21 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows for matrix 2:"));
                int cols21 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns for matrix 2:"));
                if (rows != rows21 || cols != cols21) {
                    throw new IllegalArgumentException("Matrix dimensions do not match.");
                }

                // Create the matrix 2 panel
                JPanel matrix2Panel1 = new JPanel(new GridLayout(rows21, cols21)); // corrected the GridLayout to use rows2 and cols2
                matrix2Panel1.setBorder(BorderFactory.createTitledBorder("Matrix 2"));

                JTextField[][] matrix2Fields1 = new JTextField[rows21][cols21]; // corrected to use rows2 and cols2 instead of rows and cols

                for (int i = 0; i < rows21; i++) { // corrected to use rows2 instead of rows
                    for (int j = 0; j < cols21; j++) { // corrected to use cols2 instead of cols
                        matrix2Fields1[i][j] = new JTextField(5);
                        matrix2Panel1.add(matrix2Fields1[i][j]);
                    }
                }
            });

            // Create the result panel
            JPanel resultPanel = new JPanel(new GridLayout(rows, cols));
            resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));

            // Add the matrices and show the result
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    double value1 = Double.parseDouble(matrix1Fields[i][j].getText());
                    double value2 = Double.parseDouble(matrix2Fields[i][j].getText());
                    double result = value1 + value2;
                    resultPanel.add(new JLabel(Double.toString(result)));
                }
            }

            // Show the result panel in a dialog
            JOptionPane.showMessageDialog(null, resultPanel, "Result", JOptionPane.PLAIN_MESSAGE);
        });

        // Add the panels to the main panel
        mainPanel.add(matrix1Panel, BorderLayout.CENTER);
        mainPanel.add(scalarPanel, BorderLayout.NORTH);
        mainPanel.add(jFrame, BorderLayout.SOUTH);

        // Show the main panel in a dialog
        JOptionPane.showMessageDialog(null, mainPanel, "Matrix Operations", JOptionPane.PLAIN_MESSAGE);

    }

    public void variant2(){
        int rows = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows:"));
        int cols = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns:"));

        int rows2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of rows:"));
        int cols2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of columns:"));

        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive integers.");
        }

        if (rows2 <= 0 || cols2 <= 0) {
            throw new IllegalArgumentException("Matrix dimensions must be positive integers.");
        }


        this.rows = rows;
        this.cols = cols;

        this.rows2 = rows2;
        this.cols2 = cols2;

        matrix1Fields = new JTextField[rows][cols];
        matrix2Fields = new JTextField[rows2][cols2];
        resultMatrixFields = new JTextField[rows][cols];

        // Create the main panel
        mainPanel = new JPanel(new BorderLayout());

        // Create the matrix 1 panel
        matrix1Panel = new JPanel(new GridLayout(rows, cols));
        matrix1Panel.setBorder(BorderFactory.createTitledBorder("Matrix 1"));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix1Fields[i][j] = new JTextField(5);
                matrix1Panel.add(matrix1Fields[i][j]);
            }
        }

        // Create the matrix 2 panel
        matrix2Panel = new JPanel(new GridLayout(rows2, cols2));
        matrix2Panel.setBorder(BorderFactory.createTitledBorder("Matrix 2"));

        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                matrix2Fields[i][j] = new JTextField(5);
                matrix2Panel.add(matrix2Fields[i][j]);
            }
        }

        // Create the result matrix panel
        resultMatrixPanel = new JPanel(new GridLayout(rows, cols));
        resultMatrixPanel.setBorder(BorderFactory.createTitledBorder("Result Matrix"));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrixFields[i][j] = new JTextField(5);
                resultMatrixFields[i][j].setEditable(false);
                resultMatrixPanel.add(resultMatrixFields[i][j]);
            }
        }

        // Create the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                double[][] matrix1 = getMatrixValues(matrix1Fields);
                double[][] matrix2 = getMatrixValues(matrix2Fields);
                double[][] result = com.example.matrix_calculator.MatrixOperations.add(matrix1, matrix2);
                setMatrixValues(resultMatrixFields, result);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        subtractButton = new JButton("Subtract");
        subtractButton.addActionListener(e -> {
            try {
                double[][] matrix1 = getMatrixValues(matrix1Fields);
                double[][] matrix2 = getMatrixValues(matrix2Fields);
                double[][] result = com.example.matrix_calculator.MatrixOperations.subtract(matrix1, matrix2);
                setMatrixValues(resultMatrixFields, result);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });
        multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(e -> {
            try {
                double[][] matrix1 = getMatrixValues(matrix1Fields);
                double[][] matrix2 = getMatrixValues(matrix2Fields);
                double[][] result = com.example.matrix_calculator.MatrixOperations.multiply(matrix1, matrix2);
                setMatrixValues(resultMatrixFields, result);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        JButton divideButton = new JButton("Divide");
        divideButton.addActionListener(e -> {
            try {
                double[][] matrix1 = getMatrixValues(matrix1Fields);
                double[][] matrix2 = getMatrixValues(matrix2Fields);
                double[][] result = com.example.matrix_calculator.MatrixOperations.divide(matrix1, matrix2);
                setMatrixValues(resultMatrixFields, result);
                errorLabel.setText("");
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clearMatrixFields(matrix1Fields);
            clearMatrixFields(matrix2Fields);
            clearMatrixFields(resultMatrixFields);
            errorLabel.setText("");
        });

// Add the buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(clearButton);

// Create the error label
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

// Add the components to the main panel
        mainPanel.add(matrix1Panel, BorderLayout.WEST);
        mainPanel.add(matrix2Panel, BorderLayout.EAST);
        mainPanel.add(resultMatrixPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(errorLabel, BorderLayout.NORTH);

// Set up the frame
        frame = new JFrame("Matrix Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void Menu() {
        // Создаем главное меню
        JFrame mainMenu = new JFrame("Main Menu");
        mainMenu.setContentPane(new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = Color.WHITE;
                Color color2 = Color.BLUE;
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        });
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(600, 400);
        mainMenu.setLayout(new BorderLayout());

        // Создаем метку с большим шрифтом
        JLabel titleLabel = new JLabel("Matrix calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        // Создаем еще одну метку со средним шрифтом
        JLabel subtitleLabel = new JLabel("Виберіть, скільки матриць будуть застосовано у матиматичних операціях");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));


        // Создаем панель для меток и добавляем метки на панель
        JPanel labelPanel = new JPanel(new GridBagLayout());
        labelPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        labelPanel.add(titleLabel, gbc);
        labelPanel.add(subtitleLabel, gbc);

        // Добавляем панель с метками в верхнюю часть окна
        mainMenu.add(labelPanel, BorderLayout.NORTH);

        // Добавляем две дополнительные кнопки в главное меню
        JButton button1 = new JButton("Мені потрібна операція лише з однією матрицею");
        button1.addActionListener(e -> {
            variant1();
        });

        JButton button2 = new JButton("Мені потрібні операції з двома матрицями");
        button2.addActionListener(e -> {
            variant2();
        });

        // Создаем панель для кнопок и добавляем кнопки на панель
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Добавляем пустую границу сверху, чтобы опустить кнопки ниже
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        // Добавляем панель с кнопками в центр окна
        mainMenu.add(buttonPanel, BorderLayout.CENTER);

        // Отображаем главное меню
        mainMenu.setVisible(true);
        float[] hues = {0f, 0.5f};
        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = Color.getHSBColor(hues[0], 1, 1);
                Color color2 = Color.getHSBColor(hues[1], 1, 1);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainMenu.setContentPane(contentPane);

        Timer timer = new Timer(0, e -> {
            hues[0] += 0.001f;
            hues[1] += 0.001f;
            if (hues[0] > 1) hues[0] -= 1;
            if (hues[1] > 1) hues[1] -= 1;
            contentPane.repaint();
        });
        timer.start();

        contentPane.add(labelPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

    }

    public static void main (String[]args){
        // Create the matrix calculator object
        MatrixCalculator calculator = new MatrixCalculator();

    }
}