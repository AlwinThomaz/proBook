
"use strict"
const table = document.getElementById("bookmarkTable");
const tableBody = document.getElementById("bookmarkTableBody");
const tableContainer = document.getElementById("bookmarkTableContainer");

function addBookmarkToTable(newEntry, aRow) {
    // let bookmarkId = document.createElement('td');
    // bookmarkId.innerHTML = newEntry.bookmarkId;
    let bookmarkName = document.createElement('td');
    bookmarkName.innerHTML = newEntry.name;
    let bookmarkDescription = document.createElement('td');
    bookmarkDescription.innerHTML = newEntry.description;
    let bookmarkUrl = document.createElement('td');
    bookmarkUrl.innerHTML = newEntry.url;
    // let editButton = document.createElement('td');
    // editButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick='destroy(${newEntry.poseID})' > Delete</button >`;
    let deleteButton = document.createElement('td');
    deleteButton.innerHTML = `<button type="button" id="${newEntry.id}" class="btn btn-secondary" onclick="deleteBookmark(this.id);" > Delete</button >`;

    aRow.appendChild(bookmarkName);
    aRow.appendChild(bookmarkDescription);
    aRow.appendChild(bookmarkUrl);
    //aRow.appendChild(editButton);
     aRow.appendChild(deleteButton);
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
            }
        }).catch((error) => { console.log(error.message) });

}

//constructor
function bookmarkMaker(name, description, url) {
    const bookmark = {
        bookmarkName: name.value,
        bookmarkDescription: description.value,
        bookmarkName: url.value,
    };
    return bookmark;
}

function updateBookmark() {
    let bookmark = bookmarkMaker(updateBookmarkName, updateBookmarkDescription, updateBookmarkUrl);
    console.log(bookmark)
    findBookmarkId();
    axios.put("localhost:8080/bookmark/updateBookmark?id=" + bookmarkId)
        .then((response) => {
            console.log(response)
            console.log(id)
            console.log(bookmark)
            console.log(bookmarkName)
            readAllBookmarks()

        }).catch((error) => { console.log(error.message) }).then(() => readAllBookmarks());
}

function deleteBookmark(bookmarkId) {
    console.log(bookmarkId);
    // findBookmarkId();
    axios.delete("http://localhost:8080/bookmark/deleteBookmark/" + bookmarkId)
        .then((response) => {
            console.log(response);
            readAllBookmarks()

        }).catch((error) => {
            console.error(error);
        });

}

function findBookmarkId() {
    let bookmarkId = this.id;
    return bookmarkId;
}

// function findDeleteId() {
//     let deleteId = document.getElementById('deleteId');
//     return deleteId
// }


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
}

