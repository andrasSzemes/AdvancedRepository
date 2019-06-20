export function productsRequests() {

    document.querySelector("#products").addEventListener('click', showProducts);


    function showProducts() {
        let productList = addWithClassToDOM("div", "product-list");

        let title = addWithClassToDOM("h1", "title");
        title.textContent = "Product list";

        let addLineItemButton = addWithClassToDOM("div", "add-product-circle");
        addLineItemButton.innerHTML = "<img src='/img/plus-solid.svg' class='add-product-icon' id='add-product'>";

        document.querySelector("#add-product").addEventListener('click', addNewProduct);


        fetch('/products', {method: 'GET'})
            .then((response) => response.json())
            .then((responseJson) => {
                productList = responseJson;
                for (let i = 0; i < productList.length; i++) {
                    addProduct(productList[i]);
                }
            })
    }

    const addProduct = function (product) {

        const line = document.createElement('div');
        line.classList.add('product');
        line.innerHTML = '<div class="product-name">' + product.name + '</div>';

        line.addEventListener('click', makeEditable);

        const shoppingList = document.querySelector('.product-list');

        shoppingList.appendChild(line);

    };


    function addWithClassToDOM(tagType, classType) {

        const element = document.createElement(tagType);
        element.classList.add(classType);
        document.body.appendChild(element);
        return element;

    }
}

//TODO
function addNewProduct(name) {
    sendAjax("products", "POST", name, alert("Success"), alert("Fail"))
}

function makeEditable(event) {
    event.target.innerHTML = '<input value="' + event.target.innerText + '">';
    event.target.addEventListener('blur', sendUpdate);
}

function sendUpdate() {
    alert("yesss")
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

