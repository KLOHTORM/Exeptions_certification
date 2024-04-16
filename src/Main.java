import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите через пробел Фамилию, Имя, Отчество, Дату рождения(в формате dd.mm.yyyy), Номер телефона(одним числом), Пол(f или m):");
        String info = scanner.nextLine();
        String[] data = info.split(" ");

        if (data.length != 6) {
            System.err.println("Вы ввели неверное количество данных!");
        } else {
            try {
                String surname = data[0];
                String name = data[1];
                String patronymic = data[2];
                String dateOfBirth = data[3];
                String phoneNumber = data[4];
                char sex = data[5].charAt(0);
                DateTimeFormatter dateCheck = DateTimeFormatter.ofPattern("dd.mm.yyyy");
                String dateNow = dateCheck.format(LocalDateTime.now());
                if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                    throw new IllegalArgumentException("Неверно введена дата рождения!");
                }

                String[] dateSplit = dateOfBirth.split("\\.");
                int day = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                int year = Integer.parseInt(dateSplit[2]);

                String[] dateSplitNow = dateNow.split("\\.");
                int yearNow = Integer.parseInt(dateSplitNow[2]);
                if (year == yearNow) {
                    throw new IllegalArgumentException("Этот год не может быть использован!");
                } else if (day > 31) {
                    throw new IllegalArgumentException("В месяце не больше 31 дня!");
                } else if (month > 12) {
                    throw new IllegalArgumentException("В году не больше 12 месяцев!");
                }

                if (phoneNumber.length() != 11) {
                    throw new IllegalArgumentException("Номер телефона должен состоять из 11 чисел!");
                }

                if (sex != 'f' && sex != 'm') {
                    throw new IllegalArgumentException("Неверно введен пол!");
                }

                String fileName = surname + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                writer.write("ФИО: " + surname + " " + name + " " + patronymic + "; Дата рождения: " + dateOfBirth + "; Номер телефона: " + phoneNumber + "; пол: " + sex + "\n");
                writer.close();
                System.out.println("Файл " + fileName + " создан и записан");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}