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

    generateCreateForm(domain, submitCreateUser);
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
    submitCreate("performances", performanceData).then(() => {
        showSuccessMessage("Performance created successfully!");
        loadData();  // Reload the data after creation
    });
}


window.loadData = loadData;
window.createRecord = createRecord;
