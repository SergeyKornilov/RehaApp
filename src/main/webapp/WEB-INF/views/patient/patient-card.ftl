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
        </tr>
        <tr>
            <td>description</td>
        </tr>
    <#list prescribings as prescribing>
        <tr>
            <td>${prescribing.type}</td>
        </tr>
        <tr>
            <td>${prescribing.description}</td>
        </tr>
    </#list>
    </table>
<#else>
    <p>no prescribing..</p>
</#if>

<p><a href="">Add prescribing</a></p>
<form method="post">
<p>
    <input type="hidden" name="id" value="${patient.id}">
</p>
<p>
    <label>Type</label>
    <input type="text" name="type">
</p>
<p>
    <label>Description</label>
    <input type="text" name="description">
</p>
    <button type="submit">Add</button>
</form>

</body>
</html>