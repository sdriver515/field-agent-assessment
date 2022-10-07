package learn.field_agent.data.mappers;

import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.AgentAgency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperForSecurityClearanceIdsFromAgencyAgent implements RowMapper<AgencyAgent> {

    @Override
    public AgencyAgent mapRow(ResultSet resultSet, int i) throws SQLException {
        AgencyAgent agencyAgent = new AgencyAgent();
        agencyAgent.setAgencyId(resultSet.getInt("agency_id"));
        agencyAgent.setAgentId(resultSet.getInt("agent_id"));
        agencyAgent.setIdentifier(resultSet.getString("identifier"));
        agencyAgent.setSecurityClearanceId(resultSet.getInt("security_clearance_id"));
        agencyAgent.setActivationDate(resultSet.getDate("activation_date").toLocalDate());
        agencyAgent.setActive(resultSet.getBoolean("is_active"));

        return agencyAgent;
    }
}
