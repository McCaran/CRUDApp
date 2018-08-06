package andersen.Georgiiev.Model;

import andersen.Georgiiev.Database.DatabaseHandler;

/**
 * Класс, реализующий сущность пользователя
 */

public class User extends Person {
    private static int totalAmount;
    private int id;
    private int experience;

    static {
        totalAmount = DatabaseHandler.countUsers();
    }

    public User(String name, String surname, int experience) {
        super(name, surname);
        this.experience = experience;
        id = ++totalAmount;
    }

    public User(int id, String name, String surname, int experience) {
        super(name, surname);
        this.experience = experience;
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

}
