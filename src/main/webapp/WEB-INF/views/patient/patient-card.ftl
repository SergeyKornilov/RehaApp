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
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script><script type="text/javascript" src="jquery.timepicker.js"></script>

    <!--  js for Date Start/End - connect fields  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datepair.js/0.4.16/datepair.js" integrity="sha512-rID0ls9BRjYTViswphwtM8n2d8eSykJklr9w23gRW94qwsFQnj2Syi/f2pvUcMa2H8P9Z0yqlIkth7Ma5G1mzg==" crossorigin="anonymous"></script>

    <!-- bootstrap-formhelpers fro TimeInputs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js" integrity="sha512-m4xvGpNhCfricSMGJF5c99JBI8UqWdIlSmybVLRPo+LSiB9FHYH73aHzYZ8EdlzKA+s5qyv0yefvkqjU2ErLMg==" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css" integrity="sha512-UPFdMcy+35cR5gyOgX+1vkDEzlMa3ZkZJUdaI1JoqWbH7ubiS/mhGrcM5C72QYouc2EascN3UtUrYnPoUpk+Pg==" crossorigin="anonymous" />

</head>
<body>


<p>Patient cart</p>

<!-- <p><img src=""></p> -->
<p>${patient.id}</p>
<p>${patient.surname}</p>
<p>${patient.name}</p>
<p>${patient.secondname}</p>
<p>${patient.dateOfBirth}</p>
<p>${patient.diagnosis}</p>
<p>${patient.insuranceNumber}</p>
<p>${patient.attendingDoctor}</p>
<p>${patient.status}</p>
<p>Prescribings:</p>
<#if prescribings??>
    <table class="table">
        <tr>
            <td>type</td>
            <td>description</td>
            <td>time</td>
            <td>quantity</td>
        </tr>
    <#list prescribings as prescribing>
        <tr>
            <td>${prescribing.type}</td>
            <td>${prescribing.description}</td>
            <td>${prescribing.time}</td>
            <td>${prescribing.quantity}</td>

            <td><a href="/prescribing/delete/${prescribing.id}">Delete</a></td>

            <form method="post">
            <input type="hidden" name="action" value="edit">


            <input type="hidden" name="id" value="${(prescribing.id)!}">

            <td><input type="text" name="type" placeholder="type" value="${(prescribing.type)!}"></td>
            <td><input type="text" name="description" placeholder="description" value="${(prescribing.description)!}"></td>
            <td><input type="text" name="quantity" placeholder="quantity" value="${(prescribing.quantity)!}"></td>
            <td><input type="text" name="time" placeholder="time" value="${(prescribing.time)!}"></td>

            <td><button type="submit">Save</button></td>
            </form>
        </tr>
    </#list>
    </table>
<#else>
    <p>no prescribing..</p>
</#if>


<button type="button" class="btn btn-primary" onclick="openAddProcedure()">Add procedure</button>
<div id="addProcedureForm" hidden="true">
<form method="post">
<p>
    <input type="hidden" name="action" value="addProcedure">
    <input type="hidden" name="idPatient" value="${patient.id}">
</p>
<p>
    <label>Type</label>
    <input hidden type="text" name="type" value="procedure">
</p>
<p>
    <label>Description</label>
    <input type="text" name="description">
</p>

    <p>
        <label>quantity</label>
        <input type="text" name="quantity">
    </p>
    <p>
        <label>time</label>
        <input type="text" name="time">
    </p>

    <button type="submit">Add</button>
</form>

<div id="input0"></div>
<div class="add" onclick="addPrescribingInput()">+</div>


<p id="basicExample">
    Date start <input type="text" class="date start" />
    Date end <input type="text" class="date end" />
</p>
</div>
<script>
    function openAddProcedure() {
        console.log(document.getElementById("addProcedureForm").getAttribute("hidden"))
        if (document.getElementById("addProcedureForm").getAttribute("hidden")==="true")
        {document.getElementById("addProcedureForm").removeAttribute("hidden")}
        else {document.getElementById("addProcedureForm").setAttribute("hidden", "true")}
        ;
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
    $('#basicExample .date').datepicker({
        'format': 'd.m.yyyy',
        'autoclose': true
    });

    // initialize datepair
    var basicExampleEl = document.getElementById('basicExample');
    var datepair = new Datepair(basicExampleEl);
</script>

</body>
</html>