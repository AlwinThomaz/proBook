"use strict"



function createTypes() {
    let typeName = document.getElementById('createType').value;
    const data = {
        "name": typeName
    }
    axios.post('/proBook/type/createType', data)
    .then((response) => {
        console.log(response);
    }).catch(error => {
        console.log(error);
    })

}

function populateSelect() {

    axios.get('/proBook/type/getAllTypes'
    ).then((response) => {
        response.data.forEach(addToDropDown);
        console.log(response);
    }).catch(error => {
        console.log(error);

    });

}

function addToDropDown(item, index) {
    let option = document.createElement('option');
    let select = document.getElementById('typeList');
    option.value = item.id;
    option.innerText = (item.name + " ");
    select.appendChild(option);
}


