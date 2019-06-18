export function addRegisterFunctionality() {
    let button = document.querySelector("#add-user-circle");
    button.addEventListener("click", showRegisterModal);


    function showRegisterModal() {
        button.removeEventListener("click", showRegisterModal);
        button.addEventListener("click", hideRegisterModal);

        let filter = addWithClassToDOM("div", "register-filter");
        filter.classList.add("register-filter-fade-in");

        let password2 = addWithClassToDOM("input", "password2");
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
    }

    function hideRegisterModal() {
        button.addEventListener("click", showRegisterModal);

        addClassThenDelete(".register-filter", "fade-out1", 0.4);
        addClassThenDelete(".password2", "fade-out2", 0.4);
        addClassThenDelete(".email", "fade-out2", 0.4);
        addClassThenDelete(".group-id", "fade-out2", 0.4);
        addClassThenDelete(".send-register-icon", "fade-out2", 0.4);
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

const sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
}