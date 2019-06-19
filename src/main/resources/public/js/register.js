export function addRegisterFunctionality() {
    let button = document.querySelector("#add-user-circle");
    button.addEventListener("click", showRegisterModal);


    function showRegisterModal() {
        button.removeEventListener("click", showRegisterModal);
        button.addEventListener("click", hideRegisterModal);

        let filter = addWithClassToDOM("div", "register-filter");
        filter.classList.add("register-filter-fade-in");

        let password2 = addWithClassToDOM("input", "password2");
        password2.type = "password";
        password2.placeholder = "verify password";
        password2.classList.add("password2-move");

        let email = addWithClassToDOM("input", "email");
        email.placeholder = "email";
        email.classList.add("email-move");

        let groupId = addWithClassToDOM("input", "group-id");
        groupId.placeholder = "group id";
        groupId.classList.add("group-id-move");

        let registerIcon = addWithClassToDOM("img", "send-register-icon");
        registerIcon.src = "/img/sign-in-alt-solid.svg";
        registerIcon.classList.add("send-register-icon-fade-in");
        registerIcon.addEventListener("click", sendRegisterRequest)
    }

    function hideRegisterModal() {
        button.addEventListener("click", showRegisterModal);

        addClassThenDelete(".register-filter", "fade-out1", 0.4);
        addClassThenDelete(".password2", "fade-out2", 0.4);
        addClassThenDelete(".email", "fade-out2", 0.4);
        addClassThenDelete(".group-id", "fade-out2", 0.4);
        addClassThenDelete(".send-register-icon", "fade-out2", 0.4);
    }

    function sendRegisterRequest() {
        let json = "{";

        let groupKey = document.querySelector(".group-id").value;
        if (groupKey != "") { json += "\"groupKey\": \"" + groupKey + "\", "}
        json += "\"email\": \"" + document.querySelector(".email").value + "\", ";
        json += "\"username\": \"" + document.querySelector("#username").value + "\", ";
        json += "\"password\": \"" + document.querySelector("#password").value + "\"";
        json += "}";

        sendAjax("/users", "POST", json, hideRegisterModal, changeFilterToRed);
    }
}

function addWithClassToDOM(tagType, classType) {
    const element = document.createElement(tagType);
    element.classList.add(classType);
    document.body.appendChild(element);
    return element;
}

function addClassThenDelete(selector, classType, timeOut) {
    let element = document.querySelector(selector);
    element.classList.add(classType);
    sleep(timeOut*1000).then(() => element.parentNode.removeChild(element));
}

function changeFilterToRed() {
    document.querySelector(".register-filter").classList.add("register-filter-red");
}

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
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
        //Todo: when failed, the response is an error message.. when success it's "". It's ok?
        if (event.target.response === "") { onSuccess(); }
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