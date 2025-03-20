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

    generateCreateForm(domain);
}

// Handle other CRUD operations like update and delete similarly

window.loadData = loadData;
window.createRecord = createRecord;
