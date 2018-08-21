package andersen.Georgiiev;

import andersen.Georgiiev.Cache.CustomCache;
import andersen.Georgiiev.Database.DatabaseHandler;
import andersen.Georgiiev.Model.Developer;
import andersen.Georgiiev.Model.User;

public class Main {

    public static void main(String[] args) {
        testUsersTable();
        testDevelopersTable();
        System.out.println(CustomCache.get(1, Developer.class));
        System.out.println(CustomCache.get(3, Developer.class));
        CustomCache.print();
    }

    public static void testUsersTable() {
        User user1 = new User("Dmitry", "Georgiiev", 5);
        User user2 = new User("Viktor", "Safronov", 3);
        User user3 = new User("Stanislav", "Donskoy", 7);
        DatabaseHandler.insertUser(user1);
        DatabaseHandler.insertUser(user2);
        DatabaseHandler.insertUser(user3);
        DatabaseHandler.deleteUser(3);
    }

    public static void testDevelopersTable() {
        Developer developer1 = new Developer("Anton", "Osatyuk", 1500);
        Developer developer2 = new Developer("Iliya", "Solovyov", 1500);
        Developer developer3 = new Developer("Mikhail", "Konveschinsky", 1500);
        DatabaseHandler.insertDeveloper(developer1);
        DatabaseHandler.deleteDeveloper(1);
        DatabaseHandler.insertDeveloper(developer2);
        developer2.setSalary(3000);
        DatabaseHandler.updateDeveloper(developer2);
        DatabaseHandler.insertDeveloper(developer3);
    }
}
