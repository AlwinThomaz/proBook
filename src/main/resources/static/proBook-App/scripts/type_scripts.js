"use strict"



function createTypes() {
    let typeName = document.getElementById('createType').value;
    const data = {
        "name": typeName
    }
    axios.post('http://localhost:8080/type/createType', data)
    .then((response) => {
        console.log(response);
    }).catch(error => {
        console.log(error);
    })

}

function createTypesTest() {
    debugger
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

function populateSelect() {

    axios.get('http://localhost:8080/type/getAllTypes'
    ).then((response) => {
        response.data.forEach(addToScreen);
        console.log(response);
    }).catch(error => {
        console.log(error);

    });

}

function addToScreen(item, index) {
    let option = document.createElement("option");
    let select = document.getElementById("typeList");
    option.innerText = (item.name + " ");
    select.appendChild(option);
}


