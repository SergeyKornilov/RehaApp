var surnameValidFlag;
var nameValidFlag;
var secondnameValidFlag = true;
var diagnosisValidFlag;
var insuranceNumberValidFlag;
var attendingDoctorValidFlag;
var statusValidFlag;
var dateOfBirthdayValidFlag;

var addPatientBtn = document.getElementById("addPatientBtn");

function checkBtn() {
    console.log(addPatientBtn);
    if (surnameValidFlag && nameValidFlag && secondnameValidFlag && diagnosisValidFlag && insuranceNumberValidFlag &&
        attendingDoctorValidFlag && statusValidFlag && dateOfBirthdayValidFlag){
        addPatientBtn.removeAttribute("disabled");
    } else {
        addPatientBtn.setAttribute("disabled", "true");
    }
}


function checkSurname() {

    var el = document.getElementById("surnameInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsSurname").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsSurname").querySelector("li:nth-child(2)");

    if (str.length < 2 || str.length > 25) {
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

    if (error1.classList.contains("valid") && error2.classList.contains("valid")) surnameValidFlag = true;
    else surnameValidFlag = false;

}

function checkName() {

    var el = document.getElementById("nameInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsName").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsName").querySelector("li:nth-child(2)");

    if (str.length < 2 || str.length > 25) {
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

function checkSecondname() {

    var el = document.getElementById("secondnameInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsSecondname").querySelector("li:nth-child(1)");


    if (/[^a-zA-Zа-яА-я/]+/.test(str)){
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }

    if (error1.classList.contains("valid")) secondnameValidFlag = true;
    else secondnameValidFlag = false;

}

function checkDiagnosis(){


    var el = document.getElementById("diagnosisInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsDiagnosis").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsDiagnosis").querySelector("li:nth-child(2)");

    if (str.length < 2 || str.length > 25) {
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

    if (error1.classList.contains("valid") && error2.classList.contains("valid")) diagnosisValidFlag = true;
    else diagnosisValidFlag = false;
}

function checkInsuranceNumber(){

    var el = document.getElementById("insuranceNumberInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsInsuranceNumber").querySelector("li:nth-child(1)");

    if (str.length < 8 || str.length > 25) {
        error1.classList.add('invalid');
        error1.classList.remove('valid');
    } else{
        error1.classList.add('valid');
        error1.classList.remove('invalid');
    }


    if (error1.classList.contains("valid")) insuranceNumberValidFlag = true;
    else insuranceNumberValidFlag = false;

}

function checkStatus(){
    var role = document.getElementById("status");

    if (role.value === "Inpatient" || role.value === "Outpatient" || role.value === "Issued"){
        document.getElementById("inputGroupSelect01").classList.remove("invalidInput");
        statusValidFlag = true;
    }else {
        document.getElementById("inputGroupSelect01").classList.add("invalidInput");
        statusValidFlag = false;
    }

    checkBtn();
}

function checkDate(){
    var dateOfBirthInput = document.getElementById("inputDateOfBirth");

    if (dateOfBirthInput.value === ""){
        dateOfBirthInput.classList.add("invalidInput");
        dateOfBirthdayValidFlag = false;
        checkBtn();
    } else{
        dateOfBirthInput.classList.remove("invalidInput");
        dateOfBirthdayValidFlag = true;
        checkBtn();
    }
}

function checkAllValid() {

}

function checkDoctorsList() {


    var attendingDoctor = document.getElementsByName("attendingDoctor")[0].value;
    if (attendingDoctor === "Select attending doctor" || attendingDoctor.length === 0) {
        document.getElementsByClassName("bfh-selectbox-toggle form-control")[0].classList.add("invalidInput");
        attendingDoctorValidFlag = false;
        console.log("set invalid doctors list");
        checkBtn();
    } else {
        document.getElementsByClassName("bfh-selectbox-toggle form-control")[0].classList.remove("invalidInput");
        attendingDoctorValidFlag = true;
        console.log("set valid doctors list");
        checkBtn();
    }

}

//when open list of doctors
$(document).ready(function () {
    $('.bfh-selectbox').on('show.bfhselectbox', function () {
        checkDoctorsList();
    });

});

$(document).ready(function () {
    $('.bfh-selectbox').on('change.bfhselectbox', function () {
        checkDoctorsList();
    });

});

function setRoleWhenErrorValidationOnBackend() {

    var status = document.getElementById("status").value;
    switch (status) {
        case "Inpatient" :
            document.getElementById("inputGroupSelect01").value = 2;
            break;
        case "Outpatient" :
            document.getElementById("inputGroupSelect01").value = 3;
            break;
        case "Issued" :
            document.getElementById("inputGroupSelect01").value = 4;
            break;
    }

}

function setStatus(el) {
    switch (el.innerText) {
        case "Set Status":
            document.getElementById("status").value = "";

            break;
        case "Inpatient":
            document.getElementById("status").value = "Inpatient";

            break;
        case "Outpatient":
            document.getElementById("status").value = "Outpatient";

            break;
        case "Issued":
            document.getElementById("status").value = "Issued";

            break;
    }
}




document.getElementById("inputDateOfBirth").addEventListener("keyup", checkDate);
document.getElementById("inputDateOfBirth").addEventListener("change", checkDate);


document.getElementById("surnameInput").addEventListener('keyup', checkSurname);
document.getElementById("surnameInput").addEventListener('keyup', checkBtn);


document.getElementById("nameInput").addEventListener('keyup', checkName);
document.getElementById("nameInput").addEventListener('keyup', checkBtn);


document.getElementById("secondnameInput").addEventListener('keyup', checkSecondname);
document.getElementById("secondnameInput").addEventListener('keyup', checkBtn);


document.getElementById("diagnosisInput").addEventListener('keyup', checkDiagnosis);
document.getElementById("diagnosisInput").addEventListener('keyup', checkBtn);


document.getElementById("insuranceNumberInput").addEventListener('keyup', checkInsuranceNumber);
document.getElementById("insuranceNumberInput").addEventListener('keyup', checkBtn);


document.getElementById("attendingDoctorInput").addEventListener('keyup', checkBtn);

