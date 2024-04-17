package generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Repository<Repository.Person, Long> repository;

    @BeforeEach
    void setUp() {
        repository = new Repository<>();
        repository.save(new Repository.Person("John", "Doe", 1L));
        repository.save(new Repository.Person("Jane", "Doe", 2L));
    }

    @Test
    void testFindAll() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testSave() {
        Repository.Person person = new Repository.Person("Test", "User", 3L);
        repository.save(person);
        assertEquals(3, repository.findAll().size());
        Optional<Repository.Person> personOptional = repository.findById(3L);
        assertEquals(person, personOptional.orElse(null));
    }

    @Test
    void testFindById() {
        Optional<Repository.Person> personOptional = repository.findById(1L);
        Repository.Person person = personOptional.orElse(null);
        assertNotNull(person);
        assertEquals("John", person.firstName());
        assertEquals("Doe", person.lastName());
        assertEquals(1L, person.id());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Repository.Person> personOptional = repository.findById(100L);
        assertTrue(personOptional.isEmpty());
    }
    @Test
    void testEncrypt() {
        String data = "hello";
        Integer encrypted = Repository.encrypt(data, String::hashCode);
        assertNotNull(encrypted);
        assertEquals(data.hashCode(), encrypted);
    }
}