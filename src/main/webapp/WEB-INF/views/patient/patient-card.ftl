<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <table>
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

<p>Add prescribing</p>
<form method="post">
<p>
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="idPatient" value="${patient.id}">
</p>
<p>
    <label>Type</label>
    <input type="text" name="type">
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

</body>
</html>