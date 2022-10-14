import { useState, useEffect } from 'react';
import { Link } from "react-router-dom";

function AgentList(){
    const endpoint = "http://localhost:8080/api/agent";
    const [agents, setAgents] = useState([]);

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
                <h2>Agents</h2>
                <Link 
                    className = "btn btn-primary" 
                    to="/agents/add">
                    Add Agent
                </Link>
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
                                    <Link 
                                        className="btn btn-primary"
                                        to={`/agents/edit/${agent.agentId}`}
                                        >Edit
                                    </Link>
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
            );

}
export default AgentList;