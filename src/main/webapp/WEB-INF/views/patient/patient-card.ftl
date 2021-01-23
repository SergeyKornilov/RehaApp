<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<#--    <link rel="stylesheet" href="css/styleHeader.css">-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>


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
    <link rel="stylesheet" href="/css/style-patient-card.css">


</head>
<#include "../parts/head.ftl">
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
            <td>${(prescribing.type)!}</td>
            <td>${(prescribing.name)!}</td>
            <td><p id="timePrescribingList${prescribing.id}"><#list prescribing.time?sort as time>
                    ${time}

                    </#list> </p></td>
            <td>${(prescribing.dose)!}</td>
            <td>${(prescribing.dateStart)!}</td>
            <td>${(prescribing.dateEnd)!}</td>
            <td>
                <p id="weeks${(prescribing.id)!}">
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
                 <td><a href="/prescribing/delete/${(prescribing.id)!}">Delete</a></td>
                 <td><button type="button" onclick="editPrescribing(${(prescribing.id)!}, '${(prescribing.type)!}' , '${(prescribing.name)!}',
                             '${(prescribing.dose)!}', '${(prescribing.dateStart)!}', '${(prescribing.dateEnd)!}')">Edit</button>
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

<button type="button" class="" onclick="openAddPrescribing()">Add prescribing</button>
    </div>
    <div class="row">
        <h3 id="prescribingTitle"></h3>
        <#if existingProcedureErrorMessage??>
             <p class="errorText">${existingProcedureErrorMessage}</p>
        </#if>


    </div>
    <div class="row">
<div id="prescribingForm" hidden="true">

<form method="post" onsubmit="setTime()">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />



    <input id="postTypeAddPrescribing" type="hidden" name="action" value="addPrescribing">

    <input type="hidden" name="idPatient" value="${patient.id}">
    <input id="prescribingId" type="hidden" name="id" disabled>



    <#if errors??>
        <#list errors as error>
            <#if error.defaultMessage == "Type cannot be empty">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
            <#if error.defaultMessage == "Type length: max - 25 characters">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
        </#list>
    </#if>
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label id="btnProcedure" class="btn btn-secondary active " onclick="selectProcedure()">
            <input type="radio" name="options" id="option1" autocomplete="off" checked> Procedure
        </label>
        <label id="btnMedicines" class="btn btn-secondary ml-1" onclick="selectMedicines()">
            <input type="radio" name="options" id="option2" autocomplete="off"> Medicines
        </label>
    </div>
    <p></p>

    <input hidden type="text" name="type" value="procedure">

    <#if errors??>
        <#list errors as error>
            <#if error.defaultMessage == "Name cannot be empty">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
            <#if error.defaultMessage == "Name length: min 2 characters, max - 25">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
        </#list>
    </#if>
<p>
    <label>Name</label>
    <input id="prescribingName" type="text" name="name">
</p>
    <#if errors??>
        <#list errors as error>
            <#if error.defaultMessage == "Date start cannot be empty">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
            <#if error.defaultMessage == "Date end cannot be empty">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
            <#if error.defaultMessage == "End day is earlier than start day">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
            <#if error.defaultMessage == "Start day in the past">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
        </#list>
    </#if>
    <p id="dateInput">
        Date start <input id="inputDateStart" type="text" class="date start" name="dateStart"/>
        Date end <input type="text" class="date end" name="dateEnd"/>
    </p>

    <#if errors??>
        <#list errors as error>
            <#if error.defaultMessage == "Day of the week cannot be empty">
                <p class="errorText">${error.defaultMessage}</p>
            </#if>
        </#list>
    </#if>
    <div id="daysOfWeek" class="row">
        <div class="col-12">
        <button id="sunday" onclick="setWeek(this)" type="button" class="" data-toggle="button" aria-pressed="false" autocomplete="off">
            Sunday
        </button>
        <input id="dayOfWeek-sunday" name="dayOfWeeks" disabled="true" value="Sunday" hidden>

        <button id="monday" onclick="setWeek(this)" type="button" class=""  data-toggle="button" aria-pressed="false" autocomplete="off">
            Monday
        </button>
        <input id="dayOfWeek-monday" name="dayOfWeeks" disabled="true" value="Monday" hidden>

        <button id="tuesday" onclick="setWeek(this)" type="button" class="" data-toggle="button" aria-pressed="false" autocomplete="off">
            Tuesday
        </button>
        <input id="dayOfWeek-tuesday" name="dayOfWeeks" disabled="true" value="Tuesday" hidden>

        <button id="wednesday" onclick="setWeek(this)" type="button" class=" " data-toggle="button" aria-pressed="false" autocomplete="off">
            Wednesday
        </button>
        <input id="dayOfWeek-wednesday" name="dayOfWeeks" disabled="true" value="Wednesday" hidden>

        <button id="thursday" onclick="setWeek(this)" type="button" class="" data-toggle="button" aria-pressed="false" autocomplete="off">
            Thursday
        </button>
        <input id="dayOfWeek-thursday" name="dayOfWeeks" disabled="true" value="Thursday" hidden>

        <button id="friday" onclick="setWeek(this)" type="button" class="" data-toggle="button" aria-pressed="false" autocomplete="off">
            Friday
        </button>
        <input id="dayOfWeek-friday" name="dayOfWeeks" disabled="true" value="Friday" hidden>

        <button id="saturday" onclick="setWeek(this)" type="button" class="" data-toggle="button" aria-pressed="false" autocomplete="off">
            Saturday
        </button>
        <input id="dayOfWeek-saturday" name="dayOfWeeks" disabled="true" value="Saturday" hidden>
        </div>
    </div>

        <div class="row">

            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Dose cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Dose length: max - 25">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
    <div id="doseInput" hidden>
        <label>dose</label>
        <input id="prescribingDose" type="text" name="dose" value="1">
    </div>
        </div>
    <div class="row">
        <div class="col-12">
        <button type="button" class="" onclick="addProcedureTimeInput()">+ times per day</button>
        <button type="button" class="" onclick="deleteProcedureTimeInput()">- times per day</button>
    </div></div>
    <div class="row">
        <div class="col-12">
            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Time cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
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

        </div>
    </div>




    <button type="submit" class="">Add</button>
</form>
    <p></p>
    <button type="button" class="" onclick="clearPrescribingForm()">Clear</button>




</div>
    </div>
        <div class="row">
<div class="footer">

</div>

</div>
</div>
<script src="/js/patient-card.js" type="text/javascript"></script>


</body>
</html>