package generics;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    record Person(String firstName, String lastName) {}
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

        Repository<Person> pRepo = new Repository<>();
        pRepo.save(new Person("John", "Doe"));
        pRepo.save(new Person("Jane", "Doe"));

        System.out.println(pRepo.findAll());
    }
}
