function alwin() {
    let typeName = document.getElementById('createType').value;
    const data = {
        "name": typeName
    }
    axios.post('http://localhost:8080/type/createType', data)
        .then(console.log(data))
        .catch((error) => {
            console.error(error);
        })
}


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



function addBookmarkToType() {


    console.log("££££££££££££ " + bookmarkTypeId)
    const data = {

        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl

    }
    axios.patch('http://localhost:8080/type/update/' + bookmarkTypeId, data)
        .then(console.log(data))
        .catch((error) => {
            console.error(error);
        }
        )
}
