"use strict"
const bookmarkList = document.getElementById("bookmarks");
const typeList = document.getElementById("bookmarks");


function createType() {
    let typeName = document.getElementById('createType').value;
    
    const data = {
        "name": typeName,
        
    }
    axios.patch('http://localhost:8080/type/update/{id}', data)
        .then (console.log(data))
        .catch ((error) => {
            console.error(error);
        }
        )}
   

function createBookmark() {
    let bookmarkName = document.getElementById('inputName').value;
    let bookmarkUrl = document.getElementById('inputUrl').value;
    let bookmarkDescription = document.getElementById('inputDescription').value;
    const data = {
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
    }
    axios.post('http://localhost:8080/bookmark/createBookmark', data)
    .then ((response) => {

        addBookmarkToType(response.data)
    
    }).catch ((error) => {
        console.error(error);
    }
)}



function addBookmarkToType() {
    let bookmarkName = document.getElementById('inputName').value;
    let bookmarkUrl = document.getElementById('inputUrl').value;
    let bookmarkDescription = document.getElementById('inputDescription').value;
    let bookmarkTypeId = document.getElementById("inputTypeId").value
    const data = {
        "id": bookmarkTypeId,
        "name": bookmarkName,
        "description": bookmarkDescription,
        "url": bookmarkUrl
        
    }
    axios.patch('http://localhost:8080/type/update/' + bookmarkTypeId, data)
    .then (console.log(data))
    .catch ((error) => {
        console.error(error);
    }
)}
