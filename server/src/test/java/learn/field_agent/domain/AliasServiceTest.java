package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AliasServiceTest {

    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias mockOut = makeAlias();
        mockOut.setAliasId(1);

        when(repository.add(alias)).thenReturn(mockOut);

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }//shouldAdd

    @Test
    void shouldRequirePersonaIfDuplicateName() {
        Alias alias = new Alias();
        alias.setName("Penny Smith");
        alias.setAgentId(1);
        Result<Alias> actual = service.add(alias);

        Alias aliasTwo = new Alias();
        alias.setName("Penny Smith");
        alias.setAgentId(2);
        actual = service.add(aliasTwo);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldRequirePersonaIfDuplicateName

    @Test
    void shouldNotAddWhenInvalid() {
        Alias alias = makeAlias();
        alias.setName("   ");

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setName(null);
        actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotAddWhenInvalid

    @Test
    void shouldNotAddWhenDuplicate() {
        Alias alias = new Alias();
        alias.setName("Penny Smith");
        alias.setPersona("RN, hobby artist, single mother of one child");
        alias.setAgentId(1);
        Result<Alias> actual = service.add(alias);

        Alias aliasTwo = new Alias();
        alias.setName("Penny Smith");
        alias.setPersona("RN, hobby artist, single mother of one child");
        alias.setAgentId(2);
        actual = service.add(aliasTwo);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotAddWhenDuplicate

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setAliasId(1);

        when(repository.update(alias)).thenReturn(true);

        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }//shouldUpdate

    @Test
    void shouldNotUpdateNonExistentAlias() {
        Alias alias = new Alias();

        when(repository.update(alias)).thenReturn(false);
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotUpdateNonExistentAlias

    @Test
    void shouldDelete(){
        Alias mockOut = makeAlias();
        mockOut.setAliasId(1);

        when(repository.deleteById(1)).thenReturn(true);
    }//shouldDelete

    @Test
    void shouldNotDeleteNonExistentAlias(){
        Alias mockOut = makeAlias();
        mockOut.setAliasId(1);

        when(repository.deleteById(999)).thenReturn(false);
    }//shouldNotDeleteNonExistentAlias

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setName("Penny Smith");
        alias.setPersona("RN, hobby artist, single mother of one child");
        alias.setAgentId(1);
        return alias;
    }//makeAlias

}//end
