<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <td>Date</td>
        <td>Status</td>
        <td>Time</td>
    </tr>

<#list events?sort_by("date") as event>
        <tr>
            <td>${event.date}</td>
            <td>${event.status}</td>
            <td>${event.time}</td>
            <td>${event.prescribing.type}</td>
            <td>${event.prescribing.patient.name}</td>
            <td>${event.prescribing.name}</td>
        </tr>
</#list>
</table>

</body>
</html>