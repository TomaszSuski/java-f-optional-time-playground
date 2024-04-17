package optionals;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import generics.*;

public class OptionalPlayground {
    record Car(String make, String model, Year year) {}
    record Person(String firstName, String lastName, Car car) {}
    record PersonWithOptCar(Long id, String firstName, String lastName, Optional<Car> car) implements Repository.IDable<Long>, Repository.Saveable {}

    public static void main(String[] args) {
        Person p1 = new Person("John", "Doe", new Car("Toyota", "Corolla", Year.of(2018)));
        Person p2 = new Person("Jane", "Doe", new Car("Tesla", "Model 3", Year.of(2020)));
        Person p3 = null;
        PersonWithOptCar p4 = new PersonWithOptCar(100L, "Will", "Smith", Optional.of(new Car("Bentley", "Continental", Year.of(2018))));
        PersonWithOptCar p5 = new PersonWithOptCar(200L, "Michael", "Jordan", Optional.empty());
        PersonWithOptCar p6 = new PersonWithOptCar(300L, "Elon", "Musk", Optional.of(new Car("Tesla", "Model S", Year.of(2022))));
        PersonWithOptCar p7 = new PersonWithOptCar(400L, "Bill", "Gates", Optional.of(new Car("Porsche", "Taycan", Year.of(2021))));
        PersonWithOptCar p8 = new PersonWithOptCar(500L, "Jeff", "Bezos", Optional.of(new Car("Lamborghini", "Huracan", Year.of(2020))));
        PersonWithOptCar p9 = new PersonWithOptCar(600L, "Mark", "Zuckerberg", Optional.of(new Car("Pagani", "Huayra", Year.of(2019))));
        PersonWithOptCar p10 = null;



        Optional<Person> optPerson = Optional.ofNullable(p3);
        // to get value of optional one may use get() but it is not recommended
        // or use orElse() to provide default value if optional is empty
        // but then ti has to provide default value of the same type as optional
        // here map() is used to get value from optional and apply a function to it
        // it returns another optional with the result of the function but of type of the result
        // and even if the result is null, it will be wrapped in optional
        System.out.println(optPerson
                .map(person -> person.firstName())
                .orElse("Unknown first name"));
        System.out.println();

        Optional<Person> optPerson2 = Optional.ofNullable(p3);
        System.out.println(optPerson2
                .map(Person::car)       // here we get car from person optional, it returns car wrapped in optional
                .map(Car::make)         // here we get make from car optional, it returns make wrapped in optional
                .orElse("Unknown make"));

        Optional<PersonWithOptCar> optPerson3 = Optional.ofNullable(p4);
        System.out.println(optPerson3
//                .map(PersonWithOptCar::car)     // here we get can't car from person optional, it returns optional of car wrapped in another optional
                .flatMap(PersonWithOptCar::car)   // here we get car from person optional using flatMap, it returns optional of car (unwraps one level of optional)
                .map(Car::make)                 // here we get make from car optional, it returns make wrapped in optional
                .orElse("Unknown make"));
        System.out.println();

        Repository<PersonWithOptCar, Long> repo = new Repository<>();
        repo.save(p4);
        repo.save(p5);
        repo.save(p6);
        repo.save(p7);
        repo.save(p8);
        repo.save(p9);
        repo.save(p10);

        System.out.println(repo.findById(300L).map(PersonWithOptCar::firstName).orElse("First name unknown"));
        System.out.println();

        List<Optional<PersonWithOptCar>> people = List.of(
                Optional.of(p4),
                Optional.of(p5),
                Optional.of(p6),
                Optional.of(p7),
                Optional.of(p8),
                Optional.of(p9),
                Optional.ofNullable(p10)
        );

        people.stream()
                .filter(Optional::isPresent)    // filtering out empty optionals
                .map(Optional::get)             // unwrapping optionals (no null values at this point)
                .map(PersonWithOptCar::firstName)
                .forEach(System.out::println);

    }
}
