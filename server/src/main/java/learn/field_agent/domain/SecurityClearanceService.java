package learn.field_agent.domain;

import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.data.AgencyRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {
    private final SecurityClearanceRepository repository;
    private final AgencyAgentRepository agencyAgentRepository;

    public SecurityClearanceService(SecurityClearanceRepository repository,AgencyAgentRepository agencyAgentRepository) {
        this.repository = repository;
        this.agencyAgentRepository = agencyAgentRepository;
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("securityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = repository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }//add

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }//findAll

    public SecurityClearance findById(int securityClearanceId) {
        return repository.findById(securityClearanceId);
    }//findById

    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("securityClearanceId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(securityClearance)) {
            String msg = String.format("securityClearanceId: %s, not found", securityClearance.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }//updates

    public boolean deleteById(int securityClearanceId) {
        Result<SecurityClearance> result = new Result<>();
        List<AgencyAgent> existingAgencyAgents = agencyAgentRepository.findBySecurityAgentId(securityClearanceId);
        if(existingAgencyAgents.size() > 0) {
            return repository.deleteById(securityClearanceId);
        }
        if(existingAgencyAgents.size() < 0) {
            result.addMessage("securityClearance cannot be null", ResultType.INVALID);
        }
        return false; //double check this goes through
    }//deleteById

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();
        List<SecurityClearance> existingSecurityClearances = repository.findAll();

        if (securityClearance == null) {
            result.addMessage("securityClearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("name is required", ResultType.INVALID);
        }

        for(SecurityClearance s: existingSecurityClearances){
            if(securityClearance.getName().equalsIgnoreCase(s.getName())){
                result.addMessage("You cannot have repeat names.", ResultType.INVALID);
            }}

        return result;
    }//validate

}//end
