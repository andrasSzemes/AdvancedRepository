//Globals
let shoppingList;

export function addShoppingListsFunctionality() {
    // let shoppingList = addWithClassToDOM("div", "shopping-list");
    //
    //
    // let addLineItemButton = addWithClassToDOM("div", "add-line-item-circle");
    // addLineItemButton.innerHTML = "<img src='/img/plus-solid.svg' class='add-line-item-icon'>";

    let message = addWithClassToDOM("h1", "message");
    message.innerHTML = "Add your first<br> shopping list!";

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