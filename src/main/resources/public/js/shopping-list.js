//Globals
let shoppingList;

export function addShoppingListsFunctionality() {
    // let shoppingList = addWithClassToDOM("div", "shopping-list");
    //
    //
    // let addLineItemButton = addWithClassToDOM("div", "add-line-item-circle");
    // addLineItemButton.innerHTML = "<img src='/img/plus-solid.svg' class='add-line-item-icon'>";

    addGroupChooserModal();

    document.body.dataset.actualGroup = -1;
    loadLastListActGroup();
}

const addLineItem = function (lineItem) {
    const line = document.createElement('div');
    line.classList.add('line-item');
    line.innerHTML = "<div class='dot'></div><div class='quantity'>" + lineItem.quantity + "</div>" + lineItem.product.name;

    if (lineItem.isArchived) {
        line.querySelector(".dot").classList.add("slash");
    }

    const shoppingList = document.querySelector('#shopping-list');
    shoppingList.appendChild(line);

    line.addEventListener("click", () => {
        if (line.querySelector(".dot").classList.contains("slash")) {
        line.querySelector(".dot").classList.remove("slash");
    }
else {
        line.querySelector(".dot").classList.add("slash");
    }
    fetch(backend_URL + '/line-item/check',
        {method: 'PUT',
            body: JSON.stringify(lineItem),
            headers: {'Content-Type': 'application/json'}})
})
};

window.addEventListener('load-page', function () {
    fetch(backend_URL + '/shopping-list/latest/-1', {method: 'GET'})
        .then((response) => response.json())
.then((responseJson) => {
        shoppingList = responseJson;

    for (let i = 0; i < shoppingList.lineItems.length; i++) {
        addLineItem(shoppingList.lineItems[i]);
    }
})
})

function addWithClassToDOM(tagType, classType) {
    const element = document.createElement(tagType);
    element.classList.add(classType);
    document.body.appendChild(element);
    return element;
}

function addGroupChooserModal() {
    let groupsList = addWithClassToDOM("div", "groups-list");
    for (let group of JSON.parse(document.cookie).groups) {
        let groupDiv = document.createElement("div");
        groupDiv.classList.add("group-name");
        groupDiv.innerText = group.name;
        groupDiv.dataset.id = group.id;
        groupsList.appendChild(groupDiv);
    }


    let groupsIcon = addWithClassToDOM("img", "groups-icon");
    groupsIcon.src = "/img/users-solid.svg";
    groupsIcon.addEventListener('click', () => {
        if (groupsList.classList.contains("groups-list-open")) {groupsList.classList.remove("groups-list-open")}
    else {groupsList.classList.add("groups-list-open")};
    })
}

function loadLastListActGroup() {
    sendAjax("/shopping-lists/latest-by-group/" + document.body.dataset.actualGroup,
        "GET",
        "",
        () => {
            let message = addWithClassToDOM("h1", "message");
            message.innerHTML = "This is the list!";
            //event.target.response
        },
        () => {
            let message = addWithClassToDOM("h1", "message");
            message.innerHTML = "Add your first<br> shopping list!";
        })
}



/**
 * Sends an AJAX request.
 * @param endpoint The endpoint of the request, for example "/login"
 * @param method Request method. Can be "GET", "POST", "PUT", "DELETE",..
 * @param params Parameters in URL format, like "param1=3&param3=asdf" or json
 */
function sendAjax(endpoint, method, params, onSuccess, onFail) {
    const req = new XMLHttpRequest();
    req.addEventListener("load", function (event) {
        if (req.status == 200) { onSuccess(); }
        else { onFail(); }
    });
    req.addEventListener("error", function (err) {
        console.log("Request failed for " + endpoint + " error: " + err);
    });
    req.open(method, endpoint);
    if (method === "POST" || method === "PUT") {
        req.setRequestHeader("Content-type", "application/json");
    }
    req.setRequestHeader("Authorization", "Bearer " + (JSON.parse(document.cookie)).token);
    req.send(params);
}