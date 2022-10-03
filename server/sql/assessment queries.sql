use field_agent_test;
select * from security_clearance;
select * from location;
select * from agent;
select * from alias;

select security_clearance_id, name
from security_clearance
where security_clearance_id = 1;

select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active, sc.security_clearance_id, sc.`name` as security_clearance_name
           from agency_agent aa 
           inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id
           where aa.security_clearance_id = 1;
           
select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active, sc.security_clearance_id, sc.`name` as security_clearance_name, a.name, a.persona
           from agency_agent aa 
           inner join alias a on aa.agent_id = a.agent_id
           inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id
           where aa.security_clearance_id = 1;
           
select aa.agency_id, aa.agent_id, aa.identifier, aa.activation_date, aa.is_active, sc.security_clearance_id, sc.name security_clearance_name, a.short_name, a.long_name, al.name as alias_name, al.persona
            from agency_agent aa 
            inner join agency a on aa.agency_id = a.agency_id 
            inner join alias al on aa.agent_id = al.agent_id
            inner join security_clearance sc on aa.security_clearance_id = sc.security_clearance_id 
            ;
            
update alias 
set name = 'Sarah',
persona = 'girl'
where alias_id = 1;
           
