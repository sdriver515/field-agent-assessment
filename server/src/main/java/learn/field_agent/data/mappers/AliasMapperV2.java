package learn.field_agent.data.mappers;

import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AliasMapperV2 implements RowMapper<Alias> {

    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException {
        Alias alias = new Alias();
        alias.setAliasId(resultSet.getInt("alias_id"));
        if (resultSet.getString("name") != null){alias.setName(resultSet.getString("name"));}
        alias.setPersona(resultSet.getString("persona"));
        return alias;
    }
}//end
