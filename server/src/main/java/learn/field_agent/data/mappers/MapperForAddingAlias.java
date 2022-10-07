package learn.field_agent.data.mappers;

import learn.field_agent.models.AgentAgency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperForAddingAlias implements RowMapper<AgentAgency> {
    @Override
    public AgentAgency mapRow(ResultSet resultSet, int i) throws SQLException {
        AgentAgency agentAgency = new AgentAgency();
        agentAgency.setAgentId(resultSet.getInt("agent_id"));
        agentAgency.setIdentifier(resultSet.getString("identifier"));
        agentAgency.setActivationDate(resultSet.getDate("activation_date").toLocalDate());
        agentAgency.setActive(resultSet.getBoolean("is_active"));

        SecurityClearanceMapper securityClearanceMapper = new SecurityClearanceMapper();
        agentAgency.setSecurityClearance(securityClearanceMapper.mapRow(resultSet, i));

        AgencyMapper agencyMapper = new AgencyMapper();
        agentAgency.setAgency(agencyMapper.mapRow(resultSet, i));

        AliasMapperV2 aliasMapper = new AliasMapperV2();
        agentAgency.setAlias(aliasMapper.mapRow(resultSet, i));

        return agentAgency;
    }
}//end
