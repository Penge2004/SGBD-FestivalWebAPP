// Create a new record based on selected domain
export function createRecord() {
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
