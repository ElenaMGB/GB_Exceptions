package Seminars;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Seminar_DZ {
    public static void main(String[] args) {
        // Создание объекта Scanner для ввода данных от пользователя
        Scanner scanner = new Scanner(System.in);
        // Запрос данных у пользователя
        System.out.println("Введите сведения о себе (Фамилия Имя Отчество ДатаРождения Телефон пол):");
        String input = scanner.nextLine(); // Чтение строки ввода

        try {
            // Разделение строки ввода по пробелам
            String[] parts = input.split(" ");
            // Проверка на количество введенных данных
            if (parts.length != 6) {
                throw new IllegalArgumentException("Неверное количество аргументов. Ожидалось 6.");
            }

            // Извлечение и валидация каждой части
            String surname = parts[0];
            String name = parts[1];
            String middleName = parts[2];
            // Валидация даты рождения
            LocalDate dateOfBirth = parseDate(parts[3]);
            // Валидация номера телефона
            long phoneNumber = parsePhoneNumber(parts[4]);
            // Валидация пола
            char gender = parseGender(parts[5]);

            // Запись данных в файл
            writeToFile(surname, name, middleName, dateOfBirth, phoneNumber, gender);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка: Неверный формат даты. Пожалуйста, используйте dd.MM.yyyy.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Закрытие сканера в блоке finally
        }
    }

    private static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

    private static long parsePhoneNumber(String phoneNumberStr) {
        try {
            return Long.parseLong(phoneNumberStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона.");
        }
    }

    private static char parseGender(String genderStr) {
        if (genderStr.length() != 1 || !(genderStr.equalsIgnoreCase("f") || genderStr.equalsIgnoreCase("m"))) {
            throw new IllegalArgumentException("Неверный пол. Ожидалось 'f' или 'm'.");
        }
        return genderStr.toLowerCase().charAt(0);
    }

    private static void writeToFile(String surname, String name,
                                    String middleName, LocalDate dateOfBirth,
                                    long phoneNumber, char gender) throws IOException {
        String filename = surname + ".txt"; // Формирование имени файла
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Формирование строки для записи
        String line = String.format("%s %s %s %s %d %c", surname,
                name, middleName, dateOfBirth.format(formatter), phoneNumber, gender);

        // Запись строки в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(line);
            writer.newLine(); // Переход на новую строку
        }
    }
}