// Handle the Edit action by pre-filling the form with current data
import {showErrorMessage, showSuccessMessage} from "./utils";
import {loadData} from "./loadData";

export function editRecord(domain, item) {
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

// Generalized update function for any domain
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
