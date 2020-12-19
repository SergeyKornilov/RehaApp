<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<#if add>
    <p>add</p>
    <#assign formAction>/patient-add</#assign>
    <#else>
    <p>edit</p>
        <#assign formAction>/patient-edit</#assign>
</#if>


<form action=${formAction} method="post">
    <#if !add>
    <p>
        <input type="hidden" name="id" value="${(patient.id)!}">
    </p>
    </#if>
    <p>
        <input type="text" name="surname" placeholder="surname" value="${(patient.surname)!}"/>
    </p>
    <p>
        <input type="text" name="name" placeholder="name" value="${(patient.name)!}"/>
    </p>
    <p>
        <input type="text" name="secondname" placeholder="secondname" value="${(patient.secondname)!}"/>
    </p>
    <p>
        <input type="date" name="dateOfBirth" placeholder="age" value="${(patient.dateOfBirth)!}"/>
    </p>
    <p>
        <input type="text" name="diagnosis" placeholder="diagnosis" value="${(patient.diagnosis)!}"/>
    </p>
    <p>
        <input type="text" name="insuranceNumber" placeholder="insurance Number" value="${(patient.insuranceNumber)!}"/>
    </p>
    <p>
        <input type="text" name="attendingDoctor" placeholder="attending Doctor" value="${(patient.attendingDoctor)!}"/>
    </p>
    <p>
        <input type="text" name="status" placeholder="status" value="${(patient.status)!}"/>
    </p>
    <p>
    <#if add>
        <button type="submit">add</button>
    <#else>
        <button type="submit">save</button>
    </#if>
    </p>
</form>

</body>
</html>