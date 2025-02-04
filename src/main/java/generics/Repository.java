package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Repository<T extends Repository.IDable<V> & Repository.Saveable, V> {

    record Person(String firstName, String lastName, Long id) implements IDable<Long>, Saveable {}
    public interface IDable<U> {
        U id();
    }

    public interface Saveable {}       // just for showing how to use it in the declaration
    private List<T> records = new ArrayList<>();

    public List<T> findAll() {
        return records;
    }

    public T save(T record) {
        records.add(record);
        return record;
    }

    public Optional<T> findById(V id) {
        return records.stream().filter(r -> r.id().equals(id)).findFirst();
    }

    // static generic methods even in generic class have their own types, so T here is not a T from the class"
    // the method has to be marked <T,U,V...> before return type specified
    public static <T,V> V encrypt(T data, Function<T, V> func) {
        return func.apply(data);
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

        // usage of static encrypt method. To use autocompletion from IDE we should specify types at call
        System.out.println(Repository.<String, Integer>encrypt("hello", (String::hashCode)));
    }
}
