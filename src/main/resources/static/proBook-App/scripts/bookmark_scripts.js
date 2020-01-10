"use strict"

//Add_Bookmark
function createBookmark() {
    let bookmarkName = document.getElementById('bookmarkName').value;
    let bookmarkUrl = document.getElementById('bookmarkUrl').value;
    let bookmarkDescription = document.getElementById('bookmarkDescription').value;
    const data = {
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
    }
    axios.post('http://localhost:8080/bookmark/createBookmark', data)
        .then((response) => {

            addBookmarkToType(response.data)

        }).catch((error) => {
            console.error(error);
        }
        )
}

function addBookmarkToType(bookmark) {
    let typeId = findTypeId();
    axios.patch('http://localhost:8080/type/update/' + typeId, bookmark)
        .then((response) => { console.log(response.data) })
        .catch((error) => {
            console.error(error);
        }
        )
}

function findTypeId() {
    let e = document.getElementById('typeList');
    let typeId = e.options[e.selectedIndex].value;
    return typeId;
}



//View_Bookmark
function populateBookmarkTable() {
    axios.get('http://localhost:8080/bookmarks/getAllBookmarks'
    ).then((response) => {
        response.data.forEach(addBookmark);

        console.log(response);

    }).catch(error => {
        console.log(error);

    });

}

function addBookmark() {
    let bookmarkTable = document.getElementById('bookmarkTable')
    row = bookmarkTable.insertRow(bookmarkTable.rows.length),
        i;
    for (i = 0; i < bookmarkTable.rows[0].cells.length; i++) {
        createRow(row.insertRow(item.bookmarkName));
        createCell(row.insertCell(item.bookmarkDescription));
        createCell(row.insertCell(item.bookmarkUrl));
        
 }
}


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