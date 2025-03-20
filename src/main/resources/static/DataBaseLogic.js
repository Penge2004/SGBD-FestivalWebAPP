// This function loads data and displays it in a table
function loadData() {
    let domain = document.getElementById("domainSelect").value;
    let url = `http://localhost:8080/${domain}`;

    // Fetch data from API
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log("Data fetched for domain:", domain, data);
            displayData(data, domain);  // Display the data
        })
        .catch(error => {
            console.error("Error fetching data:", error);
            showErrorMessage("Error fetching data, please try again later.");
        });
}

// Display data in a table format
function displayData(data, domain) {
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
        editBtn.addEventListener("click", () => editRecord(domain, item));  // Call editRecord
        actionsTd.appendChild(editBtn);

        let deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.addEventListener("click", () => deleteRecord(domain, item));  // Call deleteRecord
        actionsTd.appendChild(deleteBtn);

        row.appendChild(actionsTd);
        table.appendChild(row);
    });

    container.appendChild(table);
}

// CREATE: Add a new record for any domain (e.g., user, artist, etc.)
function createRecord() {
    let domain = document.getElementById("domainSelect").value;
    let formContainer = document.getElementById("formContainer");
    formContainer.innerHTML = ""; // Clear any previous forms

    if (domain === "users") {
        formContainer.innerHTML = `
            <h2>Create User</h2>
            <input type="text" id="user_name" placeholder="Name" required />
            <input type="email" id="user_email" placeholder="Email" required />
            <button id="createUserBtn">Create User</button>
        `;
        document.getElementById("createUserBtn").addEventListener("click", submitCreateUser);
    } else if (domain === "artists") {
        formContainer.innerHTML = `
            <h2>Create Artist</h2>
            <input type="text" id="artist_name" placeholder="Name" required />
            <input type="text" id="genre" placeholder="Genre" required />
            <input type="text" id="country" placeholder="Country" required />
            <button id="createArtistBtn">Create Artist</button>
        `;
        document.getElementById("createArtistBtn").addEventListener("click", submitCreateArtist);
    } else if (domain === "performances") {
        formContainer.innerHTML = `
            <h2>Create Performance</h2>
            <input type="number" id="artist_id" placeholder="Artist ID" required />
            <input type="number" id="stage_id" placeholder="Stage ID" required />
            <input type="datetime-local" id="start_time" required />
            <button id="createPerformanceBtn">Create Performance</button>
        `;
        document.getElementById("createPerformanceBtn").addEventListener("click", submitCreatePerformance);
    } else if (domain === "stages") {
        formContainer.innerHTML = `
            <h2>Create Stage</h2>
            <input type="text" id="stage_name" placeholder="Stage Name" required />
            <input type="text" id="location" placeholder="Location" required />
            <button id="createStageBtn">Create Stage</button>
        `;
        document.getElementById("createStageBtn").addEventListener("click", submitCreateStage);
    } else if (domain === "tickets") {
        formContainer.innerHTML = `
            <h2>Create Ticket</h2>
            <input type="number" id="user_id" placeholder="User ID" required />
            <input type="number" id="performance_id" placeholder="Performance ID" required />
            <input type="number" id="price" placeholder="Price" required />
            <select id="ticket_type">
                <option value="VIP">VIP</option>
                <option value="Regular">Regular</option>
            </select>
            <button id="createTicketBtn">Create Ticket</button>
        `;
        document.getElementById("createTicketBtn").addEventListener("click", submitCreateTicket);
    }
}

// Helper function to show error messages
function showErrorMessage(message) {
    let errorContainer = document.getElementById("formContainer");
    errorContainer.innerHTML = `<p class="error">${message}</p>`;
}

// Submit the form for creating a new user
function submitCreateUser() {
    let name = document.getElementById("user_name").value;
    let email = document.getElementById("user_email").value;

    const data = { name: name, email: email };
    submitCreate("users", data, "User created!");
}

// Submit the form for creating a new artist
function submitCreateArtist() {
    let name = document.getElementById("artist_name").value;
    let genre = document.getElementById("genre").value;
    let country = document.getElementById("country").value;

    const data = { name: name, genre: genre, country: country };
    submitCreate("artists", data, "Artist created!");
}

