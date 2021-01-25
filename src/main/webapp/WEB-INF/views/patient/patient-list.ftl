<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .patient-list-back{
        background: rgba(135, 234, 254, 0.3);
        }
        .btn-add-patient{
            width: 150px;
            height: 35px;
            color: white;
            box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -webkit-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -moz-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            background: linear-gradient(103.31deg, #0C8AFE -20.48%, #F806FD 118.31%);
            border-radius: 5px;
            border: none;
        }
        .btn-open-card-patient{
            color: white;
            width: 90px;
            height: 35px;
            box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -webkit-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -moz-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            background: linear-gradient(103.31deg, #0C8AFE -20.48%, #F806FD 118.31%);
            border-radius: 5px;
            border: none;
        }
    </style>
</head>
<#include "../parts/head.ftl">
<body>
<div class="container">



            <div class="col"> <h1 style="text-align: center; margin-top: 30px;margin-bottom: 30px" class="display-4">Patients list</h1></div>
<div class="row ">
    <div class="col-10">

            <input class="form-control" style="margin-bottom: 30px; width: 350px" type="text" placeholder="Search" id="search-text" onkeyup="patientsTableSearch()">

    </div>
    <div class="col-2 text-right">
            <a style="margin-left: 30px" class="pull-right" href="/add-patient-page"><button type="button" class="btn-add-patient">Add patient</button></a>

    </div>
</div>
    <div class="row">
        <div class="col-12">
            <table id="tableListPatients" class="table patient-list-back">
<tr>
    <td>id</td>
    <td>surname</td>
    <td>name</td>
    <td>secondname</td>
    <td>dateOfBirth</td>
    <td>diagnosis</td>
    <td>insuranceNumber</td>
    <td>attendingDoctor</td>
    <td>status</td>
    <td></td>
    <td></td>
    <td></td>
</tr>
<#list patients as patient>
    <#if patient.id??>
    <tr>
        <td>${patient.id}</td>
        <td>${patient.surname}</td>
        <td>${patient.name}</td>
        <td>${patient.secondname}</td>
        <td>${patient.dateOfBirth?datetime?string('dd.MM.yyyy')}</td>
        <td>${patient.diagnosis}</td>
        <td>${patient.insuranceNumber}</td>
        <td>${patient.attendingDoctor}</td>
        <td>${patient.status}</td>
        <td><a href="/patient/edit/${patient.id}"><img src="/img/edit.png"></a></td>
        <td><a href="/patient/delete/${patient.id}"><img src="/img/delete.png"></a></td>
        <td><a href="/patient/card/${patient.id}"><button type="button" class="btn-open-card-patient">Open card</button></a></td>
    </tr>
    </#if>
</#list>
</table>

        </div>
    </div>
</div>


<script>
    function patientsTableSearch() {

        var phrase = document.getElementById('search-text');
        var table = document.getElementById('tableListPatients');
        var regPhrase = new RegExp(phrase.value, 'i');
        var flag = false;
        for (var i = 1; i < table.rows.length; i++) {
            flag = false;
            for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
                flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
                if (flag) break;
            }
            if (flag) {
                table.rows[i].style.visibility = " visible";
            } else {
                table.rows[i].style.visibility = "collapse";
            }

        }
    }
</script>
</body>
</html>