package andersen.Georgiiev.Model;

/**
 * Класс, реализующий сущность разработчика
 */

import andersen.Georgiiev.Database.DatabaseHandler;


public class Developer extends Person {
    private static int totalAmount;

    static {
        totalAmount = DatabaseHandler.countDevelopers();
    }

    private int salary;
    public Developer (String name, String surname, int salary) {
        super(++totalAmount, name, surname);
        this.salary = salary;
    }

    public Developer (int id, String name, String surname, int salary) {
        super(id, name, surname);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
