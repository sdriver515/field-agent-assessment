package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SecurityClearanceServiceTest {
    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = makeSecurityClearance();
        SecurityClearance mockOut = makeSecurityClearance();
        mockOut.setSecurityClearanceId(1);

        when(repository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }//shouldAdd

    @Test
    void shouldNotAddWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName("   ");

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance = makeSecurityClearance();
        securityClearance.setName(null);
        actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotAddWhenInvalid

    @Test
    void shouldNotAddWhenDuplicateName() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setName("Secret");

        Result<SecurityClearance> actual = service.add(securityClearance);

        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotAddWhenDuplicateName



    @Test
    void shouldNotUpdateWhenInvalid() {
        SecurityClearance securityClearance = makeSecurityClearance();
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName(null);
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);
        securityClearance.setName(null);
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }//shouldNotUpdateWhenInvalid

    @Test
    void shouldNotUpdateMissing() {
        SecurityClearance securityClearance = new SecurityClearance(1, "Sarah");

        when(repository.update(securityClearance)).thenReturn(false);
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }//shouldNotUpdateMissing

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Bingo Ben");
        return securityClearance;
    }//makeSecurityClearance

    //need something for testing deleting

}//end
