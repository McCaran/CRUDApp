package andersen.Georgiiev.Cache;

import andersen.Georgiiev.Model.*;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Класс, реализующий кеширование данных
 */

public class CustomCache {
    private static final int CACHE_SIZE = 10;
    private static final int LOWER_CACHE_SIZE;
    private static final int HIGHER_CACHE_SIZE;
    private static LinkedHashSet<WeakReference<Person>> higherCache;
    private static LinkedHashSet<SoftReference<Person>> lowerCache;

    static {
        HIGHER_CACHE_SIZE = (int)Math.round(CACHE_SIZE*0.7);
        LOWER_CACHE_SIZE = CACHE_SIZE - HIGHER_CACHE_SIZE;
        higherCache = new LinkedHashSet<>(LOWER_CACHE_SIZE);
        lowerCache = new LinkedHashSet<>(HIGHER_CACHE_SIZE);
    }

    public static boolean put(Person person) {
        for (SoftReference<Person> reference : lowerCache) {
            if (reference.get().equals(person)) {
                return false;
            }
        }
        for (WeakReference<Person> reference : higherCache) {
            if (reference.get().equals(person)) {
                return false;
            }
        }
        if (higherCache.size() == HIGHER_CACHE_SIZE) {
            removeFirstFromCache(higherCache);
        }
        higherCache.add(new WeakReference<>(person));
        return true;
    }

    public static Person get(int id, Class<? extends Person> clazz) {
        Person person;
        for (SoftReference<Person> reference : lowerCache) {
            person = reference.get();
            if (person.getClass() == clazz && person.getId() == id) return person;
        }
        for (WeakReference<Person> reference : higherCache) {
            person = reference.get();
            if (person.getClass() == clazz && person.getId() == id) {
                if (lowerCache.size() == LOWER_CACHE_SIZE) {
                   removeFirstFromCache(lowerCache);
                }
                lowerCache.add(new SoftReference<>(person));
                removeFromHigherCache(person);
                return person;
            }
        }
        return null;
    }

    private static void removeFirstFromCache(LinkedHashSet cache) {
        Iterator iterator = cache.iterator();
        if (iterator.hasNext()) iterator.next();
        iterator.remove();
    }

    private static void removeFromHigherCache(Person person) {
        for (WeakReference reference: higherCache) {
            if (reference.get().equals(person)) {
                higherCache.remove(reference);
                return;
            }
        }
    }

    public static void print() {
        System.out.println("Верхний кэш: ");
        for (WeakReference reference: higherCache) {
            System.out.println(reference.get());
        }
        System.out.println("Нижний кэш:");
        for (SoftReference reference: lowerCache) {
            System.out.println(reference.get());
        }
    }
}
