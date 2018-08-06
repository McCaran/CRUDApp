package andersen.Georgiiev.Model;

/**
 * Класс, реализующий сущность разработчика
 */

import andersen.Georgiiev.Database.DatabaseHandler;

public class Developer extends Person {
    private static int totalAmount;
    private int id;

    static {
        totalAmount = DatabaseHandler.countDevelopers();
    }

    private int salary;
    public Developer (String name, String surname, int salary) {
        super(name, surname);
        this.salary = salary;
        id = ++ totalAmount;
    }

    public Developer (int id, String name, String surname, int salary) {
        super(name, surname);
        this.salary = salary;
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }
}
