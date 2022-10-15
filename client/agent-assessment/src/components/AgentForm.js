import { useState, useEffect } from 'react';
import { useHistory, Link, useParams } from "react-router-dom";

const AGENT_DEFAULT = {
    firstName: "",
    middleName: "",
    lastName: "",
    dob: 0,
    heightInInches: 0
}

function AgentForm(){
    const endpoint = "http://localhost:8080/api/agent";
    const [agent, setAgent] = useState(AGENT_DEFAULT);
    const [editAgentId, setEditAgentId] = useState(0);
    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const { agentId } = useParams();


useEffect(() => {
    if (agentId) {
      setEditAgentId(agentId);
      fetch(`${endpoint}/${agentId}`)
        .then((res) => res.json())
        .then((data) => setAgent(data));
    }
  }, [agentId]);

  const handleChange = (event) => {
    const newAgent = {...agent};

    if(event.target.type === 'checkbox'){
        newAgent[event.target.name] = event.target.checked;
    } else {
        newAgent[event.target.name] = event.target.value;
    }
    newAgent[event.target.name] = event.target.value; //NEW LINE
    setAgent(newAgent);
};

const handleSubmit = (event) => {
    event.preventDefault();
    if(editAgentId === 0){
        addAgent();
    } else{
        updateAgent();
    }
};

const updateAgent = () => {};

const addAgent = () => {
    const init = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(agent),
      };
      fetch(endpoint, init)
        .then((response) => {
          if (response.status === 201 || response.status === 400) {
            return response.json();
          } else {
            return Promise.reject(`Unexpected status code: ${response.status}`);
          }
        })
        .then((data) => {
          if (data.agentId) {
            resetState();
            history.push("/");
          } else {
            setErrors(data);
          }
        })
        .catch((error) => console.log(error));
};

const resetState = () => {
    setAgent(AGENT_DEFAULT);
    setEditAgentId(0);
    setErrors([]);
};

return (
    <>
      <h2>{editAgentId > 0 ? "Update Agent" : "Add Agent"}</h2>

      {errors.length > 0 && (
        <div>
          <h3>The following errors occured:</h3>
          <ul>
            {errors.map((error) => {
              return <li>{error}</li>;
            })}
          </ul>
        </div>
      )}

    <form onSubmit={handleSubmit}>
        <div className="form-group">
            <label htmlFor="firstName">First Name:</label>
            <input 
                id="firstName"
                name="firstName"
                type="text"
                className="form-control"
                value={agent.firstName}
                onChange={handleChange}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="middleName">Middle Name:</label>
            <input 
                id="middleName"
                name="middleName"
                type="text"
                className="form-control"
                value={agent.middleName}
                onChange={handleChange}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="lastName">Last Name:</label>
            <input 
                id="lastName"
                name="lastName"
                type="text"
                className="form-control"
                value={agent.lastName}
                onChange={handleChange}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="dob">DOB:</label>
            <input 
                id="dob"
                name="dob"
                type="date"
                className="form-control"
                value={agent.dob}
                onChange={handleChange}
                required
            />
        </div>
        <div className="form-group">
            <label htmlFor="heightInInches">Height In Inches:</label>
            <input 
                id="heightInInches"
                name="heightInInches"
                type="number"
                className="form-control"
                value={agent.heightInInches}
                onChange={handleChange}
                required
            />
        </div>
        <div className="mt-4">
          <button className="btn btn-success mr-4" type="submit">
            {editAgentId > 0 ? "Update Agent" : "Add Agent"}
          </button>
          <Link className="btn btn-warning" to="/" exact>
            Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default AgentForm;
        
        