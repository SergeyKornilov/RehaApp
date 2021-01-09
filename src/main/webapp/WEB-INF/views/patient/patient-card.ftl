<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

    <!--  css for Date Start/End-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />

    <!--  js for Date Start/End-->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script>

    <!--  js for Date Start/End - connect fields  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datepair.js/0.4.16/datepair.js" integrity="sha512-rID0ls9BRjYTViswphwtM8n2d8eSykJklr9w23gRW94qwsFQnj2Syi/f2pvUcMa2H8P9Z0yqlIkth7Ma5G1mzg==" crossorigin="anonymous"></script>

    <!-- bootstrap-formhelpers fro TimeInputs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js" integrity="sha512-m4xvGpNhCfricSMGJF5c99JBI8UqWdIlSmybVLRPo+LSiB9FHYH73aHzYZ8EdlzKA+s5qyv0yefvkqjU2ErLMg==" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css" integrity="sha512-UPFdMcy+35cR5gyOgX+1vkDEzlMa3ZkZJUdaI1JoqWbH7ubiS/mhGrcM5C72QYouc2EascN3UtUrYnPoUpk+Pg==" crossorigin="anonymous" />



    <!--TEST -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.js" integrity="sha512-znJmsCnj1pyv7QN2fn4UYqXw3Bp2KXMYPPbphEOkhGP8RQbePNSLUfZWd733MXIQsyaszx4PhVq9jadWa1fq5w==" crossorigin="anonymous"></script>

    <style>
    .footer{
        height: 300px;
    }
    .hide{
        display: none;
    }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <h2>Patient cart #${patient.id}</h2>
    </div>
    <div class="row">
        <div class="col-6">
            <h3>${patient.surname} ${patient.name} ${patient.secondname}</h3>
        </div>
        <div class="col-6">
            <p>${patient.dateOfBirth} ${patient.dateOfBirth} ${patient.dateOfBirth} ${patient.status} ${patient.attendingDoctor}</p>
        </div>

    </div>

<!-- <p><img src=""></p> -->


    <div class="row">
<p>Prescribings:</p>


<#if prescribings??>
    <table class="table">
        <tr>
            <td>type</td>
            <td>name</td>
            <td>time</td>
            <td>dose</td>
            <td>dateStart</td>
            <td>dateEnd</td>
            <td>dayOfWeeks</td>
        </tr>
    <#list prescribings as prescribing>
        <tr>
            <td>${prescribing.type}</td>
            <td>${prescribing.name}</td>
            <td><p id="timePrescribingList${prescribing.id}"><#list prescribing.time?sort as time>
                    ${time}

                    </#list> </p></td>
            <td>${prescribing.dose}</td>
            <td>${prescribing.dateStart}</td>
            <td>${prescribing.dateEnd}</td>
            <td>
                <p id="weeks${prescribing.id}">
<#--                    <#if prescribing.dayOfWeeks?contain("Sunday")>Sunday</#if>-->

                    <#if prescribing.dayOfWeeks?seq_contains("Sunday")>Sunday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Monday")>Monday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Tuesday")>Tuesday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Wednesday")>Wednesday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Thursday")>Thursday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Friday")>Friday</#if>
                    <#if prescribing.dayOfWeeks?seq_contains("Saturday")>Saturday</#if>

