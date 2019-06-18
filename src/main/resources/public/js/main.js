import {swipe} from "/js/swipe.js";

//Globals
let backend_URL = "";
let shoppingList;

function setup() {
    const shoppingList = document.createElement("div");
    shoppingList.setAttribute("id", "shopping-list");
    document.querySelector("body").appendChild(shoppingList);

    fetch('/config.json')
        .then(response => response.json())
        .then(jsonResponse => {
            backend_URL = jsonResponse.backend_URL;
            dispatchEvent(new Event('load-page')); // custom event used for syncronized load
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


//Called functions
// setup();