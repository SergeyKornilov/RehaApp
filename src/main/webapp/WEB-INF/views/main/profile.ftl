<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<#include "../parts/head.ftl">
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <table style="width: 500px; margin-left: auto; margin-right: auto">
                <h1 style="text-align: center; margin-top: 30px; margin-bottom: 30px" class="display-4">My profile</h1>

                <tr>
                    <td>Username</td>
                    <td style="font-weight: 600">${user.username}</td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td style="font-weight: 600">${user.fullName}</td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td style="font-weight: 600"><#list user.roles as role>
                            ${role}
                        </#list>
                    </td>
                </tr>
            </table>



        </div>
    </div>

</div>

</body>
</html>