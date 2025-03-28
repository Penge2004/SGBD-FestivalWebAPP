// domUtils.js
import {deleteRecord} from "/DataService.js";
import {generateUpdateForm} from "./FormUtils.js";

// Function to show the data in a table
export function displayData(data, domain) {
    let container = document.getElementById("dataContainer");
    container.innerHTML = "";  // Clear previous content

    if (data.length === 0) {
        container.innerHTML = "<p>No data available</p>";
        return;
    }

    let headers, keyMapping;
    if (domain === "users") {
        headers = ["User ID", "Name", "Email", "Actions"];
        keyMapping = ["userid", "name", "email"];
    } else if (domain === "artists") {
        headers = ["Artist ID", "Name", "Genre", "Country", "Actions"];
        keyMapping = ["artist_id", "name", "genre", "country"];
    } else if (domain === "performances") {
        headers = ["Performance ID", "Artist", "Stage", "Start Time", "Actions"];
        keyMapping = ["performance_id", "artist_id", "stage_id", "start_time"];
    } else if (domain === "stages") {
        headers = ["Stage ID", "Name", "Location", "Actions"];
        keyMapping = ["stage_id", "name", "location"];
    } else if (domain === "tickets") {
        headers = ["Ticket ID", "User ID", "Performance", "Price", "Ticket Type", "Actions"];
        keyMapping = ["ticket_id", "user.userid", "performance.performance_id", "price", "ticket_type"];
    }

    let table = document.createElement("table");

    let headerRow = document.createElement("tr");
    headers.forEach(header => {
        let th = document.createElement("th");
        th.textContent = header;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);

    // Iterate through each item in the response data
    data.forEach(item => {
        let row = document.createElement("tr");

        keyMapping.forEach(key => {
            let td = document.createElement("td");

            let keys = key.split('.');  // Split for nested properties
            let value = item;
            keys.forEach(k => {
                value = value && value[k] !== undefined ? value[k] : "N/A";
            });

            td.textContent = value !== undefined ? value : "N/A";
            row.appendChild(td);
        });

        // Add Edit and Delete buttons
        let actionsTd = document.createElement("td");
        let editBtn = document.createElement("button");
        editBtn.textContent = "Edit";
        actionsTd.appendChild(editBtn);

        // Add Edit button functionality in the displayData function
        editBtn.addEventListener("click", function() {
            // Get the record ID from the row or the item (e.g., stage_id)
            let id;
            if (domain === 'performances') {
                console.log("Extracting performance_id:", item.performance_id); // Debugging log
                id = item.performance_id;
            } else if (domain === 'tickets') {
                console.log("Extracting ticket_id:", item.ticket_id); // Debugging log
                id = item.ticket_id;
            } else if (domain === 'stages') {
                console.log("Extracting stage_id:", item.stage_id); // Debugging log
                id = item.stage_id;
            } else if (domain === 'artists') {
                console.log("Extracting artist_id:", item.artist_id); // Debugging log
                id = item.artist_id;
            } else if (domain === 'users') {
                console.log("Extracting userid:", item.userid); // Debugging log
                id = item.userid;
            }

            generateUpdateForm(domain, id, item);
        });

        // Delete button with an event listener
        let deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";

        // Add delete button functionality
        deleteBtn.addEventListener("click", () => {
            // Extract the ID of the record from the row (depends on your domain)
            const recordId = item[keyMapping[0]];  // Assuming the first key is the ID
            deleteRecord(domain, recordId).then(() => {
                showSuccessMessage(`${domain.slice(0, -1).toUpperCase()} deleted successfully!`);
                loadData();  // Reload the data after deletion
            }).catch((error) => {
                showErrorMessage(`Error deleting ${domain.slice(0, -1)}: ${error.message}`);
            });
        });

        actionsTd.appendChild(deleteBtn);
        row.appendChild(actionsTd);
        table.appendChild(row);
    });

    container.appendChild(table);
}

// Function to show error messages
export function showErrorMessage(message) {
    let errorContainer = document.getElementById("formContainer");
    errorContainer.innerHTML = `<p class="error">${message}</p>`;
}

// Function to show success messages
export function showSuccessMessage(message) {
    let successContainer = document.getElementById("formContainer");
    successContainer.innerHTML = `<p class="success">${message}</p>`;
}