<#--           <#list prescribing.dayOfWeeks?sort as day>-->
<#--               <#if day?contains("Sunday")>Sunday</#if>-->
<#--               <#if day?contains("Monday")>Monday</#if>-->
<#--               <#if day?contains("Tuesday")>Tuesday</#if>-->
<#--               <#if day?contains("Wednesday")>Wednesday</#if>-->
<#--               <#if day?contains("Thursday")>Thursday</#if>-->
<#--               <#if day?contains("Friday")>Friday</#if>-->
<#--               <#if day?contains("Saturday")>Saturday</#if>-->
<#--            </#list>-->

                </p>
            </td>
               <td></td>
                 <td><a href="/prescribing/delete/${prescribing.id}">Delete</a></td>
                 <td><button type="button" onclick="editPrescribing(${prescribing.id}, '${prescribing.type}' , '${prescribing.name}',
                             '${prescribing.dose}', '${prescribing.dateStart}', '${prescribing.dateEnd}')">Edit</button>
                 </td>
              <#--              <form method="post">
                      <input type="hidden" name="action" value="edit">

                      <input type="hidden" name="id" value="${(prescribing.id)!}">


                      <td><input type="text" name="type" placeholder="type" value="${(prescribing.type)!}"></td>
                      <td><input type="text" name="name" placeholder="name" value="${(prescribing.name)!}"></td>
                      <td><input type="text" name="time" placeholder="time" value="${(prescribing.time)!}"></td>
                      <td><input type="text" name="dose" placeholder="dose" value="${(prescribing.dose)!}"></td>
                      <td><input type="text" name="date_start" placeholder="date start" value="${(prescribing.dateStart)!}"></td>
                      <td><input type="text" name="date_end" placeholder="date end" value="${(prescribing.dateEnd)!}"></td>

                      <td>input for days of week</td>

                      <td><button type="submit">Save</button></td>
                      </form>
                      -->
        </tr>
    </#list>
    </table>
<#else>
    <p>no prescribing..</p>
</#if>
    </div>
    <div class="row">

<button type="button" class="btn btn-primary" onclick="openAddPrescribing()">Add prescribing</button>
<h3 id="prescribingTitle"></h3>


<div id="prescribingForm" hidden="true">

<form method="post" onsubmit="setTime()">



    <input id="postTypeAddPrescribing" type="hidden" name="action" value="addPrescribing">

    <input type="hidden" name="idPatient" value="${patient.id}">
    <input id="prescribingId" type="hidden" name="id" disabled>



    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label id="btnProcedure" class="btn btn-secondary active" onclick="selectProcedure()">
            <input type="radio" name="options" id="option1" autocomplete="off" checked> Procedure
        </label>
        <label id="btnMedicines" class="btn btn-secondary" onclick="selectMedicines()">
            <input type="radio" name="options" id="option2" autocomplete="off"> Medicines
        </label>
    </div>
    <p></p>

    <input hidden type="text" name="type" value="procedure">

<p>
    <label>Name</label>
    <input id="prescribingName" type="text" name="name">
