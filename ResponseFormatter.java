import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ResponseFormatter {

    private final Gson gson; // Для парсинга JSON

    public ResponseFormatter() {
        this.gson = new Gson();
    }

    /**
     * Форматирует пользователей для отображения в графическом интерфейсе.
     *
     * @param jsonResponse JSON-строка, содержащая данные пользователей.
     * @return Отформатированная строка для вывода.
     */
    public String formatUsersForDisplay(String jsonResponse) {
        try {
            // Определяем тип данных
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(jsonResponse, userListType);

            // Форматируем пользователей
            StringBuilder builder = new StringBuilder();
            int userNumber = 1;
            for (User user : users) {
                builder.append("__User №").append(userNumber++).append("__\n")
                        .append("id: ").append(user.id).append("\n")
                        .append("name: ").append(user.name).append("\n")
                        .append("email: ").append(user.email).append("\n")
                        .append("company: ").append(user.company).append("\n\n");
            }
            return builder.toString();
        } catch (Exception e) {
            return "Ошибка при парсинге пользователей: " + e.getMessage();
        }
    }

    /**
     * Форматирует задачи для отображения в графическом интерфейсе.
     *
     * @param jsonResponse JSON-строка, содержащая данные задач.
     * @return Отформатированная строка для вывода.
     */
    public String formatTodosForDisplay(String jsonResponse) {
        try {
            // Определяем тип данных
            Type todoListType = new TypeToken<List<Todo>>() {}.getType();
            List<Todo> todos = gson.fromJson(jsonResponse, todoListType);

            // Форматируем задачи
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

    /**
     * Устаревший метод для консольного вывода пользователей.
     *
     * @param jsonResponse JSON-строка, содержащая данные пользователей.
     */
    public void printUsers(String jsonResponse) {
        System.out.println(formatUsersForDisplay(jsonResponse));
    }

    /**
     * Устаревший метод для консольного вывода задач.
     *
     * @param jsonResponse JSON-строка, содержащая данные задач.
     */
    public void printTodos(String jsonResponse) {
        System.out.println(formatTodosForDisplay(jsonResponse));
    }
}
