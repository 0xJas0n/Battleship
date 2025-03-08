document.querySelectorAll(".api-button").forEach(button => {
    button.addEventListener("click", function() {
        const endpoint = this.dataset.endpoint;
        const method = this.dataset.method || "POST";
        const body = this.dataset.body ? JSON.parse(this.dataset.body) : null;

        fetch(endpoint, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: body ? JSON.stringify(body) : null
        })
            .then(response => response.json().catch(() => null))
            .then(data => console.log("Response:", data))
            .catch(error => console.error("Error:", error));
    });
});
