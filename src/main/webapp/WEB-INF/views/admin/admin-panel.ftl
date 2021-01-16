<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Control panel</title>
</head>
<body>
<p>Control panel</p>
    ${message!}
<form action="/registration" method="post">

    <div><label>User name: <input type="text" name="username"/> </label></div>
    <div><label>Password: <input type="password" name="password"/> </label></div>
    <div><label>Full name: <input type="text" name="fullName"/> </label></div>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div><input type="submit" value="Add user"></div>

</form>

</body>
</html>