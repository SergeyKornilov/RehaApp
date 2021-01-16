<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>Hello</p>
<p><a href="/patient-list">Patients list</a></p>
<p><a href="/event-list">Events list</a></p>




<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="submit" value="Sign Out"/>
</form>



<img src="img/cat.jpg">
</body>
</html>