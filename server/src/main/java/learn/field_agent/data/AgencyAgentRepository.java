package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.SecurityClearance;

import java.util.List;

public interface AgencyAgentRepository {


    List<AgencyAgent> findBySecurityAgentId(int securityClearanceId);


    boolean add(AgencyAgent agencyAgent);

    boolean update(AgencyAgent agencyAgent);

    boolean deleteByKey(int agencyId, int agentId);
}
