package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ResponseFormatter {

    private final Gson gson;

    public ResponseFormatter() {
        this.gson = new Gson();
    }

    public String formatUsersForDisplay(String jsonResponse) {
        try {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(jsonResponse, userListType);

            StringBuilder builder = new StringBuilder();
            int userNumber = 1;
            for (User user : users) {
                builder.append("__User №").append(userNumber++).append("__\n")
                        .append("id: ").append(user.id).append("\n")
                        .append("name: ").append(user.name).append("\n")
                        .append("company: ").append(user.company).append("\n")
                        .append("username: ").append(user.username).append("\n")
                        .append("email: ").append(user.email).append("\n")
                        .append("address: ").append(user.address).append("\n")
                        .append("zip: ").append(user.zip).append("\n")
                        .append("state: ").append(user.state).append("\n")
                        .append("country: ").append(user.country).append("\n")
                        .append("phoneNumber: ").append(user.phoneNumber).append("\n")
                        .append("photo: ").append(user.photo).append("\n\n");
            }
            return builder.toString();
        } catch (Exception e) {
            return "Ошибка при парсинге пользователей: " + e.getMessage();
        }
    }

    public String formatTodosForDisplay(String jsonResponse) {
        try {
            Type todoListType = new TypeToken<List<Todo>>() {}.getType();
            List<Todo> todos = gson.fromJson(jsonResponse, todoListType);

            StringBuilder builder = new StringBuilder();
            int todoNumber = 1;
            for (Todo todo : todos) {
                builder.append("__Todo №").append(todoNumber++).append("__\n")
                        .append("id: ").append(todo.id).append("\n")
                        .append("title: ").append(todo.title).append("\n")
                        .append("completed: ").append(todo.completed).append("\n\n");
            }
            return builder.toString();
        } catch (Exception e) {
            return "Ошибка при парсинге задач: " + e.getMessage();
        }
    }

    public void printUsers(String jsonResponse) {
        System.out.println(formatUsersForDisplay(jsonResponse));
    }

    public void printTodos(String jsonResponse) {
        System.out.println(formatTodosForDisplay(jsonResponse));
    }
}