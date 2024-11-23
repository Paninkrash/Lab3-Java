import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ФИО в формате:\nФамилия Имя Отчество");
        String nickname = scanner.nextLine();
        System.out.println("Введите дату в формате:\nДД.ММ.ГГГГ");
        String date = scanner.nextLine();

        String pattern = "([А-Яа-яЁё]*\\s[А-Яа-яЁё]*\\s[А-Яа-яЁё]*)";
        if (!nickname.matches(pattern))
            throw new IllegalArgumentException("Неправильный формат имени.");

        pattern = "(\\d{2}[.]\\d{2}[.]\\d{4})";
        if (!date.matches(pattern))
            throw new IllegalArgumentException("Неправильный формат даты.");

        String []ThreeParts = nickname.split(" ");
        String name = ThreeParts[1], surname =  ThreeParts[0], patronymic = ThreeParts[2];
        System.out.println(surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".");

        if (patronymic.endsWith("вна")){
            System.out.println("Пол: Ж");
        }
        else if (patronymic.endsWith("вич")){
            System.out.println("Пол: М");
        }
        else {
            System.out.println("Пол: ОПРЕДЕЛИТЬ_НЕ_УДАЛОСЬ");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthDate = LocalDate.parse(date, formatter);
        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("Введена дата из будущего.");
        }
        int years = Period.between(birthDate, LocalDate.now()).getYears();

        System.out.println(years + " " + ageEnding(years));
    }

    private static String ageEnding(int age){
        if (1 == age % 10 && age != 11){
            return "год";
        }
        else if (age % 10 >= 2 && age % 10 <= 4 && (age % 100 < 10 || age % 100 >= 20)){
            return "года";
        }
        else {
            return "лет";
        }
    }
}
