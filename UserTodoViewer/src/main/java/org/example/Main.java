package org.example;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final String usersUrl = "https://fake-json-api.mock.beeceptor.com/users";
    private final String todosUrl = "https://dummy-json.mock.beeceptor.com/todos";

    private final HttpClient client = new HttpClient();
    private final ResponseFormatter parser = new ResponseFormatter();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User and Todo Viewer");

        Text usersLabel = createLabel("Users");
        usersLabel.setFill(Color.BROWN);
        TextArea userArea = createTextArea();
        VBox usersBox = new VBox(10, usersLabel, userArea);
        usersBox.setAlignment(Pos.CENTER);

        Text todosLabel = createLabel("ToDo");
        todosLabel.setFill(Color.BROWN);
        TextArea todoArea = createTextArea();
        VBox todosBox = new VBox(10, todosLabel, todoArea);
        todosBox.setAlignment(Pos.CENTER);

        Button userButton = createStyledButton("Показать Users");
        Button todoButton = createStyledButton("Показать Todos");

        userButton.setOnAction(e -> {
            try {
                String usersResponse = client.sendGetRequest(usersUrl);
                userArea.setText(formatUsers(usersResponse));
            } catch (IOException ex) {
                userArea.setText("Ошибка загрузки Users: " + ex.getMessage());
            }
        });

        todoButton.setOnAction(e -> {
            try {
                String todosResponse = client.sendGetRequest(todosUrl);
                todoArea.setText(formatTodos(todosResponse));
            } catch (IOException ex) {
                todoArea.setText("Ошибка загрузки Todos: " + ex.getMessage());
            }
        });

        HBox buttonBox = new HBox(10, userButton, todoButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(20, usersBox, todosBox, buttonBox);
        contentBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F5DC;");
        root.setCenter(contentBox);

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea createTextArea() {
        TextArea area = new TextArea();
        area.setFont(Font.font("Courier New", 14));
        area.setEditable(false);
        area.setWrapText(true);
        area.setStyle(
                "-fx-control-inner-background: #FFF8DC;" +
                        "-fx-font-weight: bold;"
        );
        return area;
    }

    private Text createLabel(String text) {
        Text label = new Text(text);
        label.setFont(Font.font("Arial", 20));
        label.setFill(Color.DARKBLUE);
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 16));
        button.setTextFill(Color.BROWN);
        button.setStyle("-fx-background-color: #DEB887; -fx-border-color: #8B4513; -fx-border-width: 2;");
        button.setPrefWidth(200);
        return button;
    }

    private String formatUsers(String jsonResponse) {
        StringBuilder builder = new StringBuilder();
        builder.append(centerText(parser.formatUsersForDisplay(jsonResponse)));
        return builder.toString();
    }

    private String formatTodos(String jsonResponse) {
        StringBuilder builder = new StringBuilder();
        builder.append(centerText(parser.formatTodosForDisplay(jsonResponse)));
        return builder.toString();
    }

    private String centerText(String text) {
        String[] lines = text.split("\n");
        StringBuilder centeredText = new StringBuilder();

        for (String line : lines) {
            int padding = Math.max((60 - line.length()) / 2, 0);
            centeredText.append(" ".repeat(padding)).append(line).append("\n");
        }

        return centeredText.toString();
    }
}