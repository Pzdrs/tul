const api = 'https://jsonplaceholder.typicode.com/users';
const header = ['id', 'username', 'name', 'email', 'phone', 'website'];

window.document.addEventListener('DOMContentLoaded', (event) => {
    const button = document.querySelector('button');
    const contentWrapper = document.querySelector('#mydata-display');
    button.addEventListener('click', (event) => {
        fetch(api).then((response) => {
            try {
                return response.json();
            } catch (error) {
                alert('Data nejsou ve správném formátu')
            }
        }).then((data) => {
            if (data.length > 0) displayData(data, contentWrapper);
            else alert('No data found')
        }).catch((error) => {
            alert('Nefunguje API');
        });
    });
});

function displayData(data, wrapper) {
    wrapper.innerHTML = '';
    let table = document.createElement('table');
    table.classList.add('table', 'table-striped', 'table-hover');

    let thead = document.createElement('thead');

    let headerRow = thead.insertRow(0);
    header.forEach((item, _) => {
        let th = document.createElement('th');
        th.innerHTML = item;
        headerRow.appendChild(th);
    });
    let tbody = document.createElement('tbody');
    data.forEach((item, _) => {
        let row = table.insertRow();
        header.forEach((headerItem, _) => {
            let cell = row.insertCell();
            cell.innerHTML = item[headerItem];
        });
        tbody.appendChild(row);
    });

    table.append(thead, tbody);

    wrapper.appendChild(table);
}
