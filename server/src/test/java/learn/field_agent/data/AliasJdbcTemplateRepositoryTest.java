package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasJdbcTemplateRepositoryTest {
    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    //Something to find alias via agent

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(100, actual.getAliasId());
    }//shouldAdd

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
//        alias.setAliasId(8); // avoid duplicates
//        alias.getAgent().setAgentId(1);
//        assertTrue(repository.update(alias));
//
//        alias.setAliasId(12);
//        assertFalse(repository.update(alias));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }//shouldDelete

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Test Name");
        alias.setPersona("A person that does not exist.");
        alias.setAgentId(100);
        return alias;
    }//makeAlias

}//end
