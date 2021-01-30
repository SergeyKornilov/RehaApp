<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .errorText {
            color: #e74c3c;
        }
    </style>
    <link rel="stylesheet" href="/css/styleAdmin-panel.css">

    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
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

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js"
            integrity="sha512-m4xvGpNhCfricSMGJF5c99JBI8UqWdIlSmybVLRPo+LSiB9FHYH73aHzYZ8EdlzKA+s5qyv0yefvkqjU2ErLMg=="
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css"
          integrity="sha512-UPFdMcy+35cR5gyOgX+1vkDEzlMa3ZkZJUdaI1JoqWbH7ubiS/mhGrcM5C72QYouc2EascN3UtUrYnPoUpk+Pg=="
          crossorigin="anonymous"/>

</head>
<#include "../parts/head.ftl">

<body <#if !add>onload="validWhenEditLoad()" <#else> onload="setRoleWhenErrorValidationOnBackend()"</#if>>
<div class="container">
    <div class="row">
        <div style="text-align: center" class="col ">
            <#if add>
                <h1 class="display-4">Patient ADD</h1>
                <#assign formAction>/add-patient-page</#assign>
            <#else>
                <h1 class="display-4">Patient EDIT</h1>
                <#assign formAction>/patient-edit</#assign>
            </#if>
        </div>
    </div>
    <div class="row">

        <form action=${formAction} method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#if !add>
                <p>
                    <input type="hidden" name="id" value="${(patient.id)!}">
                </p>
            </#if>
            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Surname cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Surname length: min 2 characters, max - 25">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>


            <div class="group">
                <input id="surnameInput" onblur="checkSurname()" class="dynamic" type="text" required name="surname"
                       autocomplete="off" value="${(patient.surname)!}">
                <span class="bar"></span>
                <label>Surname</label>
                <ul id="errorsSurname" class="input-requirements">
                    <li>At least 2 characters long and max 25</li>
                    <li>Must only contain letters</li>
                </ul>
            </div>

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
            <div class="group">
                <input id="nameInput" onblur="checkName()" class="dynamic" type="text" required name="name"
                       autocomplete="off" value="${(patient.name)!}">
                <span class="bar"></span>
                <label>Name</label>
                <ul id="errorsName" class="input-requirements">
                    <li>At least 2 characters long and max 25</li>
                    <li>Must only contain letters</li>
                </ul>
            </div>

            <div class="group">
                <input id="secondnameInput" onblur="checkSecondname()" class="dynamic" required type="text" name="secondname"
                       autocomplete="off" value="${(patient.secondname)!}">
                <span class="bar"></span>
                <label>Secondname</label>
                <ul id="errorsSecondname" class="input-requirements">
                    <li>Must only contain letters</li>
                </ul>
            </div>


            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Patient must be over 18 years of age">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Age can`t be more than 200 years">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Date in the future">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Empty date of birth">

                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>


            <p>
                <input class="form-control" id="inputDateOfBirth" type="date" name="dateOfBirth" placeholder="age"
                       value="${(patient.dateOfBirth?string('yyyy-MM-dd'))!}"/>
            </p>


            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Diagnosis name cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Diagnosis length: min 2 characters, max - 25">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
            <div class="group">
                <input id="diagnosisInput" onblur="checkDiagnosis()" class="dynamic" type="text" required
                       name="diagnosis" autocomplete="off" value="${(patient.diagnosis)!}">
                <span class="bar"></span>
                <label>Diagnosis</label>
                <ul id="errorsDiagnosis" class="input-requirements">
                    <li>At least 2 characters long and max 25</li>
                    <li>Must only contain letters</li>
                </ul>
            </div>


            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "InsuranceNumber name cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Insurance Number length: min 8 characters, max - 25">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Duplicate insurance number">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
            <div class="group">
                <input id="insuranceNumberInput" onblur="checkInsuranceNumber()" class="dynamic" type="text" required
                       name="insuranceNumber" autocomplete="off" value="${(patient.insuranceNumber)!}">
                <span class="bar"></span>
                <label>InsuranceNumber</label>
                <ul id="errorsInsuranceNumber" class="input-requirements">
                    <li>At least 8 characters long and max 25</li>
                </ul>
            </div>


            <div id="attendingDoctorInput" class="bfh-selectbox" data-name="attendingDoctor"
                 data-value="${(patient.attendingDoctor)!}" data-filter="true">
                <div data-value="Select attending doctor">Select attending doctor</div>
                <#if users??>
                    <#list users as user>
                        <div data-value="${user.fullName}">${user.fullName}</div>
                    </#list>
                </#if>
            </div>


            <#if errors??>
                <#list errors as error>
                    <#if error.defaultMessage == "Status cannot be empty">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                    <#if error.defaultMessage == "Status length: min 2 characters, max - 15">
                        <p class="errorText">${error.defaultMessage}</p>
                    </#if>
                </#list>
            </#if>
            <p hidden>
                <input onchange="setRoleWhenErrorValidationOnBackend()" id="status" type="text" name="status"
                       placeholder="status" value="${(patient.status)!}"/>
            </p>
            <div class="input-group mb-3 " style="width: 300px">
                <select onclick="checkStatus()" class="custom-select removeShadow" id="inputGroupSelect01">
                    <option id="selectOptionSetStatus" value="1" onclick="setStatus(this)">Set Status</option>
                    <option id="selectOptionInpatient" value="2" onclick="setStatus(this)">Inpatient</option>
                    <option id="selectOptionOutpatient" value="3" onclick="setStatus(this)">Outpatient</option>
                    <option id="selectOptionIssued " value="4" onclick="setStatus(this)">Issued</option>
                </select>
            </div>
            <p>
                <#if add>
                    <button onclick="removeRequeredSecondnameInput()" class="btn btn-lg btn-primary" disabled id="addPatientBtn" type="submit">Add</button>
                <#else>
                    <button onclick="removeRequeredSecondnameInput()" class="btn btn-lg btn-primary" disabled id="addPatientBtn" type="submit">Save</button>
                </#if>
            </p>
        </form>
    </div>
</div>

<#if patient??>
    <script>
        $(document).ready(function () {
            checkSurname();
            checkName();
            checkSecondname();
            checkDiagnosis();
            checkInsuranceNumber();
            checkDoctorsList();
            checkStatus();
            checkDate();
        });
    </script>
</#if>


<script src="/js/validatorPatient.js" type="text/javascript"></script>

<script>

    function validWhenEditLoad() {

        console.log("valid when edit");

        setRoleWhenErrorValidationOnBackend();
        checkSurname();
        checkName();
        checkSecondname();
        checkDiagnosis();
        checkInsuranceNumber();
        checkDoctorsList();

        console.log("valid finished");
        var status;

        <#if patient??>
        status = "${patient.status}";
        </#if>

        switch (status) {
            case "" :
                document.getElementById("inputGroupSelect01").value = 1;
                document.getElementById("selectOptionSetStatus").click();
                break;
            case "Inpatient" :
                document.getElementById("inputGroupSelect01").value = 2;
                document.getElementById("selectOptionInpatient").click();
                break;
            case "Outpatient" :
                document.getElementById("inputGroupSelect01").value = 3;
                document.getElementById("selectOptionOutpatient").click();
                break;
            case "Issued" :
                document.getElementById("inputGroupSelect01").value = 4;
                document.getElementById("selectOptionIssued").click();
                break;
        }
        checkStatus();
    }

    function removeRequeredSecondnameInput(){
        document.getElementById("secondnameInput").removeAttribute("required");
    }

</script>
</body>
</html>