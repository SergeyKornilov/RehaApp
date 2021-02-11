<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/styleHeader.css">
</head>
<body>
<div class="nav-head">
    <div class="container">
        <div class="row">

            <div class="col-1">
                <a href="/">
                    <div class="logo"></div>
                </a>
            </div>

            <#list user.roles as role>
                <#if role = "ROLE_ADMIN">
                    <#assign x = 4>
                    <#assign userRole = role>
                </#if>
                <#if role = "ROLE_DOCTOR">
                    <#assign x = 5>
                    <#assign userRole = role>
                </#if>
                <#if role = "ROLE_NURSE">
                    <#assign x = 6>
                    <#assign userRole = role>
                </#if>

            </#list>


            <div class="col-xl-${x} col-md-4">

                <div class="title">
                    <a class="titleText" href="/">

                        Nazareth-Lazaret
                        Israel Clinic

                    </a>
                </div>

            </div>

            <#if userRole = "ROLE_ADMIN">
                <a href="/admin">
                    <div class="col-1 btn-events">
                        <img class="eventsImg" src="/img/admin.png">
                        <p style="margin-top: 4px">Admin</p>
                    </div>
                </a>
            </#if>
            <#if userRole = "ROLE_ADMIN" || userRole = "ROLE_NURSE" || userRole = "ROLE_DOCTOR">
                <a href="/event-list">
                    <div class="col-2 btn-events">
                        <img class="eventsImg" src="/img/events.png">
                        <p style="margin-top: 4px">Events</p>
                    </div>
                </a>
            </#if>

            <#if userRole = "ROLE_ADMIN" || userRole = "ROLE_DOCTOR">
                <a href="/patient-list">
                    <div class="col-2 btn-my-patients">
                        <img src="/img/patients.png">
                        <p class="my-patients-lable">My Patients</p>
                    </div>
                </a>
            </#if>
            <div class="col-2 btn-profile">
                <#if user.imgName??>
                    <img class="custom-avatar" src="/upload/${user.imgName}">
                <#else>
                <a href="/profile">
                    <img src="/img/profile.png">
                </a>
                </#if>
                <a href="/profile">
                    <lable id="profile-lable" class="profile-lable"><#if user??>
                            ${user.fullName}</#if></lable>
                </a>
            </div>
            <div class="col-1">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input class="btn-logout" type="submit" value="LoGout"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>