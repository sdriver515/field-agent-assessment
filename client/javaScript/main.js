// console.log("It's working");
const endpoint = 'http://localhost:8080/api/agent';
let agents = [];
let editAgentId = 0;

function init(){
    getAgents()
    .then(data => renderList(data));
}//init

init();

function getAgents(){
    return fetch(endpoint)
    .then(response =>{
        return response.json();
    })
}//getAgents

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
            <button>Edit</button>
            <button>Delete</button>
            </td>
        </tr>
        `
        });
        
        console.log("Starting here")
        tableBodyElement.innerHTML = agentsHTML.join('');
}//renderList

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
            console.log(data);
        } else{
            console.log(data);
        }
    })
    .catch(error => console.log(error))
}//handleSubmit




