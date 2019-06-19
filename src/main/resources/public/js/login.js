export function setupLoginPage() {
    document.querySelector("#sign-in-icon").addEventListener("click", sendLoginRequest);
}

function sendLoginRequest() {
    let json = "{";
    json += "\"username\": \"" + document.querySelector("#username").value + "\", ";
    json += "\"password\": \"" + document.querySelector("#password").value + "\"";
    json += "}";

    console.log(json);
    sendAjax("/auth/signin", "POST", json, () => {

    }, () => {
        document.querySelector("#username").classList.add("warningBackground");
        document.querySelector("#password").classList.add("warningBackground");
        sleep(3000).then(() => {
            document.querySelector("#username").classList.remove("warningBackground");
            document.querySelector("#password").classList.remove("warningBackground");
        })
    });
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