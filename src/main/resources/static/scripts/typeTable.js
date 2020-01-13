
"use strict"
const table = document.getElementById("typeTable");
const tableBody = document.getElementById("typeTableBody");
const tableContainer = document.getElementById("typeTableContainer");

function addTypeToTable(newEntry, aRow) {
    // let bookmarkId = document.createElement('td');
    // bookmarkId.innerHTML = newEntry.bookmarkId;
    let typeName = document.createElement('td');
    typeName.innerHTML = newEntry.name;
    // let editButton = document.createElement('td');
    // deleteButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick='destroy(${newEntry.poseID})' > Delete</button >`;
    // let deleteButton = document.createElement('td');
    // deleteButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick='destroy(${newEntry.poseID})' > Delete</button >`;
    

    // aRow.appendChild(bookmarkId);
    aRow.appendChild(typeName);
    // aRow.appendChild(editButton);
    // aRow.appendChild(deleteButton);
}


const readAllTypes = () => {
    // removes any existing tables
    // const tableContainer = document.getElementById('table');
    // if (tableContainer.rows.length > 1) {
    //     const tableBody = tableBody.rows.length;
    //     for (let i = tableBody; i > 1; i--) {
    //         tableContainer.deleteRow(i - 1);
    //     }
    // }
    axios.get("http://localhost:8080/type/getAllTypes")
        .then((response) => {
            console.log(response.status)
            let data = (response.data);
            console.log(data);
            console.table(data);

            //const tableContainer = document.getElementById('table');

            // creating table rows and adding data into the rows
            for (let type of data) {
                console.log(type);
                let aRow = document.createElement('tr');
                tableBody.appendChild(aRow);
                addTypeToTable(type, aRow);
            }
            // for (let i = 0; i < data.length; i++) {
            //     console.log(i + "run");
            //     let aRow = document.createElement('tr');
            //     tableBody.appendChild(aRow);
            //     addBookmarkToTable(data[i], aRow);
            // }
        }).catch((error) => { console.log(error.message) });

}

// function getBookmarkList() {
//     axios.get("http://localhost:8080/bookmark/getAllBookmarks")
//         .then((response) => {
//             console.log(response.status)
//             generateTable(response.data);

//         }).catch((error) => {
//             console.error(error);
//         });
// };


function clickable() {
    $(document).on('click', '.edit', function () {
        $(this).parent().siblings('td.data').each(function () {
            var content = $(this).html();
            $(this).html('<input value="' + content + '" />');
        });

        $(this).siblings('.save').show();
        $(this).siblings('.delete').hide();
        $(this).hide();
    });

    $(document).on('click', '.save', function () {

        $('input').each(function () {
            var content = $(this).val();
            $(this).html(content);
            $(this).contents().unwrap();
        });
        $(this).siblings('.edit').show();
        $(this).siblings('.delete').show();
        $(this).hide();

    });


    $(document).on('click', '.delete', function () {
        $(this).parents('tr').remove();
    });

    $('.add').click(function () {
        $(this).parents('table').append('<tr><td class="data"></td><td class="data"></td><td class="data"></td><td><button class="save">Save</button><button class="edit">Edit</button> <button class="delete">Delete</button></td></tr>');
    });
}

// readAll();