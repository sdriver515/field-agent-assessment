package learn.field_agent.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecurityClearance {

    private int securityClearanceId;
    private String name;
    private List<AgencyAgent> agents = new ArrayList<>();
    private AgencyAgent agent;//I added
    public AgencyAgent getAgent() {//I added
        return agent;
    }
    public void setAgent(AgencyAgent agent) {//I added
        this.agent = agent;
    }

    public int getSecurityClearanceId() {
        return securityClearanceId;
    }

    public void setSecurityClearanceId(int securityClearanceId) {
        this.securityClearanceId = securityClearanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<AgencyAgent> getAgents() {
        return new ArrayList<>(agents);
    }

    public void setAgents(List<AgencyAgent> agents) {
        this.agents = agents;
    }

    public SecurityClearance() {
    }

    public SecurityClearance(int securityClearanceId, String name) {
        this.securityClearanceId = securityClearanceId;
        this.name = name;
    }

    public SecurityClearance(AgencyAgent agent) {//I added
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityClearance that = (SecurityClearance) o;
        return securityClearanceId == that.securityClearanceId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityClearanceId, name);
    }

}//end
