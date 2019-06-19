//Globals
let shoppingList;

export function addShoppingListsFunctionality() {
    let shoppingList = addWithClassToDOM("div", "shopping-list");
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