</p>
    <p id="dateInput">
        Date start <input id="inputDateStart" type="text" class="date start" name="dateStart"/>
        Date end <input type="text" class="date end" name="dateEnd"/>
    </p>
    <div id="daysOfWeek">

        <button id="sunday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Sunday
        </button>
        <input id="dayOfWeek-sunday" name="dayOfWeeks" disabled="true" value="Sunday" hidden>

        <button id="monday" onclick="setWeek(this)" type="button" class="btn btn-primary"  data-toggle="button" aria-pressed="false" autocomplete="off">
            Monday
        </button>
        <input id="dayOfWeek-monday" name="dayOfWeeks" disabled="true" value="Monday" hidden>

        <button id="tuesday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Tuesday
        </button>
        <input id="dayOfWeek-tuesday" name="dayOfWeeks" disabled="true" value="Tuesday" hidden>

        <button id="wednesday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Wednesday
        </button>
        <input id="dayOfWeek-wednesday" name="dayOfWeeks" disabled="true" value="Wednesday" hidden>

        <button id="thursday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Thursday
        </button>
        <input id="dayOfWeek-thursday" name="dayOfWeeks" disabled="true" value="Thursday" hidden>

        <button id="friday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Friday
        </button>
        <input id="dayOfWeek-friday" name="dayOfWeeks" disabled="true" value="Friday" hidden>

        <button id="saturday" onclick="setWeek(this)" type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off">
            Saturday
        </button>
        <input id="dayOfWeek-saturday" name="dayOfWeeks" disabled="true" value="Saturday" hidden>

    </div>
    <p></p>
    <div id="doseInput" hidden>
        <label>dose</label>
        <input id="prescribingDose" type="text" name="dose" value="1">
    </div>
    <p></p>
    <p>
        <button type="button" class="btn btn-primary" onclick="addProcedureTimeInput()">+ times per day</button>
        <button type="button" class="btn btn-primary" onclick="deleteProcedureTimeInput()">- times per day</button>
        <label>time</label>
        <div id = "timeInputForm">
            <div id="procedureTimeInput1" class="bfh-selectbox" data-name="selectbox3" data-value="1" data-filter="true">
                <div data-value="1">Morning</div>
                <div data-value="2">Afternoon</div>
                <div data-value="3">Evening</div>
                <div data-value="4">08:00</div>
                <div data-value="5">09:00</div>
                <div data-value="6">10:00</div>
                <div data-value="7">11:00</div>
                <div data-value="8">12:00</div>
                <div data-value="9">13:00</div>
                <div data-value="10">14:00</div>
                <div data-value="11">15:00</div>
                <div data-value="12">16:00</div>
                <div data-value="13">17:00</div>
                <div data-value="14">18:00</div>
                <div data-value="15">19:00</div>
                <div data-value="16">20:00</div>
                <div data-value="17">21:00</div>
                <div data-value="18">22:00</div>
                <div data-value="19">23:00</div>
            </div>
            <input id="time1" name="time" hidden>
            <div id="procedureTimeInput2" hidden = "true" class="bfh-selectbox" data-name="selectbox3" data-value="1" data-filter="true">
                <div data-value="1">Morning</div>
                <div data-value="2">Afternoon</div>
                <div data-value="3">Evening</div>
                <div data-value="4">08:00</div>
                <div data-value="5">09:00</div>
                <div data-value="6">10:00</div>
                <div data-value="7">11:00</div>
                <div data-value="8">12:00</div>
                <div data-value="9">13:00</div>
                <div data-value="10">14:00</div>
                <div data-value="11">15:00</div>
                <div data-value="12">16:00</div>
                <div data-value="13">17:00</div>
                <div data-value="14">18:00</div>
                <div data-value="15">19:00</div>
                <div data-value="16">20:00</div>
                <div data-value="17">21:00</div>
                <div data-value="18">22:00</div>
                <div data-value="19">23:00</div>
            </div>
            <input id="time2" name="time" hidden disabled>
            <div id="procedureTimeInput3" hidden = "true" class="bfh-selectbox" data-name="selectbox3" data-value="1" data-filter="true">
                <div data-value="1">Morning</div>
                <div data-value="2">Afternoon</div>
                <div data-value="3">Evening</div>
                <div data-value="4">08:00</div>
                <div data-value="5">09:00</div>
                <div data-value="6">10:00</div>
                <div data-value="7">11:00</div>
                <div data-value="8">12:00</div>
                <div data-value="9">13:00</div>
                <div data-value="10">14:00</div>
                <div data-value="11">15:00</div>
                <div data-value="12">16:00</div>
                <div data-value="13">17:00</div>
                <div data-value="14">18:00</div>
                <div data-value="15">19:00</div>
                <div data-value="16">20:00</div>
                <div data-value="17">21:00</div>
                <div data-value="18">22:00</div>
                <div data-value="19">23:00</div>
            </div>
            <input id="time3" name="time" hidden disabled>
            <div id="procedureTimeInput4" hidden = "true" class="bfh-selectbox" data-name="selectbox3" data-value="1" data-filter="true">
                <div data-value="1">Morning</div>
                <div data-value="2">Afternoon</div>
                <div data-value="3">Evening</div>
                <div data-value="4">08:00</div>
                <div data-value="5">09:00</div>
                <div data-value="6">10:00</div>
                <div data-value="7">11:00</div>
                <div data-value="8">12:00</div>
                <div data-value="9">13:00</div>
                <div data-value="10">14:00</div>
                <div data-value="11">15:00</div>
                <div data-value="12">16:00</div>
                <div data-value="13">17:00</div>
                <div data-value="14">18:00</div>
                <div data-value="15">19:00</div>
                <div data-value="16">20:00</div>
                <div data-value="17">21:00</div>
                <div data-value="18">22:00</div>
                <div data-value="19">23:00</div>
            </div>
            <input id="time4" name="time" hidden disabled>
            <div id="procedureTimeInput5" hidden = "true" class="bfh-selectbox" data-name="selectbox3" data-value="1" data-filter="true">
                <div data-value="1">Morning</div>
                <div data-value="2">Afternoon</div>
                <div data-value="3">Evening</div>
                <div data-value="4">08:00</div>
                <div data-value="5">09:00</div>
                <div data-value="6">10:00</div>
                <div data-value="7">11:00</div>
                <div data-value="8">12:00</div>
                <div data-value="9">13:00</div>
                <div data-value="10">14:00</div>
                <div data-value="11">15:00</div>
                <div data-value="12">16:00</div>
                <div data-value="13">17:00</div>
                <div data-value="14">18:00</div>
                <div data-value="15">19:00</div>
                <div data-value="16">20:00</div>
                <div data-value="17">21:00</div>
                <div data-value="18">22:00</div>
                <div data-value="19">23:00</div>
            </div>
            <input id="time5" name="time" hidden disabled>
        </div>
        <div id = "myTimeInputs"></div>
    </p>



    <button type="submit" class="btn btn-primary">Add</button>
