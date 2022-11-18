package Task;

import Interface.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements TaskType {
    private String title;
    private String description;
    private static int count = 1;
    private int id;
    LocalDateTime creationDate = null;
    private TaskType.Repeatable repeatable;
    private TaskType.Type type;


    public Task(String title, String description, TaskType.Repeatable repeatable, TaskType.Type type, LocalDateTime date) {
        if (title.isBlank() || title.isEmpty()) {
            throw new IllegalArgumentException("Не заполенно обзятельное поле");
        } else {
            this.title = title;
        }
        if (description.isBlank() || description.isEmpty()) {
            throw new IllegalArgumentException("Не заполенно обзятельное поле");
        } else {
            this.description = description;
        }
        this.id = count++;
        this.creationDate = date;
        this.repeatable = repeatable;
        this.type = type;
    }


    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Repeatable getRepeatable() {
        return repeatable;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Задача: " + id +
                ", название: " + title +
                ", описание: " + description +
                ", дата создания: " + creationDate +
                ", повторяемость: " + repeatable +
                ", тип: " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!title.equals(task.title)) return false;
        if (!description.equals(task.description)) return false;
        if (!Objects.equals(creationDate, task.creationDate)) return false;
        if (repeatable != task.repeatable) return false;
        return type == task.type;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (repeatable != null ? repeatable.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
    /*Выводит время выполнения задачи и дату повторного назначения задачи*/

    @Override
    public String taskTimeNextDate() {
        if (this.repeatable == Repeatable.DAILY) {
            return "Время выполнения задачи день. Задача будет назначена снова " + (creationDate.plusDays(1));
        }
        if (this.repeatable == Repeatable.WEEKLY) {
            return "Время выполнения задачи одна неделя. Задача будет назначена снова " + (creationDate.plusWeeks(1));
        }
        if (this.repeatable == Repeatable.MONTHLY) {
            return "Время выполнения задачи месяц. Задача будет назначена снова " + (creationDate.plusMonths(1));
        }
        if (this.repeatable == Repeatable.ANNUAL) {
            return "Время выполнения задачи год. Задача будет назначена снова " + (creationDate.plusYears(1));
        } else {
            return "";
        }

    }
    /* Получает LocalDate из LocalDateTime */

    public LocalDate getLocalDate() {
        return getCreationDate().toLocalDate();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
