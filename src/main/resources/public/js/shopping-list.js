//Globals
let shoppingList;

export function addShoppingListsFunctionality() {
    // let shoppingList = addWithClassToDOM("div", "shopping-list");

    addGroupChooserModal();

    loadLastListActGroup();
}

const addLineItem = function (lineItem, shoppingList) {
    const line = document.createElement('div');
    line.classList.add('line-item');
    line.innerHTML = "<div class='dot'></div><div class='quantity'>" + lineItem.quantity + "</div>" + lineItem.product.name;

    if (lineItem.isArchived) {
        line.querySelector(".dot").classList.add("slash");
    }

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
            let shoppingListElement = addWithClassToDOM("div", "shopping-list");
            let shoppingListJSON = (JSON.parse(event.target.response)).shoppingList;

            for (let i = 0; i < shoppingListJSON.lineItems.length; i++) {
                addLineItem(shoppingListJSON.lineItems[i], shoppingListElement);
            }
        },
        () => {
            let message = addWithClassToDOM("h1", "message1");
            message.innerHTML = "Add your first<br> shopping list!";

            let addShoppingListButton = addWithClassToDOM("div", "add-circle");
            addShoppingListButton.innerHTML = "<img src='/img/list-alt-regular.svg' class='add-shopping-list-icon'>";
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