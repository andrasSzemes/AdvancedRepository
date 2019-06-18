export function addRegisterFunctionality() {
    console.log(1);
    let button = document.querySelector("#add-user-circle");

    button.addEventListener("click", () => {
        // button.classList.add("enlarge");
        let filter = document.createElement("div");
        filter.classList.add("register-filter");
        document.body.appendChild(filter);
        filter.classList.add("register-filter-fade-in");


        // let icon = document.querySelector("#add-user-icon");
        // icon.classList.add("smalling");

        let password2 = document.createElement("input");
        password2.classList.add("password2");
        password2.placeholder = "verify password";

        document.body.appendChild(password2);
        password2.classList.add("password2_move");

        let email = document.createElement("input");
        email.classList.add("email");
        email.placeholder = "email";

        document.body.appendChild(email);
        email.classList.add("email_move");

        let groupId = document.createElement("input");
        groupId.classList.add("group-id");
        groupId.placeholder = "group id";

        document.body.appendChild(groupId);
        groupId.classList.add("group-id-move");
    });

}