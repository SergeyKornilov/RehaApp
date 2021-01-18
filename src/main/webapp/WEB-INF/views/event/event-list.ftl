<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<
</head>
<#include "../parts/head.ftl">
<body>
<div class="container">
<div class="row">
    <div class="col-11">
<table id="tableListEvents" class="table table-striped table-dark">
    <tr>
        <td>Date</td>
        <td>Status</td>
        <td>Time</td>
        <td>Type</td>
        <td>Patient Name</td>
        <td>Prescribing description</td>
    </tr>

<#list events?sort_by("date") as event>
        <tr>
            <td>${event.date}</td>
            <td>${event.status}</td>
            <td>${event.time}</td>
            <td>${event.prescribing.type}</td>
            <td>${event.prescribing.patient.name}</td>
            <td>${event.prescribing.name}</td>
            <td><a href="/event/done/${event.id}"><button type="button">DONE</button> </a> </td>
        </tr>
</#list>
</table>
    </div>
    <div class="col-1">
        <button type="button" onclick="hideClosed()">Hide closed</button>
    </div>
</div>

</div>
<script>
    function hideClosed() {
        var table = document.getElementById("tableListEvents");
        for(var x = 1; x < table.rows.length; x ++){

            if (table.rows[x].cells[1].innerText === "close"){
                table.rows[x].style.display = "none";
            }
        }
    }
</script>
</body>
</html>