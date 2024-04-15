let selectedFile;

function getFile() {
    const fileInput = document.getElementById("fileInput");
    selectedFile = fileInput.files[0];
    const reader = new FileReader();

    reader.onload = function (event) {
        const content = event.target.result;
        document.getElementById("fileContent").value = content;
    };

    reader.readAsText(selectedFile);
}

function saveFile() {
    const fileName = selectedFile.name;
    const fileContent = document.getElementById("fileContent").value;

    fetch(`/files/${fileName}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: fileContent
    })
        .then(response => {
            if (response.ok) {
                alert('File saved successfully!');
            } else {
                alert('Error saving file!');
            }
        })
        .catch(error => console.error('Error:', error));
}

function deleteFile() {
    const fileName = selectedFile.name;
    fetch(`/files/${fileName}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                alert('File deleted successfully!');
            } else {
                alert('Error deleting file!');
            }
        })
        .catch(error => console.error('Error:', error));
}

function appendToFile() {
    const fileName = selectedFile.name;
    const fileContent = document.getElementById("fileContent").value;

    fetch(`/files/${fileName}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: fileContent
    })
        .then(response => {
            if (response.ok) {
                alert('Data appended to file successfully!');
            } else {
                alert('Error appending data to file!');
            }
        })
        .catch(error => console.error('Error:', error));
}


let targetFile;

function selectTargetFile(callback) {
    const targetFileInput = document.getElementById("targetFileInput");
    targetFileInput.value = null;
    targetFileInput.click();

    targetFileInput.addEventListener('change', function() {
        targetFile = targetFileInput.files[0];
        if (callback && typeof callback === 'function') {
            callback();
        }
    });
}

function copyFile() {
    if (!selectedFile) {
        alert('Please select source file.');
        return;
    }

    if (!targetFile) {
        alert('Please select target file.');
        selectTargetFile(sendCopyRequest);
        return;
    }

    sendCopyRequest();
}

function sendCopyRequest() {
    fetch(`/files/copy?sourceFileName=${selectedFile.name}&targetFileName=${targetFile.name}`, {
        method: 'POST',
    })
        .then(response => {
            if (response.ok) {
                alert('File copied successfully!');
            } else {
                alert('Error copying file!');
            }
        })
        .catch(error => console.error('Error:', error));
}

async function moveFile() {
    const targetDirectory = document.getElementById('targetDirectory').value.trim();
    if (!selectedFile || !targetDirectory) {
        alert('Please select a file and specify the target directory.');
        return;
    }

    const formData = new FormData();
    formData.append('file', selectedFile);
    formData.append('targetDirectory', targetDirectory);

    try {
        const response = await fetch('/files/move', {
            method: 'POST',
            body: formData
        });
        if (response.ok) {
            alert('File moved successfully!');
        } else {
            alert('Error moving file!');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error moving file!');
    }
}




// function selectTargetFile() {
//     const targetFileInput = document.getElementById("targetFileInput");
//     targetFileInput.click(); // Программно вызываем клик для элемента input type="file"
//     targetFileInput.addEventListener('change', function() {
//         targetFile = targetFileInput.files[0];
//     });
// }
//
// function copyFile() {
//     if (!selectedFile) {
//         alert('Please select source file.');
//         return;
//     }
//
//     if (!targetFile) {
//         alert('Please select target file.');
//         selectTargetFile();
//     }
//
//
//     fetch(`/files/copy?sourceFileName=${selectedFile.name}&targetFileName=${targetFile.name}`, {
//         method: 'POST',
//     })
//         .then(response => {
//             if (response.ok) {
//                 alert('File copied successfully!');
//             } else {
//                 alert('Error copying file!');
//             }
//         })
//         .catch(error => console.error('Error:', error));
// }