package optionals;

import java.time.Year;
import java.util.Optional;

public class OptionalPlayground {
    record Car(String make, String model, Year year) {}
    record Person(String firstName, String lastName, Car car) {}
    record PersonWithOptCar(String firstName, String lastName, Optional<Car> car) {}

    public static void main(String[] args) {
        Person p1 = new Person("John", "Doe", new Car("Toyota", "Corolla", Year.of(2018)));
        Person p2 = new Person("Jane", "Doe", new Car("Tesla", "Model 3", Year.of(2020)));
        Person p3 = null;
        PersonWithOptCar p4 = new PersonWithOptCar("Will", "Smith", Optional.of(new Car("Bentley", "Continental", Year.of(2018))));

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
    }
}