</form>
    <p></p>
    <button type="button" class="btn btn-primary" onclick="clearPrescribingForm()">Clear</button>




</div>
    </div>
        <div class="row">
<div class="footer">

</div>

</div>
</div>
<script>
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

    //    $('#prescribingDose').setAttribute('value', "123");



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
</script>


<script>
    function editPrescribing(id, type, name, dose, dateStart, dateEnd) {     //на вход принимаем значения из списка назначений
        clearPrescribingForm();

        document.getElementById("prescribingTitle").innerText="Edit prescribing";
        document.getElementById("prescribingForm").removeAttribute("hidden");

        document.getElementById("postTypeAddPrescribing").setAttribute("value", "edit");

        if(type==="procedure") selectProcedure();
        if(type==="medicines") selectMedicines();

        document.getElementById("prescribingId").removeAttribute("disabled");
        document.getElementById("prescribingId").setAttribute("value", id);

        document.getElementById("prescribingName").setAttribute("value", name);
        document.getElementById("prescribingDose").setAttribute("value", dose);

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
</script>
<script>
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
</script>

<script>
    function selectProcedure() {
        document.getElementById("btnProcedure").classList.add("active");
        document.getElementById("btnMedicines").classList.remove("active");

        document.getElementsByName("type")[0].setAttribute("value", "procedure");
        document.getElementById("doseInput").setAttribute("hidden", "true");
        document.getElementsByName("dose")[0].setAttribute("value", "1");
    }
    function selectMedicines() {
        document.getElementById("btnMedicines").classList.add("active");
        document.getElementById("btnProcedure").classList.remove("active");
        document.getElementsByName("type")[0].setAttribute("value", "medicines");
        document.getElementById("doseInput").removeAttribute("hidden");
        document.getElementsByName("dose")[0].setAttribute("value", "");
    }
</script>

<script>
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

</script>

<script>
        //перед отправкой в скрытые input подставляем введенное время из spans
        function setTime() {
            for (x = 1; x <= 5; x++){
                if (document.getElementById("procedureTimeInput" + x).getAttribute("hidden") === "true") break;
                var text = document.getElementById("procedureTimeInput" + x).getElementsByClassName("bfh-selectbox-option")[0].textContent;
                document.getElementById("time" + x).removeAttribute("disabled");
                document.getElementById("time" + x).setAttribute("value", text);
            }
        }
</script>
<script>
    function openAddPrescribing() {
        document.getElementById("prescribingTitle").innerText="Add prescribing";
        if (document.getElementById("prescribingForm").getAttribute("hidden")==="true")
        {document.getElementById("prescribingForm").removeAttribute("hidden");
         document.getElementById("postTypeAddPrescribing").setAttribute("value", "addPrescribing");
         document.getElementById("prescribingId").setAttribute("disabled", 'true');
        }
        else {document.getElementById("prescribingForm").setAttribute("hidden", "true")}
    }
    </script>

<script>
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
</script>

<!-- Datepair script-->
<script>
    $('#dateInput .date').datepicker({
        'format': 'dd.mm.yyyy',
        'autoclose': true
    });

    // initialize datepair
    var basicExampleEl = document.getElementById('dateInput');
    var datepair = new Datepair(basicExampleEl);
</script>
<script>

</script>
</body>
</html>