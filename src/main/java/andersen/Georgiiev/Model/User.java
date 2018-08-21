package andersen.Georgiiev.Model;

import andersen.Georgiiev.Database.DatabaseHandler;

/**
 * Класс, реализующий сущность пользователя
 */

public class User extends Person {
    private static int totalAmount;
    private int experience;

    static {
        totalAmount = DatabaseHandler.countUsers();
    }

    public User(String name, String surname, int experience) {
        super(++totalAmount, name, surname);
        this.experience = experience;
    }

    public User(int id, String name, String surname, int experience) {
        super(id, name, surname);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
