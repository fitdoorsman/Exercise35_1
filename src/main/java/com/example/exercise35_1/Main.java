package com.example.exercise35_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Main extends Application {
    private Connection connection;

    private Label statusLabel;
    private TextArea resultArea;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Exercise35_1";
    private static final String DB_USER = "root"; // Change if needed
    private static final String DB_PASSWORD = "Ibelieve1437!"; // Change to your password

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exercise 35.1");

        Button connectButton = new Button("Connect to Database");
        Button batchUpdateButton = new Button("Batch Update");
        Button nonBatchUpdateButton = new Button("Non-Batch Update");

        statusLabel = new Label("Not connected");
        resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPrefHeight(200);

        connectButton.setOnAction(e -> connectToDatabase());
        batchUpdateButton.setOnAction(e -> performBatchUpdate());
        nonBatchUpdateButton.setOnAction(e -> performNonBatchUpdate());

        VBox vbox = new VBox(10, connectButton, batchUpdateButton, nonBatchUpdateButton, statusLabel, resultArea);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statusLabel.setText("Connected to DB successfully!");
        } catch (SQLException e) {
            statusLabel.setText("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void performBatchUpdate() {
        if (connection == null) {
            resultArea.appendText("Not connected to database!\n");
            return;
        }

        String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        Random rand = new Random();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                statement.setDouble(1, rand.nextDouble());
                statement.setDouble(2, rand.nextDouble());
                statement.setDouble(3, rand.nextDouble());
                statement.addBatch();
            }

            statement.executeBatch();
            long endTime = System.currentTimeMillis();

            resultArea.appendText("Batch update completed!\nElapsed time: " + (endTime - startTime) + " ms\n\n");
        } catch (SQLException e) {
            resultArea.appendText("Batch update failed: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void performNonBatchUpdate() {
        if (connection == null) {
            resultArea.appendText("Not connected to database!\n");
            return;
        }

        String sql = "INSERT INTO Temp (num1, num2, num3) VALUES (?, ?, ?)";
        Random rand = new Random();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                statement.setDouble(1, rand.nextDouble());
                statement.setDouble(2, rand.nextDouble());
                statement.setDouble(3, rand.nextDouble());
                statement.executeUpdate();
            }

            long endTime = System.currentTimeMillis();

            resultArea.appendText("Non-batch update completed!\nElapsed time: " + (endTime - startTime) + " ms\n\n");
        } catch (SQLException e) {
            resultArea.appendText("Non-batch update failed: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}