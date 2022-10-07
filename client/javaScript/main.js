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




