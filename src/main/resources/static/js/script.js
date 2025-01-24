document.addEventListener("DOMContentLoaded", () => {
    const submitBtn = document.getElementById("submitBtn");
    const inputText = document.getElementById("inputText");
    const resultBox = document.getElementById("result");
    const transformButtons = document.querySelectorAll(".transform-option");
    const selectedTransformsList = document.getElementById("selectedTransforms");

    const baseUrl = "/api";
    const selectedTransforms = [];

    function renderSelectedTransforms() {
        selectedTransformsList.innerHTML = "";
        selectedTransforms.forEach((transform, index) => {
            const li = document.createElement("li");
            li.textContent = `${index + 1}. ${transform}`;
            li.dataset.transform = transform;

            const removeBtn = document.createElement("button");
            removeBtn.textContent = "X";
            removeBtn.classList.add("btn-remove");
            removeBtn.addEventListener("click", () => {
                removeTransform(transform);
            });

            li.appendChild(removeBtn);
            selectedTransformsList.appendChild(li);
        });
    }

    function addTransform(transform) {
        selectedTransforms.push(transform);
        renderSelectedTransforms();
    }

    function removeTransform(transform) {
        const index = selectedTransforms.indexOf(transform);
        if (index !== -1) {
            selectedTransforms.splice(index, 1);
            renderSelectedTransforms();
        }
    }

    transformButtons.forEach(button => {
        button.addEventListener("click", () => {
            const transform = button.dataset.transform;
            addTransform(transform);
        });
    });

    submitBtn.addEventListener("click", async () => {
        const text = inputText.value;
        const transforms = selectedTransforms.join(",");
        const url = `${baseUrl}/${encodeURIComponent(text)}?transforms=${transforms}`;
        try {
            const response = await fetch(url, { method: "GET" });
            const data = await response.json();
            resultBox.textContent = data.text;
        } catch (error) {
            resultBox.textContent = "Error: Unable to fetch data.";
            console.error(error);
        }
    });
});