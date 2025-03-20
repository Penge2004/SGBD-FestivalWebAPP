// Handle the Delete action
import {showErrorMessage, showSuccessMessage} from "./utils";
import {loadData} from "./loadData";

export function deleteRecord(domain, item) {
    const confirmation = confirm(`Are you sure you want to delete this ${domain.slice(0, -1)}?`);

    if (confirmation) {
        fetch(`http://localhost:8080/${domain}/${item.id || item.userid || item.artist_id}`, {
            method: "DELETE",
        })
            .then(response => response.json())
            .then(() => {
                showSuccessMessage(`${domain} deleted successfully!`);
                loadData();  // Reload data after deletion
            })
            .catch(error => {
                console.error(`Error deleting ${domain}:`, error);
                showErrorMessage(`Error deleting ${domain}, please try again later.`);
            });
    }
}
