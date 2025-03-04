package managers;


import models.LabWork;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Оперирует коллекцией.
 */
public class CollectionManager {
    private int currentId = 1;
    private Map<Integer, LabWork> labWorks = new HashMap<>();
    private Vector<LabWork> collection = new Vector<LabWork>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
    }

    /**
     * @return коллекция.
     */
    public Vector<LabWork> getCollection() {
        return collection;
    }

    /**
     * @return Последнее время инициализации.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        fileManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Получить дракона по ID
     */
    public LabWork byId(int id) {
        return labWorks.get(id);
    }

    /**
     * Содержит ли колекции дракона
     */
    public boolean isСontain(LabWork e) {
        return e == null || byId(e.getId()) != null;
    }


    /**
     * Получить свободный ID
     */
    public int getFreeId() {
        while (byId(currentId) != null)
            if (++currentId < 0)
                currentId = 1;
        return currentId;
    }


    /**
     * Добавляет LabWork
     */
    public boolean add(LabWork d) {
        if (isСontain(d)) return false;
        labWorks.put(d.getId(), d);
        collection.add(d);
        update();
        return true;
    }

    /**
     * Удаляет Labwork по ID
     */
    public boolean remove(int id) {
        var a = byId(id);
        if (a == null) return false;
        labWorks.remove(a.getId());
        collection.remove(a);
        update();
        return true;
    }

    /**
     * Фиксирует изменения коллекции
     */
    public void update() {
        Collections.sort(collection);
    }

    /**
     * Загружает коллекцию из файла.
     *
     * @return true в случае успеха.
     */
    public boolean loadCollection() {
        labWorks.clear();
        fileManager.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (var e : collection)
            if (byId(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId() > currentId) currentId = e.getId();
                labWorks.put(e.getId(), e);
            }
        update();
        return true;
    }

    /**
     * Функция для получения первого элемента в коллекции
     *
     * @return возвращает первый элемент коллекци
     */
    public LabWork getFirst() {
        Iterator<LabWork> iterator = collection.iterator();
        if (iterator.hasNext()) return iterator.next();
        return null;
    }

    /**
     * Метод перемешивает элементы коллекции групп в случайном порядке.
     */
    public String shuffle() {
        Collections.shuffle(collection);
        return null;
    }

    /**
     * Сортирует коллекцию в обратном порядке
     */
    public String reorder() {
        Collections.reverse(collection);
        return null;
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (LabWork labWork : collection) {
            info.append(labWork + "\n\n");
        }
        return info.toString().trim();
    }
}