
"use strict"
const table = document.getElementById("bookmarkTable");
const tableBody = document.getElementById("bookmarkTableBody");
const tableContainer = document.getElementById("bookmarkTableContainer");

function addBookmarkToTable(newEntry, aRow) {

    let bookmarkName = document.createElement('td');
    bookmarkName.innerHTML = newEntry.name;
    bookmarkName.setAttribute("class", "data");
    bookmarkName.setAttribute("id", "bookmarkName");

    let bookmarkDescription = document.createElement('td');
    bookmarkDescription.innerHTML = newEntry.description;
    bookmarkDescription.setAttribute("class", "data");
    bookmarkDescription.setAttribute("id", "bookmarkDescription");

    let bookmarkUrl = document.createElement('td');
    bookmarkUrl.innerHTML = newEntry.url;
    bookmarkUrl.setAttribute("class", "data");
    bookmarkUrl.setAttribute("id", "bookmarkUrl");

    let buttonCell = document.createElement('td');

    let editButton = document.createElement('button');
    editButton.setAttribute("type", "button");
    editButton.setAttribute("class", "edit");
    editButton.setAttribute("data-toggle", "modal");
    editButton.setAttribute("data-target", "#exampleModal");
    editButton.innerText = "Edit";
    buttonCell.appendChild(editButton);

    let saveButton = document.createElement('button');
    saveButton.setAttribute("type", "button");
    saveButton.setAttribute("class", "save");
    saveButton.setAttribute("data-toggle", "modal");
    saveButton.setAttribute("data-target", "#exampleModal");
    saveButton.innerText = "Save";
    saveButton.id = newEntry.id;
    console.log(saveButton.id);
    buttonCell.appendChild(saveButton);

    let deleteButton = document.createElement('button');
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("class", "delete");
    deleteButton.id = newEntry.id;

    deleteButton.innerText = "Delete";
    buttonCell.appendChild(deleteButton);


    aRow.appendChild(bookmarkName);
    aRow.appendChild(bookmarkDescription);
    aRow.appendChild(bookmarkUrl);
    aRow.appendChild(buttonCell);
}


function readAllBookmarks() {
    axios.get("http://localhost:8080/bookmark/getAllBookmarks")
        .then((response) => {
            console.log(response.status)
            let data = (response.data);
            console.log(data);
            console.table(data);

            for (let bookmark of data) {
                console.log(bookmark);
                let aRow = document.createElement('tr');
                tableBody.appendChild(aRow);
                addBookmarkToTable(bookmark, aRow);
                clickable();
            }
        }).catch((error) => { console.log(error.message) });

}


function updateBookmark(bookmarkId) {
    let bookmarkName = document.getElementById('bookmarkName').innerText;
    let bookmarkUrl = document.getElementById('bookmarkUrl').innerText;
    let bookmarkDescription = document.getElementById('bookmarkDescription').innerText;

    const data = {
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
    }
    axios.put("http://localhost:8080/bookmark/updateBookmark?id=" + bookmarkId, data)
        .then((response) => {
            console.log(response)
        }).catch((error) => { console.log(error.message) });
}



function deleteBookmark(bookmarkId) {
    console.log(bookmarkId);
    axios.delete("http://localhost:8080/bookmark/deleteBookmark/" + bookmarkId)
        .then((response) => {
            location.reload();
            console.log(response);
            readAllBookmarks()

        }).catch((error) => {
            console.error(error);
        });

}

function clickable() {
    $(document).on('click', '.edit', function () {
        $(this).parent().siblings('td.data').each(function () {
            let content = $(this).html();
            $(this).html('<input value="' + content + '" />');
        });

        $(this).siblings('.save').show();
        $(this).siblings('.delete').hide();
        $(this).hide();
    });

    $(document).on('click', '.save', function () {
        $('input').each(function () {
            let content = $(this).val();
            $(this).html(content);
            $(this).contents().unwrap();
        });
        console.log($(this).attr("id"));
        updateBookmark($(this).attr("id"));
        $(this).siblings('.edit').show();
        $(this).siblings('.delete').show();
        $(this).hide();

    });


    $(document).on('click', '.delete', function () {
        deleteBookmark($(this).attr("id"));
        $(this).parents('tr').remove();
    });

}

