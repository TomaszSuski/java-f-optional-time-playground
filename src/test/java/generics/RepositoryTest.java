package generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(person, repository.findById(3L));
    }

    @Test
    void testFindById() {
        Repository.Person person = repository.findById(1L);
        assertNotNull(person);
        assertEquals("John", person.firstName());
        assertEquals("Doe", person.lastName());
        assertEquals(1L, person.id());
    }

    @Test
    void testFindByIdNotFound() {
        assertThrows(RuntimeException.class, () -> repository.findById(100L));
    }

    @Test
    void testEncrypt() {
        String data = "hello";
        Integer encrypted = Repository.encrypt(data, String::hashCode);
        assertNotNull(encrypted);
        assertEquals(data.hashCode(), encrypted);
    }
}