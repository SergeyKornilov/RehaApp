
var nameValidFlag;
var doseValidFlag = true;
var dateValidFlag;

var addPrescribingBtn = document.getElementById("addPrescribingBtn");


function checkBtn() {

    if (nameValidFlag && doseValidFlag){
        addPrescribingBtn.removeAttribute("disabled");
        addPrescribingBtn.classList.add("active");
    } else {
        addPrescribingBtn.setAttribute("disabled", "true");
        addPrescribingBtn.classList.remove("active");
    }

}

function checkDate() {

}

function checkName() {
    var el = document.getElementById("prescribingName");

    var str = el.value;

    var error1 = document.getElementById("errorsUsername").querySelector("li:nth-child(1)");


    if (str.length < 1 || str.length > 25) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }


    if (error1.classList.contains("valid")) nameValidFlag = true;
    else nameValidFlag = false;

}

function checkDose(){
    var el = document.getElementById("prescribingDose");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsDose").querySelector("li:nth-child(1)");

    if (str.length < 1 || str.length > 25) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }


    if (error1.classList.contains("valid")) doseValidFlag = true;
    else doseValidFlag = false;
}





document.getElementById("prescribingName").addEventListener('keyup', checkName);
document.getElementById("prescribingName").addEventListener('keyup', checkBtn);

document.getElementById("prescribingDose").addEventListener('keyup', checkDose);
document.getElementById("prescribingDose").addEventListener('keyup', checkBtn);

