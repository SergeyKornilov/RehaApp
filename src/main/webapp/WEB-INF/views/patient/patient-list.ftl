<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<#include "../parts/head.ftl">
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
<p>Patients list</p>
<table class="table">
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
        <td>${patient.dateOfBirth}</td>
        <td>${patient.diagnosis}</td>
        <td>${patient.insuranceNumber}</td>
        <td>${patient.attendingDoctor}</td>
        <td>${patient.status}</td>
        <td><a href="/patient/edit/${patient.id}">Edit</a></td>
        <td><a href="/patient/delete/${patient.id}">Delete</a></td>
        <td><a href="/patient/card/${patient.id}">Open card</a></td>
    </tr>
    </#if>
</#list>
</table>
<a href="/add-patient-page">Add patient</a>
        </div>
    </div>
</div>

</body>
</html>