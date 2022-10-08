// console.log("It's working");
const endpoint = 'http://localhost:8080/api/agent';
let agents = [];
let editAgentId = 0;

function displayList(){
    setCurrentView('List');
    getAgents()
    .then(data => {
        agents = data;
        renderList(data)
    });
}//displayList

function getAgents(){
    return fetch(endpoint)
    .then(response =>{
        return response.json();
    })
}//getAgents

function setCurrentView(view){
    const formContainerElement = document.getElementById('formContainer');
    const listContainerElement = document.getElementById('listContainer');

    switch(view){
        case 'List':
            formContainerElement.style.display = 'none';
            listContainerElement.style.display = 'block';
            break;
        case 'Form':
            formContainerElement.style.display = 'block';
            listContainerElement.style.display = 'none';
    }
}

function renderList(agents){
    const tableBodyElement = document.getElementById('tableRows');  
    const agentsHTML = agents.map(a => {
    return tableBodyElement.innerHTML = `
        <tr>
        <td>${a.firstName}</td>
        <td>${a.middleName}</td>
        <td>${a.lastName}</td>
        <td>${a.dob}</td>
        <td>${a.heightInInches}</td>
        <td>
            <button onclick="handleEditAgent(${a.agentId})">Edit</button>
            <button onclick="handleDeleteAgent(${a.agentId})">Delete</button>
            </td>
        </tr>
        `
        });
        
        console.log("Starting here")
        tableBodyElement.innerHTML = agentsHTML.join('');
}//renderList

//handling submitting
function handleSubmit(event){
    event.preventDefault();
    const firstName = document.getElementById('firstName').value;
    const middleName = document.getElementById('middleName').value;
    const lastName = document.getElementById('lastName').value;
    const dob = document.getElementById('dob').value;
    const heightInInches = document.getElementById('heightInInches').value;

    const agent = {
        firstName,
        middleName,
        lastName,
        dob, //might need to pare something here
        heightInInches: heightInInches ? parseInt(heightInInches) : 0,
    };
    if(editAgentId > 0 ){
        doPut(agent);
    } else{
        doPost(agent);
    }
}//handleSubmit

//handle Add
function handleAddAgent(){
    setCurrentView('Form');
}

//update
function handleEditAgent(agentId){
        const agent = agents.find(agent => agent.agentId === agentId);

        document.getElementById('firstName').value = agent.firstName;
        document.getElementById('middleName').value = agent.middleName;
        document.getElementById('lastName').value = agent.lastName;
        document.getElementById('dob').value = agent.dob;
        document.getElementById('heightInInches').value = agent.heightInInches;

        document.getElementById('formHeading').innerText = "Update Agent";
        document.getElementById('formSubmitButton').innerText = "Update Agent";

        editAgentId = agentId;
        setCurrentView('Form');

}//handleEditAgent

//delete
function handleDeleteAgent(agentId){
    const agent = agents.find(agent => agent.agentId === agentId);
    if(confirm(`Delete the agent ${agent.firstName} ${agent.middleName} ${agent.lastName}?`)){
        const init = {
            method: 'DELETE'
        };
        fetch(`${endpoint}/${agentId}`, init)
        .then(response => {
            if(response.status === 204){
                displayList();
                resetState();
            } else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .catch(console.log);
    }
}//handleDeleteAgent

//handle errors
    function renderErrors(errors){
        const errorsHTML = errors.map(e =>`<li>${e}</li>`);
        const errorsHTMLString = `
            <p>These errors were found: </p>
            <ul>
                ${errorsHTML.join('')}
            </ul>
        `;
        document.getElementById('errors').innerHTML = errorsHTMLString;
    }//renderErrors

    //handle posts and puts
    
    function doPost(agent){
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(agent)
        };
        fetch('http://localhost:8080/api/agent', init)
        .then(response => {
            if(response.status === 201 || response.status === 400){
                return response.json();
            } else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if(data.agentId){
                displayList();
                resetState();
            } else{
                renderErrors(data);
            }
        })
        .catch(error => console.log(error))
    }//doPost

    function doPut(agent){
        agent.agentId = editAgentId;

        const init = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(agent)
        };
        fetch(`http://localhost:8080/api/agent/${editAgentId}`, init)
        .then(response => {
            if(response.status === 204){
                return null;
            } else if(response.status === 400){
                return response.json();
            } else{
                return Promise.reject(`Unexpected staatus code: ${response.status}`);
            }
        })
        .then(data => {
            if(!data){
                displayList();
                resetState();
            } else{
                renderErrors(data);
            }
        })
        .catch(console.log);
    }//doPut

    function resetState(){
        document.getElementById('form').reset();
        document.getElementById('formSubmitButton').innerText = 'Add agent';
        document.getElementById('errors').innerHTML = '';
        editAgentId = 0;
        setCurrentView('List');
    }//resetState

displayList();




