package andersen.Georgiiev.Model;

import java.util.Objects;

/**
 * Абстрактный класс, определяющий общие черты для наследников
 */

public abstract class Person {
    private int id;
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
