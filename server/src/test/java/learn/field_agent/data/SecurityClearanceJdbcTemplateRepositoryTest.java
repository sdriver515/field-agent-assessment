package learn.field_agent.data;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {
    final static int NEXT_SECURITYCLEARANCE_ID = 4;

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        actual = repository.findById(200);
        assertEquals(null, actual);
    }//shouldFindById

    @Test
    void shouldFindSecurityClearances() {
        List<SecurityClearance> securityClearances = repository.findAll();
        assertNotNull(securityClearances);
        assertTrue(securityClearances.size() > 0);
    }//shouldFindSecurityClearances

    @Test
    void shouldFindSecret() {
        SecurityClearance secret = repository.findById(1);
        assertEquals("Secret", secret.getName());
    }//shouldFindSecret

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = makeSecurityClearance();
        SecurityClearance actual = repository.add(securityClearance);
        assertNotNull(actual);
    }//shouldAdd

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = repository.findById(1);
        securityClearance.setName("Bob");
        assertTrue(repository.update(securityClearance));
    }//shouldUpdate

    @Test
    void shouldDeleteSecurityClearance() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }//shouldDeleteSecurityClearance

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Secret Name");
        return securityClearance;
    }//makeSecurityClearance

}//end