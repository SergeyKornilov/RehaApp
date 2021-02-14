<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <#--    <link rel="stylesheet" href="css/styleHeader.css">-->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <!--  css for Date Start/End-->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
          integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw=="
          crossorigin="anonymous"/>
    <!--  js for Date Start/End-->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"
            integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ=="
            crossorigin="anonymous"></script>
    <!--  js for Date Start/End - connect fields  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datepair.js/0.4.16/datepair.js"
            integrity="sha512-rID0ls9BRjYTViswphwtM8n2d8eSykJklr9w23gRW94qwsFQnj2Syi/f2pvUcMa2H8P9Z0yqlIkth7Ma5G1mzg=="
            crossorigin="anonymous"></script>
    <!-- bootstrap-formhelpers fro TimeInputs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js"
            integrity="sha512-m4xvGpNhCfricSMGJF5c99JBI8UqWdIlSmybVLRPo+LSiB9FHYH73aHzYZ8EdlzKA+s5qyv0yefvkqjU2ErLMg=="
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css"
          integrity="sha512-UPFdMcy+35cR5gyOgX+1vkDEzlMa3ZkZJUdaI1JoqWbH7ubiS/mhGrcM5C72QYouc2EascN3UtUrYnPoUpk+Pg=="
          crossorigin="anonymous"/>

    <!--TEST -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.js"
            integrity="sha512-znJmsCnj1pyv7QN2fn4UYqXw3Bp2KXMYPPbphEOkhGP8RQbePNSLUfZWd733MXIQsyaszx4PhVq9jadWa1fq5w=="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/style-patient-card.css">

</head>
<#include "../parts/head.ftl">
<body>

