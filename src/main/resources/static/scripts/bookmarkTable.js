
"use strict"
const table = document.getElementById("bookmarkTable");
const tableBody = document.getElementById("bookmarkTableBody");
const tableContainer = document.getElementById("bookmarkTableContainer");

function addBookmarkToTable(newEntry, aRow) {

    let bookmarkName = document.createElement('td');
    bookmarkName.innerHTML = newEntry.name;
    bookmarkName.setAttribute("class", "data");
    bookmarkName.setAttribute("id", "bookmarkName"+newEntry.id);

    let bookmarkDescription = document.createElement('td');
    bookmarkDescription.innerHTML = newEntry.description;
    bookmarkDescription.setAttribute("class", "data");
    bookmarkDescription.setAttribute("id", "bookmarkDescription"+newEntry.id);

    let bookmarkUrl = document.createElement('td');
    bookmarkUrl.innerHTML = newEntry.url;
    bookmarkUrl.setAttribute("class", "data");
    bookmarkUrl.setAttribute("id", "bookmarkUrl"+newEntry.id);

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
    axios.get("/proBook/bookmark/getAllBookmarks")
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

function getBookmarksByType() {
    let typeName = findTypeName();
    axios.get("/proBook/bookmark/getBookmarksByType?name=" +typeName)
        .then((response) => {
            console.log(response.status)
            let data = (response.data);
            console.log(data);
            console.table(data);
            clearTableBody();

            for (let bookmark of data) {
                console.log(bookmark);
                let aRow = document.createElement('tr');
                tableBody.appendChild(aRow);
                addBookmarkToTable(bookmark, aRow);
            }
            clickable();
        }).catch((error) => { console.log(error.message) });

}

function findTypeName() {
    let e = document.getElementById('typeList');
    let typeName = e.selectedOptions[0].innerHTML;
    return typeName;
}


function updateBookmark(bookmarkId) {
    let bookmarkName = document.getElementById('bookmarkName'+bookmarkId).innerText;
    let bookmarkUrl = document.getElementById('bookmarkUrl'+bookmarkId).innerText;
    let bookmarkDescription = document.getElementById('bookmarkDescription'+bookmarkId).innerText;

    const data = {
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
    }
    axios.put("/proBook/bookmark/updateBookmark?id=" + bookmarkId, data)
        .then((response) => {
            console.log(response)
        }).catch((error) => { console.log(error.message) });
}



function deleteBookmark(bookmarkId) {
    console.log(bookmarkId);
    axios.delete("/proBook/bookmark/deleteBookmark/" + bookmarkId)
        .then((response) => {
            location.reload();
            console.log(response);
            readAllBookmarks()

        }).catch((error) => {
            console.error(error);
        });

}
let done = false;
function clickable() {
    if(done) {
        return;
    }
    $(document).on('click', '.edit', function () {
        $(this).parent().siblings('td.data').each(function () {
            let content = $(this)[0].innerText;
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
        alert("Bookmark Updated");
        $(this).siblings('.edit').show();
        $(this).siblings('.delete').show();
        $(this).hide();

    });


    $(document).on('click', '.delete', function () {
        deleteBookmark($(this).attr("id"));
        alert("Bookmark Deleted");
        $(this).parents('tr').remove();
    });

    done = true;
}

    function clearTableBody() {
        tableBody.innerHTML = "";
    }

