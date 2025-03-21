// formUtils.js

// Function to generate a form for creating a record
import {
    submitUpdateArtist,
    submitUpdatePerformance,
    submitUpdateStage,
    submitUpdateTicket,
    submitUpdateUser
} from "./App.js";

export function generateCreateForm(domain, item) {
    let formContainer = document.getElementById("formContainer");
    formContainer.innerHTML = "";  // Clear previous content

    if (domain === "users") {

        formContainer.innerHTML = `
            <h2>Create User</h2>
            <input type="text" id="user_name" placeholder="Name" required />
            <input type="email" id="user_email" placeholder="Email" required />
            <button id="createUserBtn">Create User</button>
        `;
        // Attach event listener to the "Create User" button
        document.getElementById("createUserBtn").addEventListener("click", item);

    } else if (domain === "artists") {
        formContainer.innerHTML = `
            <h2>Create Artist</h2>
            <input type="text" id="artist_name" placeholder="Name" required />
            <input type="text" id="genre" placeholder="Genre" required />
            <input type="text" id="country" placeholder="Country" required />
            <button id="createArtistBtn">Create Artist</button>
        `;
        document.getElementById("createArtistBtn").addEventListener("click", item);
    } else if (domain === "performances") {
        formContainer.innerHTML = `
            <h2>Create Performance</h2>
            <input type="number" id="artist_id" placeholder="Artist ID" required />
            <input type="number" id="stage_id" placeholder="Stage ID" required />
            <input type="datetime-local" id="start_time" required />
            <button id="createPerformanceBtn">Create Performance</button>
        `;
        document.getElementById("createPerformanceBtn").addEventListener("click", item);
    } else if (domain === "stages") {
        formContainer.innerHTML = `
            <h2>Create Stage</h2>
            <input type="text" id="name" placeholder="Stage name" required />
            <input type="text" id="location" placeholder="Location" required />
            <button id="createStageBtn">Create Stage</button>
        `;
        document.getElementById("createStageBtn").addEventListener("click", item);
    }

    else if (domain === "tickets") {
        formContainer.innerHTML = `
            <h2>Create Ticket</h2>
            <input type="number" id="userid" placeholder="User ID" required />
            <input type="number" id="performance_id" placeholder="Performance ID" required />
            <input type="number" id="price" placeholder="Price" required />
            <input type="text" id="type" placeholder="Type" required />
            <button id="createTicketBtn">Create Ticket</button>
        `;
        document.getElementById("createTicketBtn").addEventListener("click", item);
    }
}

// function to generate the update form based on domain and record ID
export function generateUpdateForm(domain, id, item) {
    let formContainer = document.getElementById("formContainer");
    formContainer.innerHTML = "";  // Clear previous content

    if (domain === "users") {
        formContainer.innerHTML = `
            <h2>Edit User</h2>
            <input type="text" id="user_name" value="${item.name}" required />
            <input type="email" id="user_email" value="${item.email}" required />
            <button id="updateUserBtn">Update User</button>
        `;
        document.getElementById("updateUserBtn").addEventListener("click", function () {
            submitUpdateUser(id);  // Call the function to submit the update
        });

    } else if (domain === "artists") {
        formContainer.innerHTML = `
            <h2>Edit Artist</h2>
            <input type="text" id="artist_name" value="${item.name}" required />
            <input type="text" id="genre" value="${item.genre}" required />
            <input type="text" id="country" value="${item.country}" required />
            <button id="updateArtistBtn">Update Artist</button>
        `;
        document.getElementById("updateArtistBtn").addEventListener("click", function () {
            submitUpdateArtist(id);  // Call the function to submit the update
        });

    } else if (domain === "performances") {
        formContainer.innerHTML = `
            <h2>Edit Performance</h2>
            <input type="number" id="artist_id" value="${item.artist_id}" required />
            <input type="number" id="stage_id" value="${item.stage_id}" required />
            <input type="datetime-local" id="start_time" value="${item.start_time}" required />
            <button id="updatePerformanceBtn">Update Performance</button>
        `;
        document.getElementById("updatePerformanceBtn").addEventListener("click", function () {
            submitUpdatePerformance(id);  // Call the function to submit the update
        });

    } else if (domain === "stages") {
        formContainer.innerHTML = `
            <h2>Edit Stage</h2>
            <input type="text" id="name" value="${item.name}" required />
            <input type="text" id="location" value="${item.location}" required />
            <button id="updateStageBtn">Update Stage</button>
        `;
        document.getElementById("updateStageBtn").addEventListener("click", function () {
            submitUpdateStage(id);  // Call the function to submit the update
        });
    } else if (domain === "tickets") {
        formContainer.innerHTML = `
        <h2>Edit Ticket</h2>
        <input type="number" id="userid" value="${item.user.userid}" required />
        <input type="number" id="performance_id" value="${item.performance.performance_id}" required />
        <input type="number" id="price" value="${item.price}" required />
        <input type="text" id="type" value="${item.ticket_type}" required />
        <button id="updateTicketBtn">Update Ticket</button>
    `;
        document.getElementById("updateTicketBtn").addEventListener("click", function () {
            submitUpdateTicket(id);  // Call the function to submit the update
        });
    }
}
