import {addShoppingListsFunctionality} from "/js/shopping-list.js";

export function setupLoginPage() {
    document.querySelector("#sign-in-icon").addEventListener("click", sendLoginRequest);
    document.querySelector("#sign-in-icon").addEventListener("click", addShoppingListsFunctionality);
}

function sendLoginRequest() {
    Haptics.vibrate(200);
    let json = "{";
    json += "\"username\": \"" + document.querySelector("#username").value + "\", ";
    json += "\"password\": \"" + document.querySelector("#password").value + "\"";
    json += "}";

    sendAjax("/users/auth", "POST", json, handleLoginSuccess, handleLoginReject);
}

function handleLoginSuccess() {
    document.cookie = event.target.response;
    // console.log(JSON.parse(document.cookie).token);
    addClassThenDelete("h1", "fade-out3", 1);
    addClassThenDelete("#username", "fade-out3", 1);
    addClassThenDelete("#password", "fade-out3", 1);
    addClassThenDelete("#sign-in-icon", "fade-out3", 1);
    addClassThenDelete("#add-user-circle", "fade-out3", 1);
}

function handleLoginReject() {
    document.querySelector("#username").classList.add("warningBackground");
    document.querySelector("#password").classList.add("warningBackground");
    sleep(3000).then(() => {
        document.querySelector("#username").classList.remove("warningBackground");
    document.querySelector("#password").classList.remove("warningBackground");
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