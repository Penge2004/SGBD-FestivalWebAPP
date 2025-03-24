// Function to fetch data from the server
export function fetchData(domain) {
    let url = `http://localhost:8080/${domain}`;

    return fetch(url)
        .then(response => response.json())
        .catch(error => {
            throw new Error("Error fetching data: " + error);
        });
}

// Function to send a POST request to create a new record
export function submitCreate(domain, data) {

    return fetch(`http://localhost:8080/${domain}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            console.log("Received response:", response);//debug
            if (!response.ok) {
                throw new Error("Failed to create record");
            }

            return response.json();  // Extracts the JSON content from the response
        })
        .then(responseData => {
            console.log('Response from server:', responseData); //debug
            return responseData;
        })
        .catch(error => {
            console.error("Error creating record:", error);
        });
}


// Function to send a PUT request to update an existing record
export function submitUpdate(domain, id, data) {
    return fetch(`http://localhost:8080/${domain}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .catch(error => {
            throw new Error("Error updating record: " + error);
        });
}

// Function to send a DELETE request
export function deleteRecord(domain, id) {
    return fetch(`http://localhost:8080/${domain}/${id}`, {
        method: "DELETE",
    })
        .then(() => {})
        .catch(error => {
            throw new Error("Error deleting record: " + error);
        });
}