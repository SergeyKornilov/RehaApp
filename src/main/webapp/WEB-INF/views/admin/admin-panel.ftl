<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Control panel</title>
    <link rel="stylesheet" href="/css/styleAdmin-panel.css">

</head>
<#include "../parts/head.ftl">
<body>


<div class="container">

    <div class="row">
        <div class="col"><h1 style="text-align: center; margin-top: 30px" class="display-4">Admin panel</h1></div>
    </div>
    <div class="row">
        <div class="col-5">

            <h3 style="padding-bottom: 15px; margin-top: 15px">Add user:</h3>
            <p id="duplicateUsernameError" style="color: #e74c3c"></p>

            <form method="post">
                <div hidden><label>ROLE: <input id="role" type="text" name="roles"/></label></div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <input id="postTypeAddUser" type="hidden" name="action" value="addUser">

                <div class="group">
                    <input id="usernameInput" onblur="checkName()" class="dynamic" type="text" required name="username"
                           autocomplete="off">
                    <span class="bar"></span>
                    <label id="placeholderUsernameInput">Username</label>
                    <ul id="errorsUsername" class="input-requirements">
                        <li>At least 3 characters long and max 25</li>
                        <li>Must only contain letters</li>
                    </ul>
                </div>

                <div class="group">
                    <input id="passwordInput" onblur="checkPassword()" class="dynamic" type="password" name="password"
                           required autocomplete="off">
                    <span class="bar"></span>
                    <label>Password</label>
                    <ul id="errorsPassword" class="input-requirements">
                        <li>At least 8 characters long</li>
                        <li>Maximum 225 characters</li>
                    </ul>
                </div>

                <div class="group">
                    <input id="fullNameInput" onblur="checkFullName()" class="dynamic" type="text" name="fullName"
                           required autocomplete="off">
                    <span class="bar"></span>
                    <label>Full name</label>
                    <ul id="errorsFullName" class="input-requirements">
                        <li>At least 5 characters long and max 50</li>
                        <li>Must only contain letters</li>
                    </ul>
                </div>

                <div class="group">
                    <input id="emailInput" onblur="checkEmail()" class="dynamic" type="text" name="email"
                           required autocomplete="off">
                    <span class="bar"></span>
                    <label>Email</label>
                    <ul id="errorsEmail" class="input-requirements">
                        <li>Can`t be empty</li>
                        <li>Enter correct address!</li>
                    </ul>
                </div>

                <div class="input-group mb-3 " style="width: 300px">
                    <select onclick="checkRole()" class="custom-select removeShadow" id="inputGroupSelect01">
                        <option id="selectOptionSetRole" value="1" onclick="setRole(this)">Set Role</option>
                        <option id="selectOptionDoctor" value="2" onclick="setRole(this)">Doctor</option>
                        <option id="selectOptionNurse" value="3" onclick="setRole(this)">Nurse</option>
                        <option id="selectOptionAdmin" value="4" onclick="setRole(this)">Admin</option>
                    </select>
                </div>
                <div><input class="btn-add-clear-user" id="addUserBtn" type="submit" value="Add user" disabled></div>
                <div>
                    <button style="margin-top: 20px" class="btn-add-clear-user" type="button" onclick="clearUserForm()">
                        Clear
                    </button>
                </div>
            </form>

        </div>
        <div class="col-7" style="margin-top: 15px">
            <h3>Users:</h3>
            <table class="table" style="margin-top: 45px">
                <tr>
                    <td>Username</td>
                    <td>Full name</td>
                    <td>Role</td>
                    <td>Email</td>
                </tr>
                <#list users as usr>
                    <#if usr.username??>
                        <tr>
                            <td>${(usr.username)!}</td>
                            <td>${(usr.fullName)!}</td>
                            <td id="role${(usr.username)!}"><#if usr.roles?seq_contains("ROLE_DOCTOR")>Doctor</#if>
                                <#if usr.roles?seq_contains("ROLE_NURSE")>Nurse</#if>
                                <#if usr.roles?seq_contains("ROLE_ADMIN")>Admin</#if>
                            </td>
                            <td>${(usr.email)!}</td>
                            <td><a href="/user/delete/${(usr.username)!}"><img src="/img/delete.png"></a></td>
                            <td>
                                <img style="cursor: pointer;" src="/img/edit.png"
                                     onclick="editUser('${(usr.username)!}', '${(usr.fullName)!}', '${(usr.password)!}', '${(usr.email)!}')">
                            </td>
                        </tr>
                    </#if>
                </#list>
            </table>

        </div>
    </div>
</div>


<script>

    function clearUserForm() {
        document.getElementById("usernameInput").value = "";
        document.getElementById("fullNameInput").value = "";
        document.getElementById("passwordInput").value = "";
        document.getElementById("role").value = "";
        document.getElementById("emailInput").value = "";
        roleValidFlag = false;
        nameValidFlag = false;
        passwordValidFlag = false;
        fullNameValidFlag = false;
        emailInputFlag = false;

        document.getElementById("inputGroupSelect01").value = 1;
        addUserBtn.setAttribute("disabled", "true");
        addUserBtn.value = "Add user";
        document.getElementById("placeholderUsernameInput").innerText = "Username";
        document.getElementById("usernameInput").classList.remove("readonlyInput");

        document.getElementById("usernameInput").removeAttribute("readonly");
        document.getElementById("postTypeAddUser").value = "addUser";

    }

    function editUser(username, fullName, password, email) {


        var role = document.getElementById("role" + username).innerText;

        document.getElementById("usernameInput").setAttribute("readonly", "true");
        document.getElementById("placeholderUsernameInput").innerText = "";
        document.getElementById("usernameInput").classList.add("readonlyInput");

        document.getElementById("postTypeAddUser").value = "editUser";

        addUserBtn.value = "editUser";

        switch (role) {
            case "Doctor" :
                document.getElementById("inputGroupSelect01").value = 2;
                document.getElementById("selectOptionDoctor").click();
                break;
            case "Admin" :
                document.getElementById("inputGroupSelect01").value = 4;
                document.getElementById("selectOptionAdmin").click();
                break;
            case "Nurse" :
                document.getElementById("inputGroupSelect01").value = 3;
                document.getElementById("selectOptionNurse").click();
                break;
        }

        document.getElementById("usernameInput").value = username;
        document.getElementById("fullNameInput").value = fullName;
        document.getElementById("passwordInput").value = password;
        document.getElementById("emailInput").value = email;

        checkFullName();
        checkPassword();
        checkName();
        checkEmail();
        checkRole();

    }


    function setRequired(el) {
        el.setAttribute("required", "true");
    }

    function removeRequired(el) {
        el.removeAttribute("required");
    }

    function setRole(el) {
        switch (el.innerText) {
            case "Set Role":
                document.getElementById("role").value = "";
                break;
            case "Doctor":
                document.getElementById("role").value = "ROLE_DOCTOR";
                break;
            case "Nurse":
                document.getElementById("role").value = "ROLE_NURSE";
                break;
            case "Admin":
                document.getElementById("role").value = "ROLE_ADMIN";
        }
    }


</script>
<script>
    <#if errors??>
    <#list errors as error>
    <#if error = "username">
    document.getElementById("usernameInput").classList.add("invalidInput");
    </#if>
    <#if error = "password">
    document.getElementById("passwordInput").classList.add("invalidInput");
    </#if>
    <#if error = "password">
    document.getElementById("fullNameInput").classList.add("invalidInput");
    </#if>
    <#if error = "duplicate username">
    console.log("duplicate username error");
    document.getElementById("duplicateUsernameError").innerText = "User with the same username exists!";
    </#if>
    </#list>
    </#if>
</script>

<script src="/js/validation.js" type="text/javascript"></script>

</body>
</html>