
//Ввод с консоли в формате Ф.И.О Возраст пол
//Выход из режима вода по горячей кнопке
//Вывод несортированного списка в формате Иванов И.И. 32M
//Два варианта продолжения, либо вывод списка сортированного по возрасту и полу либо завершение приложения.
//Реализовать сортировку, по возможности, с использованием дополнительного индексного списка.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Person> persons = new ArrayList<>();

        while (true) {
            System.out.println("Введите Ф.И.О, возраст и пол через пробел (например, Иванов Иван Иванович 32 M), либо нажмите q для выхода:");
            String input = scanner.nextLine().trim();

            if (input.equals("q")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 4) {
                System.out.println("Некорректный ввод!");
                continue;
            }

            String fullName = parts[0] + " " + parts[1] + " " + parts[2];
            int age = Integer.parseInt(parts[3]);
            char gender = parts[4].charAt(0);

            Person person = new Person(fullName, age, gender);
            persons.add(person);
        }

        System.out.println("Список людей (несортированный):");
        for (Person person : persons) {
            System.out.println(person.toString());
        }

        while (true) {
            System.out.println("Введите s для сортировки списка по возрасту и полу, либо q для выхода:");
            String input = scanner.nextLine().trim();

            if (input.equals("q")) {
                break;
            }

            if (input.equals("s")) {
                Collections.sort(persons, new PersonComparator());
                System.out.println("Список людей (отсортированный):");
                for (Person person : persons) {
                    System.out.println(person.toString());
                }
            } else {
                System.out.println("Некорректный ввод!");
            }
        }

        scanner.close();
    }

}

class Person {
    private String fullName;
    private int age;
    private char gender;

    public Person(String fullName, int age, char gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return fullName + " " + age + gender;
    }
}

class PersonComparator implements java.util.Comparator<Person> {
    public int compare(Person person1, Person person2) {
        int result = Integer.compare(person1.getAge(), person2.getAge());
        if (result == 0) {
            result = Character.compare(person1.getGender(), person2.getGender());
        }
        return result;
    }
}