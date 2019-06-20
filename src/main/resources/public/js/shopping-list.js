import {productsRequests} from "/js/products.js";
//Globals
let shoppingList;

export function addShoppingListsFunctionality() {
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
        } else {
            line.querySelector(".dot").classList.add("slash");
        }
        fetch('/line-item/check',
            {
                method: 'PUT',
                body: JSON.stringify(lineItem),
                headers: {'Content-Type': 'application/json'}
            })
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
        if (groupsList.classList.contains("groups-list-open")) {
            groupsList.classList.remove("groups-list-open")
        } else {
            groupsList.classList.add("groups-list-open")
        }
        ;
    })
}

function loadLastListActGroup() {
    sendAjax("/shopping-lists/latest-by-group/" + document.body.dataset.actualGroup,
        "GET",
        "",
        () => {
            let shoppingListElement = addWithClassToDOM("div", "shopping-list");
            let shoppingListJSON = (JSON.parse(event.target.response)).shoppingList;
            shoppingListElement.dataset.id = shoppingListJSON.id;

            for (let i = 0; i < shoppingListJSON.lineItems.length; i++) {
                addLineItem(shoppingListJSON.lineItems[i], shoppingListElement);
            }
            let addProductButton = addWithClassToDOM("div", "add-circle");
            addProductButton.innerHTML = "<img src='/img/plus-solid.svg' class='add-product-icon'>";
            productsRequests();
        },
        () => {
            let message = addWithClassToDOM("h1", "message1");
            message.innerHTML = "Add your first<br> shopping list!";

            let addShoppingListButton = addWithClassToDOM("div", "add-circle");
            addShoppingListButton.innerHTML = "<img src='/img/list-alt-regular.svg' class='add-shopping-list-icon'>";

            addShoppingListButton.addEventListener("click", () => {
                let usedId = (JSON.parse(document.cookie)).userId;
                let groupId = document.body.dataset.actualGroup;

                let json = "{\"memberId\": \"" + usedId + "\", " +
                    "\"groupId\": \"" + groupId + "\"}";

                sendAjax("/shopping-lists", "POST", json, addNewListSuccess, addNewListFail)
            })
        })
}

function addNewListSuccess() {
    let shoppingListElement = addWithClassToDOM("div", "shopping-list");
    let listId = (JSON.parse(event.target.response)).listId;
    shoppingListElement.dataset.id = listId;

    //DELETE message1, add-circle
    addClassThenDelete(".message1", "fade-out3", 1);
    addClassThenDelete(".add-circle", "fade-out3", 1);

    let addProductButton = addWithClassToDOM("div", "add-circle");
    addProductButton.innerHTML = "<img src='/img/plus-solid.svg' class='add-product-icon' class='add-product'>";
}

function addNewListFail() {
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
        if (req.status == 200) {
            onSuccess();
        } else {
            onFail();
        }
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

function addClassThenDelete(selector, classType, timeOut) {
    let element = document.querySelector(selector);
    element.classList.add(classType);
    sleep(timeOut * 1000).then(() => element.parentNode.removeChild(element));
}

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}