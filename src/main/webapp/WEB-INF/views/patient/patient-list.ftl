<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>Patients list</p>
<table>
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
    </tr>
    </#if>
</#list>
</table>
<a href="/patient-add/">Add patient</a>

</body>
</html>