<div class="container">
    <div class="row center-block">
        <div class="col-12">
            <h1 style="text-align: center; margin-top: 30px; margin-bottom: 30px" class="display-4">Patient card
                #${patient.id}</h1>
        </div>

    </div>
    <div class="row">
        <div class="patient-info-back">
            <a href="/patient/edit/${patient.id}" style="position: absolute; right: 20px;top:25px"><img
                        src="/img/edit-patient-card.png"></a>
            <table class="patient-info-table">
                <tr>
                    <td class="patient-info-table-col1">Name:</td>
                    <td class="patient-info-table-col2">${(patient.name)!}</td>
                    <td rowspan="8">
                        <div class="user-avatar-back"><img src="/img/userdefault.png"></div>
                    </td>
                    <td rowspan="8" class="btn-edit-patient-card"></td>
                </tr>
                <tr>
                    <td>Secondname:</td>
                    <td class="patient-info-table-col2">${(patient.secondname)!}</td>
                </tr>
                <tr>
                    <td>Surname:</td>
                    <td class="patient-info-table-col2">${(patient.surname)!}</td>
                </tr>
                <tr>
                    <td>Date of birth:</td>
                    <td class="patient-info-table-col2">${(patient.dateOfBirth?string('dd.MM.yyyy'))!}</td>
                </tr>
                <tr>
                    <td>Diagnosis:</td>
                    <td class="patient-info-table-col2">${(patient.diagnosis)!}</td>
                </tr>
                <tr>
                    <td>Insurance number:</td>
                    <td class="patient-info-table-col2">${(patient.insuranceNumber)!}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td class="patient-info-table-col2">${(patient.status)!}</td>
                </tr>
                <tr>
                    <td>Attending doctor</td>
                    <td class="patient-info-table-col2">${(patient.attendingDoctor)!}</td>
                </tr>

            </table>
        </div>
    </div>

        <div class="row" style="margin-top: 30px; margin-bottom: 15px">
            <div class="col-10">
        <h4>Prescribings:</h4>
            </div>
            <div class="col-2">
        <a href="/prescribing/export/${patient.id}" ><button id="btnExportPdf" type="button" class="btn-add-prescribing">Export in PDF
        </button></a>
            </div>
        </div>
        <div class="row">
        <#if prescribings??>
            <table class="table">
                <tr>
                    <td>Type</td>
                    <td>Name</td>
                    <td>Time</td>
                    <td>Dose</td>
                    <td>Date start</td>
                    <td>Date end</td>
                    <td>Day of weeks</td>
                </tr>
                <#list prescribings as prescribing>
                    <tr>
                        <td>${(prescribing.type)!}</td>
                        <td>${(prescribing.name)!}</td>
                        <td><p id="timePrescribingList${prescribing.id}"><#list prescribing.time?sort as time>
                                    ${time}

                                </#list> </p></td>
                        <td>${(prescribing.dose)!}</td>
                        <td>${(prescribing.dateStart?string('dd.MM.yyyy'))!}</td>
                        <td>${(prescribing.dateEnd?string('dd.MM.yyyy'))!}</td>
                        <td>
                            <p id="weeks${(prescribing.id)!}">
                                <#if prescribing.dayOfWeeks?seq_contains("Sunday")>Sunday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Monday")>Monday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Tuesday")>Tuesday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Wednesday")>Wednesday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Thursday")>Thursday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Friday")>Friday</#if>
                                <#if prescribing.dayOfWeeks?seq_contains("Saturday")>Saturday</#if>


                            </p>
                        </td>
                        <td></td>
                        <td><a href="/prescribing/delete/${(prescribing.id)!}"><img src="/img/delete.png"></a></td>


                        <td>
                            <img style="cursor: pointer" src="/img/edit.png"
                                 onclick="editPrescribing(${(prescribing.id)!}, '${(prescribing.type)!}' , '${(prescribing.name)!}',
                                         '${(prescribing.dose)!}', '${(prescribing.dateStart)!}', '${(prescribing.dateEnd)!}')">
                        </td>

                    </tr>
                </#list>
            </table>
        <#else>
            <p>no prescribing..</p>
        </#if>
    </div>
    <div class="row">
        <button id="btnAaddPrescribing" type="button" class="btn-add-prescribing" onclick="openAddPrescribing()">Add
            prescribing
        </button>
    </div>

    <div class="row">
        <div id="prescribingForm" hidden="true">
            <div class="row">
                <div class="col">
                    <h3 id="prescribingTitle"></h3>
                    <#if existingProcedureErrorMessage??>
                        <p id="errorMessageP" class="errorText">${existingProcedureErrorMessage}</p>
                    </#if>
                </div>
            </div>

            <form method="post" style="width: 330px" onsubmit="setTime()">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <input id="postTypeAddPrescribing" type="hidden" name="action" value="addPrescribing">

                <input type="hidden" name="idPatient" value="${patient.id}">
                <input id="prescribingId" type="hidden" name="id" disabled>

                <#if errors??>
                    <#list errors as error>
                        <#if error.defaultMessage?contains("On the date")>
                            <p class="errorText">${error.defaultMessage}</p>
                        </#if>

                        <#if error.defaultMessage == "Type cannot be empty">
                            <p class="errorText">${error.defaultMessage}</p>
                        </#if>
                        <#if error.defaultMessage == "Type length: max - 25 characters">
                            <p class="errorText">${error.defaultMessage}</p>
                        </#if>
                    </#list>
                </#if>
                <div class="btn-group btn-group-toggle" data-toggle="buttons">

                    <button id="btnProcedure" onclick="selectProcedure()" class="active">Procedure</button>
                    <button id="btnMedicines" onclick="selectMedicines()" class="">Medicines</button>

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
                    <input onblur="checkName()" class="form-control" id="prescribingName" type="text" name="name">
                <ul id="errorsUsername" class="input-requirements">
                    <li>Name cannot be empty and max long 25</li>
                    <li>Must only contain letters</li>
                </ul>
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
                    Date start <input onchange="checkDateStart()" id="inputDateStart" type="text"
                                      class="date start form-control" name="dateStart" autocomplete="off"/>
                    Date end <input onchange="checkDateEnd()" id="inputDateEnd" type="text"
                                    class="date end form-control" name="dateEnd" autocomplete="off"/>
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
                        <ul id="daysOfWeekError" class="input-requirements">
                            <li>At least one day of the week must be selected</li>
                        </ul>
                        <button id="sunday" onclick="setWeek(this); checkDaysOfWeek()" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Sun
                        </button>
                        <input id="dayOfWeek-sunday" name="dayOfWeeks" disabled="true" value="Sunday" hidden>

                        <button id="monday" onclick="setWeek(this); checkDaysOfWeek()" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Mon
                        </button>
                        <input id="dayOfWeek-monday" name="dayOfWeeks" disabled="true" value="Monday" hidden>

                        <button id="tuesday" onclick="setWeek(this); checkDaysOfWeek();" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Tue
                        </button>
                        <input id="dayOfWeek-tuesday" name="dayOfWeeks" disabled="true" value="Tuesday" hidden>

                        <button id="wednesday" onclick="setWeek(this); checkDaysOfWeek();" type="button" class=" "
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Wen
                        </button>
                        <input id="dayOfWeek-wednesday" name="dayOfWeeks" disabled="true" value="Wednesday" hidden>

                        <button id="thursday" onclick="setWeek(this); checkDaysOfWeek();" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Thu
                        </button>
                        <input id="dayOfWeek-thursday" name="dayOfWeeks" disabled="true" value="Thursday" hidden>

                        <button id="friday" onclick="setWeek(this); checkDaysOfWeek();" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Fri
                        </button>
                        <input id="dayOfWeek-friday" name="dayOfWeeks" disabled="true" value="Friday" hidden>

                        <button id="saturday" onclick="setWeek(this); checkDaysOfWeek();" type="button" class=""
                                data-toggle="button" aria-pressed="false" autocomplete="off">
                            Sat
                        </button>
                        <input id="dayOfWeek-saturday" name="dayOfWeeks" disabled="true" value="Saturday" hidden>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
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


                            <input onblur="checkDose()" id="prescribingDose" class="form-control" type="text"
                                   name="dose" value="1">
                            <ul id="errorsDose" class="input-requirements">
                                <li>Dose cannot be empty and max long 25</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <button style="margin-bottom: 20px; margin-top: 20px" type="button" class="active"
                                onclick="addProcedureTimeInput()">+ times per day
                        </button>
                        <button type="button" class="active" onclick="deleteProcedureTimeInput()">- times per day
                        </button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <#if errors??>
                            <#list errors as error>
                                <#if error.defaultMessage == "Time cannot be empty">
                                    <p class="errorText">${error.defaultMessage}</p>
                                </#if>
                            </#list>
                        </#if>
                        <div id="timeInputForm">
                            <div id="procedureTimeInput1" class="bfh-selectbox" data-name="selectbox3" data-value="1"
                                 data-filter="true">
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
                            <div id="procedureTimeInput2" hidden="true" class="bfh-selectbox" data-name="selectbox3"
                                 data-value="1" data-filter="true">
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
                            <div id="procedureTimeInput3" hidden="true" class="bfh-selectbox" data-name="selectbox3"
                                 data-value="1" data-filter="true">
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
                            <div id="procedureTimeInput4" hidden="true" class="bfh-selectbox" data-name="selectbox3"
                                 data-value="1" data-filter="true">
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
                            <div id="procedureTimeInput5" hidden="true" class="bfh-selectbox" data-name="selectbox3"
                                 data-value="1" data-filter="true">
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
                        <div id="myTimeInputs"></div>

                    </div>
                </div>

                <button id="addPrescribingBtn" type="submit" class="" disabled>Add</button>
            </form>
            <p></p>
            <button type="button" class="active" onclick="clearPrescribingForm()">Clear</button>

        </div>
    </div>
    <div class="row">
        <div class="footer">

        </div>

    </div>
</div>

<script src="/js/patient-card.js" type="text/javascript"></script>

<script src="/js/validatePrescribingsOn-Patient-card.js" type="text/javascript"></script>

</body>
</html>