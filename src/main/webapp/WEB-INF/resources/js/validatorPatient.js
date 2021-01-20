
var surnameValidFlag;
var nameValidFlag;
var secondnameValidFlag;
var diagnosisValidFlag;
var insuranceNumberValidFlag;
var attendingDoctorValidFlag;
var statusValidFlag;

var addPatientBtn = document.getElementById("addPatientBtn");


function checkBtn() {
    console.log(addPatientBtn);
    if (surnameValidFlag && nameValidFlag && secondnameValidFlag && diagnosisValidFlag && insuranceNumberValidFlag &&
        attendingDoctorValidFlag && statusValidFlag){
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

function checkAttendingDoctor(){

    var el = document.getElementById("attendingDoctorInput");

    el.value = el.value.trim();
    var str = el.value;

    var error1 = document.getElementById("errorsAttendingDoctor").querySelector("li:nth-child(1)");
    var error2 = document.getElementById("errorsAttendingDoctor").querySelector("li:nth-child(2)");

    if (str.length < 5 || str.length > 50) {
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

    if (error1.classList.contains("valid") && error2.classList.contains("valid")) attendingDoctorValidFlag = true;
    else attendingDoctorValidFlag = false;
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


document.getElementById("attendingDoctorInput").addEventListener('keyup', checkAttendingDoctor);
document.getElementById("attendingDoctorInput").addEventListener('keyup', checkBtn);

