document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('registrationButton').addEventListener('click', function(event) {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const role = document.getElementById('role').value;

        const data = {
            name: username,
            password: password,
            roles: role
        };

        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/new', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log('Успешно отправлено');
                window.location.href = 'http://localhost:8080';
            }
        };
        xhr.send(JSON.stringify(data));
    });
});
