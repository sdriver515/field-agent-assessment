import { useState, useEffect } from 'react';

// const AGENT_DATA = [
//     {
//         agentId: 1,
//         firstName: "John",
//         middleName: "B.",
//         lastName: "Smith",
//         dob: '2000/01/01',
//         heightInInches: 65
//     }
// ];

const AGENT_DEFAULT = {
    firstName: "",
    middleName: "",
    lastName: "",
    dob: 0,
    heightInInches: 0
}
function Agents(){
    const endpoint = "http://localhost:8080/api/agent";
    const [agent, setAgent] = useState(AGENT_DEFAULT);
    const [editAgentId, setEditAgentId] = useState(0);
    // const [agents, setAgents] = useState(AGENT_DATA);
    const [agents, setAgents] = useState([]);
    const [currentView, setCurrentView] = useState("List");
    const [errors, setErrors] = useState([]);

    useEffect(() => {
        getAgents();
      }, []);

      const getAgents = () => {
        fetch(endpoint)
          .then((res) => {
            if (res.status === 200) {
              return res.json();
            } else {
              return Promise.reject(`Unexpected status code: ${res.status}`);
            }
          })
          .then((data) => {
            setAgents(data);
          })
          .catch(console.error);
      };

    const handleChange = (event) => {
        const newAgent = {...agent};

        //might delete all this because it is for the checkbox with tracking
        if(event.target.type === 'checkbox'){
            newAgent[event.target.name] = event.target.checked;
        } else {
            newAgent[event.target.name] = event.target.value;
        }
        setAgent(newAgent);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if(editAgentId === 0){
            // agent.agentId = Math.floor(Math.random() * 100000);
            // const newAgents = [...agents];
            // newAgents.push(agent);
            // setAgents(newAgents);
            addAgent();
        } else{
            // agent.agentId = editAgentId;
            // const newAgents = [...agents];
            // const indexToUpdate = newAgents.findIndex(agent => agent.agentId === editAgentId);
            // newAgents[indexToUpdate] = agent;
            // setAgent(newAgents);
            updateAgent();
        }
        // resetState();
    };

    const updateAgent = () => {
        agent.agentId = editAgentId;
        const init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(agent),
        };

    fetch(`${endpoint}/${editAgentId}`, init)
      .then((response) => {
        if (response.status === 204) {
          return null;
        } else if (response.status === 400) {
          return response.json();
        } else {
          return Promise.reject(`Unexpected status code: ${response.status}`);
        }
      })
      .then((data) => {
        if (!data) {
          getAgents();
          resetState();
        } else {
          setErrors(data);
        }
      })
      .catch(console.log);
    };
    
    
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
                getAgents();
                resetState();
              } else {
                setErrors(data);
              }
            })
            .catch((error) => console.log(error));
    };

    const resetState = () => {
        setAgent(AGENT_DEFAULT);
        setEditAgentId(0);
        setCurrentView('List');
        setErrors([]);
    };

    const handleEditAgent = (agentId) => {
        setEditAgentId(agentId);

        const agent = agents.find(
            (agent) => agent.agentId === agentId
        );

        const editAgent = { ...agent };
        setAgent(editAgent);
        setCurrentView("Edit");
    };

    const handleDeleteAgent = (agentId) => {
        const agent = agents.find(
            (agent) => agent.agentId === agentId
        );
        if (
            window.confirm(
                `Delete agent ${agent.firstName} ${agent.middleName} ${agent.lastName} ?`
            )
        ) {
            const init = {
                method: "DELETE",
            };

            fetch(`${endpoint}/${agentId}`, init)
            .then((response) => {
                if(response.status === 204){
                    resetState();
                    getAgents();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .catch(console.log);
        }
    };
    
    return (
        <>
        {(currentView === 'Add' || currentView === 'Edit') && (
            <>
                <h2>
                    {editAgentId > 0 ? 'Update Agent' : 'Add Agent'}
                </h2>

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
                />
            </div>
            <div>
                <div className="mt-4">
                    <button 
                        className="btn btn-success" 
                        type="submit">
                            {editAgentId > 0 ? 'Update Agent' : 'Add Agent'}
                    </button>
                    <button 
                        className="btn btn-warning" 
                        type="button" 
                        onClick={resetState}
                        >Cancel
                    </button>
                </div>
            </div>

        </form>
        </>
        )}
        {currentView === 'List' && (
                <>
                <h2>Agents</h2>
                <button 
                    className = "btn btn-primary" 
                    onClick={() => setCurrentView('Add')}
                    >Add Agent
                </button>
                <table>
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Middle Name</th>
                            <th>Last Name</th>
                            <th>DOB</th>
                            <th>Height In Inches</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        {agents.map(agent => (
                            <tr key={agent.agentId}>
                                <td>{agent.firstName}</td>
                                <td>{agent.middleName}</td>
                                <td>{agent.lastName}</td>
                                <td>{agent.dob}</td>
                                <td>{agent.heightInInches}</td>
                                <td className="buttonContainer">
                                    <button 
                                        className="btn btn-primary"
                                        onClick={() => handleEditAgent(agent.agentId)}
                                        >Edit
                                    </button>
                                    <button 
                                        className="btn btn-danger"
                                        onClick={() => handleDeleteAgent(agent.agentId)}
                                        >Delete
                                    </button>
                                </td>
                            </tr>
                        )
                            )}
                    </tbody>
                </table>
                </>
        )}
        </>
    );
}
    
    export default Agents;