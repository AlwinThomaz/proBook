"use strict"


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
        .then((response) => {console.log(response.data)})
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

function createBookmarkTest() {
    let bookmarkName = document.getElementById('bookmarkName').value;
    let bookmarkUrl = document.getElementById('bookmarkUrl').value;
    let bookmarkDescription = document.getElementById('bookmarkDescription').value;
    const data = {
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
    }
    axios.post('http://localhost:8080/bookmark/createBookmark', data)
        .then(console.log(data))
        .catch((error) => {
            console.error(error);
        })
}