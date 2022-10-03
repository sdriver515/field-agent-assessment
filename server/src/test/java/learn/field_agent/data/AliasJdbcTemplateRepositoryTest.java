package learn.field_agent.data;

import learn.field_agent.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void shouldFindAll(){
        List<Alias> actual = repository.findAll();
        assertNotNull(actual);
        assertTrue(actual.size()>1, String.valueOf(actual.size()));
    }//shouldFindAll

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertTrue(actual.getAliasId()>1, String.valueOf(actual.getAliasId()));
    }//shouldAdd

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setName("Joe");
        alias.setAliasId(1);
        alias.setPersona("Fake persona");
        assertTrue(repository.update(alias));

        alias.setAliasId(12);
        assertFalse(repository.update(alias));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
    }//shouldDelete

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Sarah");
        alias.setPersona("A person that does not exist.");
        alias.setAgentId(6);
        return alias;
    }//makeAlias

}//end
