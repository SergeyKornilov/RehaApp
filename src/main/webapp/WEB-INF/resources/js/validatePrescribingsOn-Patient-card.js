
var nameValidFlag;
var doseValidFlag = true;
var dateStartValidFlag;
var dateEndValidFlag;
var daysOfWeekValidFlag;



var addPrescribingBtn = document.getElementById("addPrescribingBtn");


function checkBtn() {


    if (nameValidFlag && doseValidFlag && dateEndValidFlag && dateStartValidFlag && daysOfWeekValidFlag){
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

    console.log(doseValidFlag);

}

function checkDateStart(){
    if(document.getElementById("inputDateStart").value.length != 0){
        dateStartValidFlag = true;
        document.getElementById("inputDateStart").classList.remove("errorInput");
        checkBtn();
    }
    else {
        document.getElementById("inputDateStart").classList.add("errorInput");
        dateStartValidFlag = false;
        checkBtn();
    }
}

function checkDateEnd(){
    if(document.getElementById("inputDateEnd").value.length != 0){
        dateEndValidFlag = true;
        document.getElementById("inputDateEnd").classList.remove("errorInput");
        checkBtn();
    }
    else {
        document.getElementById("inputDateEnd").classList.add("errorInput");
        dateEndValidFlag = false;
        checkBtn();
    }
}

function checkDaysOfWeek(){
    var er = document.getElementById("daysOfWeekError").querySelector("li:nth-child(1)");

    if (document.getElementById("sunday").classList.contains("active") ||
        document.getElementById("monday").classList.contains("active") ||
        document.getElementById("tuesday").classList.contains("active") ||
        document.getElementById("wednesday").classList.contains("active") ||
        document.getElementById("thursday").classList.contains("active") ||
        document.getElementById("friday").classList.contains("active") ||
        document.getElementById("saturday").classList.contains("active")){

        daysOfWeekValidFlag = true;
        er.classList.add("valid");
        er.classList.remove("invalid")
        checkBtn();
    } else {

        daysOfWeekValidFlag = false;
        er.classList.add("invalid");
        er.classList.remove("valid")
        checkBtn();


    }
}





document.getElementById("prescribingName").addEventListener('keyup', checkName);
document.getElementById("prescribingName").addEventListener('keyup', checkBtn);

document.getElementById("prescribingDose").addEventListener('keyup', checkDose);
document.getElementById("prescribingDose").addEventListener('keyup', checkBtn);

document.getElementById("inputDateEnd").addEventListener("blur", checkDateEnd);
document.getElementById("inputDateStart").addEventListener("blur", checkDateStart);
