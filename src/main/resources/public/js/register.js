export function addRegisterFunctionality() {
    console.log(1);
    let button = document.querySelector("#add-user-circle");

    button.addEventListener("click", showRegisterModal);


    function showRegisterModal() {
        let filter = addWithClassToDOM("div", "register-filter");
        filter.classList.add("register-filter-fade-in");

        let password2 = addWithClassToDOM("input", "password2");
        password2.placeholder = "verify password";
        password2.classList.add("password2_move");

        let email = addWithClassToDOM("input", "email");
        email.placeholder = "email";
        email.classList.add("email_move");

        let groupId = addWithClassToDOM("input", "group-id");
        groupId.placeholder = "group id";
        groupId.classList.add("group-id-move");

        let registerIcon = document.createElement("img");
        registerIcon.src = "/img/sign-in-alt-solid.svg";
        registerIcon.setAttribute("id", "send-register-icon");
        document.body.appendChild(registerIcon);
    }
}

function addWithClassToDOM(tagType, classType) {
    const element = document.createElement(tagType);
    element.classList.add(classType);
    document.body.appendChild(element);
    return element;
}