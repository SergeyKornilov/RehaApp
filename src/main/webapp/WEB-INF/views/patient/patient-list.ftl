<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>Hello</p>
<#list patients as patient>
    <p>${patient.toString()}</p>
</#list>
</body>
</html>