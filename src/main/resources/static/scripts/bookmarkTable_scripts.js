
"use strict"
const table = document.getElementById("bookmarkTable");
const tableBody = document.getElementById("bookmarkTableBody");
const tableContainer = document.getElementById("bookmarkTableContainer");

//View_Bookmark_Table

function populateBookmarkTable() {
    axios.get('http://localhost:8080/bookmark/getAllBookmarks'
    ).then((response) => {
        response.data.forEach(addToBookmarkTable);
        console.log(response);

    }).catch(error => {
        console.log(error);

    });

}

function addBookmarkRow() {
    let row = document.createElement("tr");
    row.setAttribute("id", bookmark.id);
    row.setAttribute("name", bookmark.name);
    row.setAttribute("description", bookmark.description);
    row.setAttribute("url", bookmark.url);
}

function generateTable(bookmarkList) {
    clearTableBody();
    if (!jQuery.isEmptyObject(bookmarkList)) {
        for (let bookmark of bookmarkList) {
            addRow(bookmark);
        }
    }
    else {
        tableContainer.innerHTML = "";
        let errorMessage = document.createElement("h2");
        message.innerText = "No saved bookmarks, please enter a new bookmark!";
        tableContainer.appendChild(errorMessage);
    }
};

function generateBookmarkList() {
    axios.get("http://localhost:8080/bookmark/getAllBookmarks")
        .then((response) => {
            console.log(response.status)
            generateTable(response.data);

        }).catch((error) => {
            console.error(error);
        });
};


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