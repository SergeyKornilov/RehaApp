<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/css/style-login-page.css">
</head>
<body style="background-image: url(/img/loginPage.png)">
<div class="container">
    <div class="row ">
        <div class="col">
            <div class="login-form">
                <div class="login-page-title">
                    <img style="height: 38px; margin-top: 8px" src="/img/star-of-david.png">
                    <p>Nazareth-Lazareth Israel Clinic</p>
                </div>
                <#if errorMessage??>
                    <div class="error-login-field">Incorrect username or password.</div>
                </#if>

                <div class="inputs-login-form">
                    <form action="/login" method="post">
                        <div><input class="form-control input-username-password" type="text" name="username"
                                    placeholder="Username" autocomplete="off"/></div>
                        <div><input class="form-control input-username-password" type="password" name="password"
                                    placeholder="Password" autocomplete="off"/></div>
                        <div><input class="form-control btn-login" type="submit" value="Login"></div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>