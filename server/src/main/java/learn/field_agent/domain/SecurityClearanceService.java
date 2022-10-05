package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {
    private final SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
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
//        Result<SecurityClearance> result = new Result<>();
//        List<SecurityClearance> existingSecurityClearances = repository.findAll();
//
//        for(SecurityClearance s: existingSecurityClearances){
//            if(securityClearanceId != s.getSecurityClearanceId()){
//                result.addMessage("This security clearance ID does not exist.", ResultType.INVALID);
//            } else if (securityClearanceId == s.getSecurityClearanceId()){
//
//            }
//        }
        return repository.deleteById(securityClearanceId);
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
