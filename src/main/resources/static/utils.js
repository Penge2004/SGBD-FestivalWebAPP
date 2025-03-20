// Show an error message to the user
export function showErrorMessage(message) {
    let errorContainer = document.getElementById("formContainer");
    errorContainer.innerHTML = `<p class="error">${message}</p>`;
}

// Show a success message to the user
export function showSuccessMessage(message) {
    let successContainer = document.getElementById("formContainer");
    successContainer.innerHTML = `<p class="success">${message}</p>`;
}
