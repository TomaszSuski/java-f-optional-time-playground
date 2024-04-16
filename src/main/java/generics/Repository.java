package generics;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findByIndex(long index) {
        return records.get(Long.valueOf(index).intValue());
    }


    public static void main(String[] args) {
        Repository<String> repo = new Repository<>();
        repo.save("house");
        repo.save("tree");
        repo.save("road");

        System.out.println(repo.findAll());
    }
}
