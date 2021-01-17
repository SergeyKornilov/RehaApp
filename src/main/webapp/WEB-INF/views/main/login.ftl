<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>LoginPage</p>

<form action="/login" method="post">
    <div><label>User name: <input type="text" name="username"/> </label></div>
    <div><label>Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sing in"></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</body>
</html>