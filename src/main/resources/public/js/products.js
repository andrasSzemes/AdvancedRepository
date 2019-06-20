import {swipe} from "/js/swipe.js";

export function productsRequests() {

    document.querySelector(".add-product-icon").addEventListener('click', showProducts);


    function showProducts() {
        let productList = addWithClassToDOM("div", "product-list");

        let title = addWithClassToDOM("h1", "products-title");
        title.textContent = "Product List";
        //TODO add menu icon

        //let addProductElement = addWithClassToDOM("div", "add-product-circle");
        //addProductElement.innerHTML = "<img src='/img/plus-solid.svg' class='add-product-icon' id='add-product'>";

        //document.querySelector("#add-product").addEventListener('click', addNewProduct);


        fetch('/products', {method: 'GET'})
            .then((response) => response.json())
            .then((responseJson) => {

                let handler = document.createElement("div");
                handler.classList.add("product-list-handler");
                handler.innerHTML = "<img src='/img/grip-lines-solid.svg' class='product-list-handler-icon'>";
                productList.appendChild(handler);
                swipe(".product-list-handler");
                console.log(responseJson);
                let productListJSON = responseJson;
                for (let i = 0; i < productListJSON.length; i++) {
                    addProduct(productListJSON[i]);
                }
            })
    }

    const addProduct = function (product) {

        const line = document.createElement('div');
        line.classList.add('product');
        line.innerHTML = '<div class="product-name">' + product.name + '</div>';

        //adds product from product list to shopping list
        let list = document.querySelector(".shopping-list");
        line.addEventListener('click', () => {
            addToShoppingList(product.name, "", list)
        });

        const productList = document.querySelector('.product-list');

        productList.appendChild(line);

    };


    function addWithClassToDOM(tagType, classType) {

        const element = document.createElement(tagType);
        element.classList.add(classType);
        document.body.appendChild(element);
        return element;

    }
}


//TODO
/*function addNewProduct(name) {
    sendAjax("products", "POST", name, alert("Success"), alert("Fail"))
}*/

const addToShoppingList = function (productName, quantity, shoppingList) {
    const line = document.createElement('div');
    line.classList.add('line-item');
    line.innerHTML = "<div class='dot'></div><div class='quantity'>" + quantity + "</div>" + productName;


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

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}

function addClassThenDelete(selector, classType, timeOut) {
    let element = document.querySelector(selector);
    element.classList.add(classType);
    // sleep(timeOut*1000).then(() => element.parentNode.removeChild(element));
}

