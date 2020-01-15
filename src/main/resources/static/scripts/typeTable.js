
"use strict"
const table = document.getElementById("typeTable");
const tableBody = document.getElementById("typeTableBody");
const tableContainer = document.getElementById("typeTableContainer");

function addTypeToTable(newEntry, aRow) {

    let typeName = document.createElement('td');
    typeName.innerHTML = newEntry.name;
    typeName.setAttribute("class", "data");
    typeName.setAttribute("id", "typeName");

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

    aRow.appendChild(typeName);
    aRow.appendChild(buttonCell);
}


function readAllTypes() {
    axios.get("http://localhost:8080/type/getAllTypes")
        .then((response) => {
            console.log(response.status)
            let data = (response.data);
            console.log(data);
            console.table(data);

            for (let type of data) {
                console.log(type);
                let aRow = document.createElement('tr');
                tableBody.appendChild(aRow);
                addTypeToTable(type, aRow);
                clickable();
            }
        }).catch((error) => { console.log(error.message) });

}

function updateType(typeId) {
    let typeName = document.getElementById('typeName').innerText;

    const data = {
        "name": typeName
    }
    axios.put("http://localhost:8080/type/updateType?id=" + typeId, data)
        .then((response) => {
            console.log(response)
        }).catch((error) => { console.log(error.message) });
}



function deleteType(typeId) {
    console.log(typeId);
    axios.delete("http://localhost:8080/type/deleteType/" + typeId)
        .then((response) => {
            location.reload();
            console.log(response);
            readAllTypes()

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
        updateType($(this).attr("id"));
        $(this).siblings('.edit').show();
        $(this).siblings('.delete').show();
        $(this).hide();

    });


    $(document).on('click', '.delete', function () {
        deleteType($(this).attr("id"));
        $(this).parents('tr').remove();
    });

    done = true;
}

    function clearTableBody() {
        tableBody.innerHTML = "";
    }




