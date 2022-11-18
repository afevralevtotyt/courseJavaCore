import Interface.TaskType;
import Task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/* Данный класс нужен для обслуживания класса Task*/
public class TaskService {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Task> deletedTasks = new HashMap<>();

    /* Метод отображает задачи на определенную дату*/

    public void showDayTask(LocalDate localDate) {
        int count = 0;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {

            if (entry.getValue().getRepeatable() == TaskType.Repeatable.ONCE && localDate.equals(entry.getValue().getCreationDate().toLocalDate())) {
                System.out.println(entry.getValue().getTitle() + ", создана " + entry.getValue().getCreationDate().toLocalDate() + " не повторяется");
                count++;
            } else if (entry.getValue().getRepeatable() == TaskType.Repeatable.DAILY) {
                System.out.println(entry.getValue().getTitle() + ", создана " + entry.getValue().getCreationDate().toLocalDate() + " повторяется ежедневно. " + entry.getValue().taskTimeNextDate());
                count++;
            } else if (entry.getValue().getRepeatable() == TaskType.Repeatable.WEEKLY && entry.getValue().getCreationDate().getDayOfWeek().equals(localDate.getDayOfWeek())) {
                System.out.println(entry.getValue().getTitle() + ", создана " + entry.getValue().getCreationDate().toLocalDate() + " повторяется еженедельно. " + entry.getValue().taskTimeNextDate());
                count++;
            } else if (entry.getValue().getRepeatable() == TaskType.Repeatable.MONTHLY && entry.getValue().getCreationDate().getDayOfMonth() == localDate.getDayOfMonth()) {
                System.out.println(entry.getValue().getTitle() + ", создана " + entry.getValue().getCreationDate().toLocalDate() + " повторяется ежемесячно. " + entry.getValue().taskTimeNextDate());
                count++;
            } else if (entry.getValue().getRepeatable() == TaskType.Repeatable.ANNUAL && entry.getValue().getCreationDate().getDayOfYear() == localDate.getDayOfYear()) {
                System.out.println(entry.getValue().getTitle() + ", создана " + entry.getValue().getCreationDate().toLocalDate() + " повторяется ежегодно. " + entry.getValue().taskTimeNextDate());
                count++;
            }
        }
        System.out.println();
        if (count == 0) System.out.println("На данную дату задач нет");
        System.out.println();
    }
    /* Метод добавляет задачу в Мап*/

    public void addTask(String title, String decription, String repeatable, String type, LocalDateTime date) {
        Task task;
        switch (repeatable) {
            case "О":
                task = new Task(title, decription, TaskType.Repeatable.ONCE, type.equals("Л") ? TaskType.Type.PERSONAL : TaskType.Type.WORK, date);
                tasks.put(task.getId(), task);
                break;
            case "Д":
                task = new Task(title, decription, TaskType.Repeatable.DAILY, type.equals("Л") ? TaskType.Type.PERSONAL : TaskType.Type.WORK, date);
                tasks.put(task.getId(), task);
                break;
            case "Н":
                task = new Task(title, decription, TaskType.Repeatable.WEEKLY, type.equals("Л") ? TaskType.Type.PERSONAL : TaskType.Type.WORK, date);
                tasks.put(task.getId(), task);
                break;
            case "М":
                task = new Task(title, decription, TaskType.Repeatable.MONTHLY, type.equals("Л") ? TaskType.Type.PERSONAL : TaskType.Type.WORK, date);
                tasks.put(task.getId(), task);
                break;
            case "Г":
                task = new Task(title, decription, TaskType.Repeatable.ANNUAL, type.equals("Л") ? TaskType.Type.PERSONAL : TaskType.Type.WORK, date);
                tasks.put(task.getId(), task);
                break;
        }
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    /* Метод удаляет задачу из исходной Мап и помещает ее в архив удаленных*/

    public void delete(int id) {

        if (tasks.containsKey(id)) {
            deletedTasks.put(id, tasks.get(id));
            tasks.remove(id);
            System.out.println("Задача удалена");
        } else {
            System.out.println("Нет задачи с таким id");
        }

    }

    /* Отображает все удаленные задачи*/

    public void showDeletedTasks() {
        System.out.println("Удаленные задачи:");
        System.out.println(deletedTasks);
    }

    /* Редактирует задачу по id*/

    public void editTasks(int id, String title, String description) {
        if (tasks.containsKey(id)) {
            tasks.get(id).setTitle(title);
            tasks.get(id).setDescription(description);
        } else {
            System.out.println("Нет задачи с таким id");
        }
    }

    /* Группирует задачи по дате */

    public Map<LocalDate, List<Task>> groupTasks() {

        Stream<Task> stream = tasks.values().stream();

        Map<LocalDate, List<Task>> groupedTasks = stream.collect(groupingBy(Task::getLocalDate));

        return groupedTasks;
    }
}

