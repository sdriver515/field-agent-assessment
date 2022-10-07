package learn.field_agent.data;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;

import java.util.List;

public interface AliasRepository {
    List<Alias> findAll()//findAll
    ;

    Alias add(Alias alias);
    boolean update(Alias alias);
    boolean deleteById(int aliasId);
}//end
