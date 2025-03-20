// formUtils.js

// Function to generate a form for creating a record
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
    } else if (domain === "artists") {
        formContainer.innerHTML = `
            <h2>Create Artist</h2>
            <input type="text" id="artist_name" placeholder="Name" required />
            <input type="text" id="genre" placeholder="Genre" required />
            <input type="text" id="country" placeholder="Country" required />
            <button id="createArtistBtn">Create Artist</button>
        `;
    } else if (domain === "performances") {
        formContainer.innerHTML = `
            <h2>Create Performance</h2>
            <input type="number" id="artist_id" placeholder="Artist ID" required />
            <input type="number" id="stage_id" placeholder="Stage ID" required />
            <input type="datetime-local" id="start_time" required />
            <button id="createPerformanceBtn">Create Performance</button>
        `;
    }
    // Add other domain forms here similarly
}
