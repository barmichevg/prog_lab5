package managers;


import models.LabWork;
import java.time.LocalDate;
import java.util.*;


/**
 * Оперирует коллекцией.
 */
public class CollectionManager {
    private int currentId = 1;
    private Map<Integer, LabWork> labWorks = new HashMap<>();

    private ArrayDeque<String> logStack = new ArrayDeque<String>();//????


    private Vector<LabWork> collection = new Vector<LabWork>();
    private LocalDate lastInitTime;
    private LocalDate lastSaveTime;
    private final FileManager fileManager;
    public boolean isAscendingSort;

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
    public LocalDate getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения.
     */
    public LocalDate getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        fileManager.writeCollection(collection, logStack);
        lastSaveTime = LocalDate.now();
    }

    /**
     * Получить Labwork по ID
     */
    public LabWork byId(int id) { return labWorks.get(id); }

    /**
     * Содержит ли колекции Labwork
     */
    public boolean isСontain(LabWork e) { return e == null || byId(e.getId()) != null; }

    /**
     * Получить свободный ID
     */
    public int getFreeId() {
        while (byId(++currentId) != null);
        return currentId;
    }

//    /**???
//     * Добавляет Labwork
//     */
//    public boolean add(LabWork a) {
//        if (isСontain(a)) return false;
//        labWorks.put(a.getId(), a);
//        collection.add(a);
//        update();
//        return true;
//    }
    /**???
     * Добавляет Labwork
     */
    public boolean add(LabWork a) {
        if (a == null) return false;
        labWorks.put(a.getId(), a);
        collection.add(a);
        update();
        return true;
    }

//    /**???????
//     * Получить дракона из удалённых
//     */
//    public Dragon byDieId(long id) { try{for (var e:collectionDie)if (e.getId()==id)return e;return null;} catch (IndexOutOfBoundsException e) { return null; } }
//    /**
//     * Добавляет дракона по ID из удалённых
//     */
//    public boolean add(long id) {
//        Dragon ret = byDieId(id);
//        if (ret == null) return false;
//        dragons.put(ret.getId(), ret);
//        collection.add(ret);
//        collectionDie.remove(ret);
//        update();
//        return true;
//    }

    /**
     * @return true в случае успеха.
     */
    public boolean swap(int id, int repId) {
        var e = byId(id);
        var re = byId(repId);
        if (e == null) return false;
        if (re == null) return false;
        var ind = collection.indexOf(e);
        var rind = collection.indexOf(re);
        if (ind < 0) return false;
        if (rind < 0) return false;
        e.setId(repId);
        re.setId(id);
        labWorks.put(e.getId(), e);
        labWorks.put(re.getId(), re);
        update();
        return true;
    }

//    /**???
//     * Удаляет Labwork по ID
//     */
//    public boolean remove(int id) {
//        var a = byId(id);
//        if (a == null) return false;
//        labWorks.remove(a.getId());
//        collection.remove(a);
//        update();
//        return true;
//    }
//    /**
//     * Удаляет Labwork по ID
//     */
//    public boolean remove(int id) {
//        LabWork ret = byId(id);
//        if (ret == null) return false;
//        var ind = collection.indexOf(ret);
//        if (ind < 0) return false;
//        collection.remove(ret);
////        collectionDie.add(ret);
//        update();
//        return true;
//    }
    /**
     * Удаляет Labwork по ID
     */
    public boolean remove(int id) {
        LabWork ret = byId(id);
        if (ret == null) return false;
        var ind = collection.indexOf(ret);
        if (ind < 0) return false;
        collection.remove(ret);

        update();
        return true;
    }

    /**
     * Метод перемешивает элементы коллекции групп в случайном порядке.
     */
    public String shuffle() {
        Collections.shuffle(collection);
        return null;
    }

    public void deb2(int t) {
        System.out.println("    ===" + t+ "===");
        System.out.println(this);
        for (var ee : logStack)
            System.out.println("    "+t+"_" + ee);
        System.out.println("    ======");

    }

    /**
     * Создает транзакцию или добавляет операцию в транзакцию
     */
    public void addLog(String cmd, boolean isFirst) {
        if (isFirst)
            logStack.push("+");
        if (!cmd.equals(""))
            logStack.push(cmd);
    }

    /**?????????????
     * Фиксирует изменения коллекции
     */
    public void update() {
        // Создаем компаратор для LabWork
        Comparator<LabWork> comparator = new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
//                 Здесь нужно определить логику сравнения объектов LabWork
//                 Например, если есть поле id:
                return Integer.compare(o1.getId(), o2.getId());
//                 Или если нужно сравнивать по нескольким полям:
//                 int nameComparison = o1.getName().compareTo(o2.getName());
//                 if (nameComparison != 0) return nameComparison;
//                 return Integer.compare(o1.getId(), o2.getId());
            }
        };
        Collections.sort(collection, comparator);
        if (isAscendingSort) Collections.reverse(collection);
    }
//    /**
//     * Фиксирует изменения коллекции
//     */
//    public void update() {
//        Collections.sort(collection);
//        if (isAscendingSort) Collections.reverse(collection);
//    }
//    /**
//     * Обновляет Labwork
//     */
//    public boolean update(LabWork a) {
//        if (!isСontain(a)) return false;
//        collection.remove(byId(a.getId()));
//        labWorks.put(a.getId(), a);
//        collection.add(a);
//        update();
//        return true;
//    }

    /**
     * Загружает коллекцию из файла.
     * @return true в случае успеха.
     */
    public boolean loadCollection() {
        labWorks.clear();
        fileManager.readCollection(collection, logStack);//dumpManager.readCollection(collection, collectionDie, logStack);
        lastInitTime = LocalDate.now();
        for (var e : collection)
            if (byId(e.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (e.getId()>currentId) currentId = e.getId();
                labWorks.put(e.getId(), e);
            }
        update();
        return true;
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (LabWork labWork : collection) {
            info.append(labWork+"\n\n");
        }
        return info.toString().trim();
    }
}