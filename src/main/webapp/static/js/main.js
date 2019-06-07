//Globals
let backend_URL = "";
let shoppingList;

function setup() {
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
    line.innerHTML = "<div class='quantity'>" + lineItem.quantity + "</div>" + lineItem.product.name;

    const shoppingList = document.querySelector('#shopping-list');
    shoppingList.appendChild(line);
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
setup();