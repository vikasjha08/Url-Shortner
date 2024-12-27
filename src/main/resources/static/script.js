document.getElementById('shortenButton').addEventListener('click', async () => {
    const urlInput = document.getElementById('urlInput').value;
    const resultDiv = document.getElementById('result');

    if (!urlInput) {
        resultDiv.textContent = 'Please enter a URL.';
        return;
    }

    try {
        // Validate URL
        new URL(urlInput);

        // Send POST request
        const response = await fetch('http://localhost:8080/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ longUrl: urlInput }),
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();
        resultDiv.innerHTML = `Shortened URL: <a href="http://localhost:8080/${data.shortUrl}" target="_blank">http://localhost:8080/${data.shortUrl}</a>`;
    } catch (error) {
        resultDiv.textContent = `Error: ${error.message}`;
    }
});
