package learn.field_agent.models;

import java.time.LocalDate;

public class AgencyAgent {

    private int agencyId;
    private String identifier;
    private LocalDate activationDate;
    private boolean active;

    private Agent agent;
    private SecurityClearance securityClearance;
    private int agentId; //I added

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getSecurityClearanceId() {
        return securityClearanceId;
    }

    public void setSecurityClearanceId(int securityClearanceId) {
        this.securityClearanceId = securityClearanceId;
    }

    private int securityClearanceId; //I added

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public SecurityClearance getSecurityClearance() {
        return securityClearance;
    }

    public void setSecurityClearance(SecurityClearance securityClearance) {
        this.securityClearance = securityClearance;
    }
}
