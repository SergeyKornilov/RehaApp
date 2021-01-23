
var countTimeInputs = 2;

function clearPrescribingForm(){

    $('#dateInput .date').datepicker('setDate', null);
    $('#sunday').removeClass("active");
    $('#monday').removeClass("active");
    $('#tuesday').removeClass("active");
    $('#wednesday').removeClass("active");
    $('#thursday').removeClass("active");
    $('#friday').removeClass("active");
    $('#saturday').removeClass("active");

    document.getElementById("dayOfWeek-" + "sunday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "monday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "tuesday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "wednesday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "thursday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "friday").setAttribute("disabled", "true");
    document.getElementById("dayOfWeek-" + "saturday").setAttribute("disabled", "true");


    document.getElementById('prescribingDose').value="";
    document.getElementById('prescribingName').value="";


    while(countTimeInputs > 2) {
        deleteProcedureTimeInput();

    }
}

function addProcedureTimeInput() {
    while (countTimeInputs < 2) countTimeInputs++;                   // ???
    if (countTimeInputs <= 5) {

        document.getElementById('procedureTimeInput' + countTimeInputs).removeAttribute("hidden");

        countTimeInputs++;
    } else
    {
        alert('Maximum!');
    }
}
function deleteProcedureTimeInput() {
    if (countTimeInputs > 2) {
        countTimeInputs--;

        document.getElementById('procedureTimeInput' + countTimeInputs).setAttribute("hidden", "true");
    } else
    {
        alert('Minimum!');
    }
}




function editPrescribing(id, type, name, dose, dateStart, dateEnd) {     //на вход принимаем значения из списка назначений
    clearPrescribingForm();

    document.getElementById("prescribingTitle").innerText="Edit prescribing";
    document.getElementById("prescribingForm").removeAttribute("hidden");

    document.getElementById("postTypeAddPrescribing").setAttribute("value", "edit");

    if(type==="procedure") selectProcedure();
    if(type==="medicines") selectMedicines();

    document.getElementById("prescribingId").removeAttribute("disabled");
    document.getElementById("prescribingId").setAttribute("value", id);

    // document.getElementById("prescribingName").setAttribute("value", name);
    // document.getElementById("prescribingDose").setAttribute("value", dose);

    document.getElementById('prescribingDose').value=dose;
    document.getElementById('prescribingName').value=name;

    // document.getElementsByName("dateStart")[0].setAttribute("value", dateStart);
    //document.getElementsByName("dateEnd")[0].setAttribute("value", dateEnd);


    dateStart= dateStart.split(' ')[0]; //приводим время под формат для datepicker
    dateEnd = dateEnd.split(' ')[0];
    $('#dateInput .date.start').datepicker('setDate', dateStart);
    $('#dateInput .date.end').datepicker('setDate', dateEnd);




    var strWeeks = document.getElementById("weeks" + id).innerText;

    if (strWeeks.indexOf("Sunday") !== -1) document.getElementById("sunday").click();
    if (strWeeks.indexOf("Monday") !== -1) document.getElementById("monday").click();
    if (strWeeks.indexOf("Tuesday") !== -1) document.getElementById("tuesday").click();
    if (strWeeks.indexOf("Wednesday") !== -1) document.getElementById("wednesday").click();
    if (strWeeks.indexOf("Thursday") !== -1) document.getElementById("thursday").click();
    if (strWeeks.indexOf("Friday") !== -1) document.getElementById("friday").click();
    if (strWeeks.indexOf("Saturday") !== -1) document.getElementById("saturday").click();



//подставляем время
    var strTime = document.getElementById("timePrescribingList"+id).innerText.split(" ");
    for(x = 1; x < strTime.length; x++){
        addProcedureTimeInput();
    }
    for(x=0; x < strTime.length; x++){
        document.getElementsByClassName("bfh-selectbox-option")[x].textContent=strTime[x];
    }
}

function setWeek(el) {
    var day = el.getAttribute("id");
    // el.classList.add("active");
    if(document.getElementById("dayOfWeek-" + day).getAttribute("disabled") === "true"){
        document.getElementById("dayOfWeek-" + day).removeAttribute("disabled");
        el.classList.add("active");
    } else {
        document.getElementById("dayOfWeek-" + day).setAttribute("disabled", "true");
        el.classList.remove("active");
    }
}


function selectProcedure() {

    document.getElementById("btnProcedure").classList.add("active");
    document.getElementById("btnMedicines").classList.remove("active");

    document.getElementsByName("type")[0].setAttribute("value", "procedure");
    document.getElementById("doseInput").setAttribute("hidden", "true");
    document.getElementsByName("dose")[0].setAttribute("value", "1");

    //open weeks pattern input
    document.getElementById("daysOfWeek").removeAttribute("hidden");

    document.getElementById("dayOfWeek-sunday").setAttribute("disabled", "true");
    document.getElementById("sunday").classList.remove("active");

    document.getElementById("dayOfWeek-monday").setAttribute("disabled", "true");
    document.getElementById("monday").classList.remove("active");

    document.getElementById("dayOfWeek-tuesday").setAttribute("disabled", "true");
    document.getElementById("tuesday").classList.remove("active");

    document.getElementById("dayOfWeek-wednesday").setAttribute("disabled", "true");
    document.getElementById("wednesday").classList.remove("active");

    document.getElementById("dayOfWeek-thursday").setAttribute("disabled", "true");
    document.getElementById("thursday").classList.remove("active");

    document.getElementById("dayOfWeek-friday").setAttribute("disabled", "true");
    document.getElementById("friday").classList.remove("active");

    document.getElementById("dayOfWeek-saturday").setAttribute("disabled", "true");
    document.getElementById("saturday").classList.remove("active");
}
function selectMedicines() {
    document.getElementById("btnMedicines").classList.add("active");
    document.getElementById("btnProcedure").classList.remove("active");
    document.getElementsByName("type")[0].setAttribute("value", "medicines");
    document.getElementById("doseInput").removeAttribute("hidden");
    document.getElementsByName("dose")[0].setAttribute("value", "");

    //hide weeks pattern input
    document.getElementById("daysOfWeek").setAttribute("hidden", "true");

    document.getElementById("dayOfWeek-sunday").removeAttribute("disabled");
    document.getElementById("sunday").classList.add("active");

    document.getElementById("dayOfWeek-monday").removeAttribute("disabled");
    document.getElementById("monday").classList.add("active");

    document.getElementById("dayOfWeek-tuesday").removeAttribute("disabled");
    document.getElementById("tuesday").classList.add("active");

    document.getElementById("dayOfWeek-wednesday").removeAttribute("disabled");
    document.getElementById("wednesday").classList.add("active");

    document.getElementById("dayOfWeek-thursday").removeAttribute("disabled");
    document.getElementById("thursday").classList.add("active");

    document.getElementById("dayOfWeek-friday").removeAttribute("disabled");
    document.getElementById("friday").classList.add("active");

    document.getElementById("dayOfWeek-saturday").removeAttribute("disabled");
    document.getElementById("saturday").classList.add("active");




}


document.getElementById("timeInputForm").addEventListener("DOMSubtreeModified", switchTime);
function switchTime() {
    var elements = document.getElementsByClassName("bfh-selectbox-option");
    for (var i = 0; i < elements.length; i ++){
        switch (document.getElementsByClassName("bfh-selectbox-option")[i].textContent) {
            case "Morning": document.getElementsByClassName("bfh-selectbox-option")[i].textContent = "08:00";
                break;
            case "Afternoon": document.getElementsByClassName("bfh-selectbox-option")[i].textContent = "14:00";
                break;
            case "Evening": document.getElementsByClassName("bfh-selectbox-option")[i].textContent = "20:00";
                break;
        }
    }
}



//перед отправкой в скрытые input подставляем введенное время из spans
function setTime() {
    for (x = 1; x <= 5; x++){
        if (document.getElementById("procedureTimeInput" + x).getAttribute("hidden") === "true") break;
        var text = document.getElementById("procedureTimeInput" + x).getElementsByClassName("bfh-selectbox-option")[0].textContent;
        document.getElementById("time" + x).removeAttribute("disabled");
        document.getElementById("time" + x).setAttribute("value", text);
    }
}



function openAddPrescribing() {
    document.getElementById("prescribingTitle").innerText="Add prescribing";
    if (document.getElementById("prescribingForm").getAttribute("hidden")==="true")
    {document.getElementById("prescribingForm").removeAttribute("hidden");
        document.getElementById("postTypeAddPrescribing").setAttribute("value", "addPrescribing");
        document.getElementById("prescribingId").setAttribute("disabled", 'true');
    }
    else {document.getElementById("prescribingForm").setAttribute("hidden", "true")}
}


var x = 0;
function addPrescribingInput() {
    if (x < 10) {
        var str = '<input type="text" class="link" placeholder="Time"> <div id="input' + (x + 1) + '"></div>';
        document.getElementById('input' + x).innerHTML = str;
        x++;
    } else
    {
        alert('STOP it!');
    }
}


<!-- Datepair script-->

$('#dateInput .date').datepicker({
    'format': 'dd.mm.yyyy',
    'autoclose': true
});

// initialize datepair
var basicExampleEl = document.getElementById('dateInput');
var datepair = new Datepair(basicExampleEl);
