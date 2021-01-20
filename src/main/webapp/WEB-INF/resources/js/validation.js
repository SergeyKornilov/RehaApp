

var nameValidFlag;
var passwordValidFlag;
var roleValidFlag;
var fullNameValidFlag;

var addUserBtn = document.getElementById("addUserBtn");

function checkBtn() {

    if (nameValidFlag && passwordValidFlag && roleValidFlag && fullNameValidFlag){
        addUserBtn.removeAttribute("disabled");
    } else {
        addUserBtn.setAttribute("disabled", "true");
    }

}

function checkFullName() {
    var el = document.getElementById("fullNameInput");

    var str = el.value;

    var error1 = document.getElementById("errorsFullName").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsFullName").querySelector("li:nth-child(2)");

    if (str.length < 5 || str.length > 50) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }



    if (/[^a-zA-Zа-яА-я /]+/.test(str)){
        error2.classList.add('invalid');
        error2.classList.remove('valid');
    } else{
        error2.classList.add('valid');
        error2.classList.remove('invalid');
    }

    if (error1.classList.contains("valid") && error2.classList.contains("valid")) fullNameValidFlag = true;
    else fullNameValidFlag = false;
}

function checkPassword() {
    var el = document.getElementById("passwordInput");
    var str = el.value;
    var error1 = document.getElementById("errorsPassword").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsPassword").querySelector("li:nth-child(2)");

    if (str.length < 8) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }
    if (str.length > 30) {
        error2.classList.add('invalid');
        error2.classList.remove('valid');
    } else{
        error2.classList.add('valid');
        error2.classList.remove('invalid');
    }
    if (error1.classList.contains("valid") && error2.classList.contains("valid")) passwordValidFlag = true;
    else passwordValidFlag = false;

}

function checkName() {
    var el = document.getElementById("usernameInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsUsername").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsUsername").querySelector("li:nth-child(2)");

    if (str.length < 3 || str.length > 25) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }

    if (/[^a-zA-Zа-яА-я/]+/.test(str)){
        error2.classList.add('invalid');
        error2.classList.remove('valid');
    } else{
        error2.classList.add('valid');
        error2.classList.remove('invalid');
    }

    if (error1.classList.contains("valid") && error2.classList.contains("valid")) nameValidFlag = true;
    else nameValidFlag = false;

}

function checkRole(){
    var role = document.getElementById("role");

    if (role.value === "ROLE_DOCTOR" || role.value === "ROLE_ADMIN" || role.value === "ROLE_NURSE"){
        document.getElementById("inputGroupSelect01").classList.remove("invalidInput");
        roleValidFlag = true;
    }else {
        document.getElementById("inputGroupSelect01").classList.add("invalidInput");
        roleValidFlag = false;
    }
    checkBtn();
}


document.getElementById("usernameInput").addEventListener('keyup', checkName);
document.getElementById("usernameInput").addEventListener('keyup', checkBtn);

document.getElementById("passwordInput").addEventListener('keyup', checkPassword);
document.getElementById("passwordInput").addEventListener('keyup', checkBtn);

document.getElementById("fullNameInput").addEventListener('keyup', checkFullName);
document.getElementById("fullNameInput").addEventListener('keyup', checkBtn);

