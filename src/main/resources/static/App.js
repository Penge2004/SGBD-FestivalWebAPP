// app.js
import { fetchData, submitCreate, submitUpdate, deleteRecord } from './dataService.js';
import { displayData, showErrorMessage, showSuccessMessage } from './domUtils.js';
import { generateCreateForm } from './formUtils.js';

// Load data based on the selected domain
function loadData() {

    const domainSelect = document.getElementById('domainSelect');
    const domain = domainSelect.value;

    fetchData(domain).then(data => {
        displayData(data, domain);
    }).catch(error => {
        showErrorMessage(error.message);
    });
}

// Create new record
function createRecord() {
    const domainSelect = document.getElementById('domainSelect');
    const domain = domainSelect.value;

    console.log("Create record is working") //debug

    // Mapping domain to the corresponding submit function
    const submitFunctions = {
        "users": submitCreateUser,
        "artists": submitCreateArtist,
        "performances": submitCreatePerformance,
        "stages" : submitCreateStage,
        "tickets" : submitCreateTicket
    };

    generateCreateForm(domain, submitFunctions[domain]);
}

// Handle other CRUD operations like update and delete similarly
// Function to submit the new user data
function submitCreateUser() {

    const name = document.getElementById("user_name").value;
    const email = document.getElementById("user_email").value;

    const userData = { name, email };  // Create an object with the input values

    // Call the submitCreate function and pass the data to the API
    submitCreate("users", userData).then(() => {
        showSuccessMessage("User created successfully!");
        loadData();  // Reload the data after creation
    });
}

// Function to submit the new artist data
function submitCreateArtist() {
    const name = document.getElementById("artist_name").value;
    const genre = document.getElementById("genre").value;
    const country = document.getElementById("country").value;

    const artistData = { name, genre, country };  // Create an object with the input values

    // Call the submitCreate function and pass the data to the API
    submitCreate("artists", artistData).then(() => {
        showSuccessMessage("Artist created successfully!");
        loadData();  // Reload the data after creation
    });
}

// Function to submit the new performance data
function submitCreatePerformance() {
    const artist_id = document.getElementById("artist_id").value;
    const stage_id = document.getElementById("stage_id").value;
    const start_time = document.getElementById("start_time").value;

    const performanceData = { artist_id, stage_id, start_time };  // Create an object with the input values

    // Call the submitCreate function and pass the data to the API
    console.log(performanceData) // DEBUG
    submitCreate("performances", performanceData).then(() => {
        showSuccessMessage("Performance created successfully!");
        loadData();  // Reload the data after creation
    });
}

function submitCreateStage(){

    const stage_name = document.getElementById("name").value;
    const stage_location = document.getElementById("location").value;

    console.log(stage_name,stage_location);//debug

    const stageData = {
        name: stage_name,        // Change 'stage_name' to 'name'
        location: stage_location // Change 'stage_location' to 'location'
    };

    console.log(stageData);//debug

    submitCreate("stages", stageData).then(() => {
        showSuccessMessage("Stage created successfully!");
        loadData();
    })
}

function submitCreateTicket(){
    const user_id = document.getElementById("userid").value;
    const performance_id = document.getElementById("performance_id").value;
    const price = document.getElementById("price").value;
    const type = document.getElementById("type").value;

    const ticketData = {user_id, performance_id, price, type};

    submitCreate("tickets", ticketData).then(() => {
        showSuccessMessage("Ticket created successfully!");
        loadData();
    })
}

// Function to submit the updated user data
export function submitUpdateUser(id) {
    const name = document.getElementById("user_name").value;
    const email = document.getElementById("user_email").value;

    const userData = { name, email };  // Create an object with the updated values

    submitUpdate("users", id, userData).then(() => {
        showSuccessMessage("User updated successfully!");
        loadData();  // Reload the data after updating
    });
}

// Function to submit the updated artist data
export function submitUpdateArtist(id) {
    const name = document.getElementById("artist_name").value;
    const genre = document.getElementById("genre").value;
    const country = document.getElementById("country").value;

    const artistData = { name, genre, country };  // Create an object with the updated values

    submitUpdate("artists", id, artistData).then(() => {
        showSuccessMessage("Artist updated successfully!");
        loadData();  // Reload the data after updating
    });
}

// Function to submit the updated performance data
export function submitUpdatePerformance(id) {
    const artist_id = document.getElementById("artist_id").value;
    const stage_id = document.getElementById("stage_id").value;
    const start_time = document.getElementById("start_time").value;

    const performanceData = { artist_id, stage_id, start_time };

    submitUpdate("performances", id, performanceData).then(() => {
        showSuccessMessage("Performance updated successfully!");
        loadData();  // Reload the data after updating
    });
}

// Function to submit the updated stage data
export function submitUpdateStage(id) {
    const stage_name = document.getElementById("name").value;
    const stage_location = document.getElementById("location").value;

    const stageData = { name: stage_name, location: stage_location };

    submitUpdate("stages", id, stageData).then(() => {
        showSuccessMessage("Stage updated successfully!");
        loadData();  // Reload the data after updating
    });
}

export function submitUpdateTicket(id){
    const user_id = document.getElementById("userid").value;
    const performance_id = document.getElementById("performance_id").value;
    const price = document.getElementById("price").value;
    const type = document.getElementById("type").value;

    const ticketData = {user_id, performance_id, price, type};

    submitUpdate("tickets", id,  ticketData).then(() => {
        showSuccessMessage("Ticket updated successfully!");
        loadData();
    })
}

window.loadData = loadData;
window.createRecord = createRecord;