// Submit the form for creating a new performance
function submitCreatePerformance() {
    let artist_id = document.getElementById("artist_id").value;
    let stage_id = document.getElementById("stage_id").value;
    let start_time = document.getElementById("start_time").value;

    const data = { artist_id: artist_id, stage_id: stage_id, start_time: start_time };
    submitCreate("performances", data, "Performance created!");
}

// Submit the form for creating a new stage
function submitCreateStage() {
    let stage_name = document.getElementById("stage_name").value;
    let location = document.getElementById("location").value;

    const data = { name: stage_name, location: location };
    submitCreate("stages", data, "Stage created!");
}

// Submit the form for creating a new ticket
function submitCreateTicket() {
    let user_id = document.getElementById("user_id").value;
    let performance_id = document.getElementById("performance_id").value;
    let price = document.getElementById("price").value;
    let ticket_type = document.getElementById("ticket_type").value;

    const data = { user_id: user_id, performance_id: performance_id, price: price, ticket_type: ticket_type };
    submitCreate("tickets", data, "Ticket created!");
}

// Generalized POST request function for creating any record
function submitCreate(domain, data, successMessage) {
    fetch(`http://localhost:8080/${domain}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log(`${domain} created:`, data);
            showSuccessMessage(successMessage);
            loadData();  // Reload data after creation
        })
        .catch(error => {
            console.error(`Error creating ${domain}:`, error);
            showErrorMessage(`Error creating ${domain}, please try again later.`);
        });
}

// Function to handle Edit action (pre-fill the form with current data)
function editRecord(domain, item) {
    let formContainer = document.getElementById("formContainer");
    formContainer.innerHTML = "";  // Clear previous content

    if (domain === "users") {
        formContainer.innerHTML = `
            <h2>Edit User</h2>
            <input type="text" id="user_name" value="${item.name}" required />
            <input type="email" id="user_email" value="${item.email}" required />
            <button id="updateUserBtn">Update User</button>
        `;
        document.getElementById("updateUserBtn").addEventListener("click", () => submitUpdateUser(item.userid));
    } else if (domain === "artists") {
        formContainer.innerHTML = `
            <h2>Edit Artist</h2>
            <input type="text" id="artist_name" value="${item.name}" required />
            <input type="text" id="genre" value="${item.genre}" required />
            <input type="text" id="country" value="${item.country}" required />
            <button id="updateArtistBtn">Update Artist</button>
        `;
        document.getElementById("updateArtistBtn").addEventListener("click", () => submitUpdateArtist(item.artist_id));
    } else if (domain === "performances") {
        formContainer.innerHTML = `
            <h2>Edit Performance</h2>
            <input type="number" id="artist_id" value="${item.artist_id}" required />
            <input type="number" id="stage_id" value="${item.stage_id}" required />
            <input type="datetime-local" id="start_time" value="${item.start_time}" required />
            <button id="updatePerformanceBtn">Update Performance</button>
        `;
        document.getElementById("updatePerformanceBtn").addEventListener("click", () => submitUpdatePerformance(item.performance_id));
    }
}

// Function to handle the actual update
function submitUpdate(domain, data, id) {
    fetch(`http://localhost:8080/${domain}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(() => {
            showSuccessMessage(`${domain} updated successfully!`);
            loadData();  // Reload data after update
        })
        .catch(error => {
            console.error(`Error updating ${domain}:`, error);
            showErrorMessage(`Error updating ${domain}, please try again later.`);
        });
}

// DELETE: Function to handle the deletion of a record
function deleteRecord(domain, item) {
    const confirmation = confirm(`Are you sure you want to delete this ${domain.slice(0, -1)}?`);
    if (confirmation) {
        fetch(`http://localhost:8080/${domain}/${item.userid || item.artist_id || item.performance_id || item.stage_id || item.ticket_id}`, {
            method: "DELETE",
        })
            .then(() => {
                showSuccessMessage(`${domain.slice(0, -1)} deleted successfully!`);
                loadData();  // Reload data after deletion
            })
            .catch(error => {
                console.error(`Error deleting ${domain}:`, error);
                showErrorMessage(`Error deleting ${domain}, please try again later.`);
            });
    }
}



// Helper function to show success messages
function showSuccessMessage(message) {
    let successContainer = document.getElementById("formContainer");
    successContainer.innerHTML = `<p class="success">${message}</p>`;
}

// Bind event listener to domain change
document.getElementById("domainSelect").addEventListener("change", loadData);

// Initial load
window.onload = function() {
    loadData();
};
