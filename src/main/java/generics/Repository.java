package generics;

import java.util.ArrayList;
import java.util.List;

public class Repository<T extends Repository.IDable<V> & Repository.Saveable, V> {

    record Person(String firstName, String lastName, Long id) implements IDable<Long>, Saveable {}
    interface IDable<U> {
        U id();
    }

    interface Saveable {}       // just for showing how to use it in the declaration
    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    T findById(V id) {
        return records.stream().filter(r -> r.id().equals(id)).findFirst().orElseThrow();
    }


    public static void main(String[] args) {
//        Repository<String> repo = new Repository<>();
//        repo.save("house");
//        repo.save("tree");
//        repo.save("road");
//
//        System.out.println(repo.findAll());

        Repository<Person, Long> pRepo = new Repository<>();
        pRepo.save(new Person("John", "Doe", 1L));
        pRepo.save(new Person("Jane", "Doe", 2L));

        System.out.println(pRepo.findAll());
        System.out.println(pRepo.findById(1L));
    }
}
