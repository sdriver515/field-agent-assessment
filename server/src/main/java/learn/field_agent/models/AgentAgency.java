package learn.field_agent.models;

import java.time.LocalDate;

public class AgentAgency {

    private int agentId;
    private String identifier;
    private LocalDate activationDate;
    private boolean active;

    private Agency agency;
    private SecurityClearance securityClearance;

    private Alias alias; //I added

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public SecurityClearance getSecurityClearance() {
        return securityClearance;
    }

    public void setSecurityClearance(SecurityClearance securityClearance) {
        this.securityClearance = securityClearance;
    }

    public Alias getAlias() {//I added
        return alias;
    }//getAlias

    public void setAlias(Alias alias) {//I added
        this.alias = alias;
    }//setAlias

}//end
