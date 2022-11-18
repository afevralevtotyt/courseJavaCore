import Interface.TaskType;
import Task.Task;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.SortedMap;

import static Interface.TaskType.*;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                             deleteTask(scanner);
                            break;
                        case 3:
                                showTask(scanner);
                                break;
                        case 4:
                            dailyBook.showDeletedTasks();
                            break;
                        case 5:
                            editTask(scanner);
                            break;
                        case 6:
                            System.out.println(dailyBook.groupTasks());
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }
    static TaskService dailyBook = new TaskService();

    /* Метод для ввода задачи. Проверяет ввод с клавиатуры и, в случае ошибки ввода бросает исключение*/
    private static void inputTask(Scanner scanner) {
        System.out.println("Введите название задачи и нажмите Enter");

        String title = scanner.next();

        System.out.println("Введите краткое описание задачи");

        String description = scanner.next();


        System.out.println("Выберите повторяемость задачи О-однократная, Д-ежедневная, Н-еженедельная, М-ежемесячная, Г-ежегодная");
        String repeat = scanner.next();
        try{
        if (repeat.equals("О")||repeat.equals("Д")||repeat.equals("М")||repeat.equals("Г")){
            }else{throw new IllegalArgumentException("Введен неверный символ, попробуйте снова");}}
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            repeat =scanner.next();
        }

        System.out.println("Введите тип задачи Л-личная, Р-рабочая");
        String type = scanner.next();

        try {
            if ((type.equals("Л"))||type.equals("Р")){
            }else{throw new IllegalArgumentException("Введен неверный символ, попробуйте снова");}}
        catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
        type =scanner.next();}
        System.out.println("Введите дату, на которую необходимо показать задачи в формате YYYY-MM-DDTHH:MM:SS");

        String dateTime =scanner.next();
        try{
            if (dateTime.matches("[2][0][2-9][2-9][-][01][0-9][-][0-3][0-9][T][0-2][0-9][:][0-5][0-9]:[0-5][0-9]")){
            }else{
                throw new IllegalArgumentException("Неверный формат даты");
            }}
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            dateTime=scanner.next();
        }
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTime);


        dailyBook.addTask(title, description, repeat, type, dateTime1);
    }

    /* Метод выводит задачи на определенную дату. Проверяет ввод с клавиатуры и, в случае ошибки ввода бросает исключение*/

    private static void showTask(Scanner scanner) {

        System.out.println("Введите дату, на которую необходимо показать задачи в формате YYYY-MM-DD");

        String date =scanner.next();
        try{
            if (date.matches("[2][0][2-9][2-9][-][01][0-9][-][0-3][0-9]")){
                    }else{
                throw new IllegalArgumentException("Неверный формат даты");
            }}
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            date=scanner.next();
        }
        LocalDate date1 = LocalDate.parse(date);

        dailyBook.showDayTask( date1);    }

    /* Метод удаляет задачу по id*/
    public static void deleteTask(Scanner scanner){
        System.out.println("Введите id задачи, которую вы собиратесь удалить ");
        int id = scanner.nextInt();
        dailyBook.delete(id);

    }

    /* Метод вызывает ввод с клавиатуры для редактирования названия задачи и описания по id*/
    public static void editTask(Scanner scanner){
        System.out.println("Введите id задачи, которую вы собиратесь удалить ");
        int id = scanner.nextInt();
        System.out.println("Введите название задачи и нажмите Enter");

        String title = scanner.next();

        System.out.println("Введите краткое описание задачи");

        String description = scanner.next();

        dailyBook.editTasks(id, title, description);

    }
    /* Метод выводит меню*/
    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n2. Удалить задачу \n3. Получить задачу на указанный день \n4. Показать удаленные задачи\n5. Редактировать задачу \n6. Вывести все задачи\n0. Выход"
        );
    }
    }