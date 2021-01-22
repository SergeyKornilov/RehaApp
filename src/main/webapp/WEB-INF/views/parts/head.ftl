
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
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

            <div class="col-xl-5 col-md-4">

                <div class="title">
                    <a class="titleText" href="/">

                    Nazareth-Lazaret
                    Israel Clinic

                    </a>
                </div>

            </div>

            <a href="/event-list">
            <div class="col-1 btn-events">
                <img class="eventsImg" src="/img/events.png">
                <p style="margin-top: 4px">Events</p>
            </div>
            </a>

            <a href="/patient-list">
                <div class="col-2 btn-my-patients">
                    <img src="/img/patients.png">
                    <p class="my-patients-lable">My Patients</p>
                </div>
            </a>

                <div class="col-2 btn-profile">
                    <a href="/profile">
                    <img src="/img/profile.png">
                    </a>
                    <a href="/profile">
                    <lable id="profile-lable" class="profile-lable"><#if user??>
                        ${user.fullName}</#if></lable>
                    </a>
                </div>

            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn-logout" type="submit" value="LoGout"/>
            </form>
        </div>
    </div>
</div>
</body>