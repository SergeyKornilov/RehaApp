<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .user-avatar-back{
            /*border-radius: 100px;*/
            width: 350px;
            height: 350px;

            /*background: #FFFFFF;*/
            box-shadow: 3px 3px 7px rgba(0, 0, 0, 0.25);
            border-radius: 10px;
            padding: 40px;

            display: flex;
            justify-content: center;
            margin-top: 100px;
            margin-left: 30px;

        }
        .user-avatar-default{
            margin-left: auto;
            margin-right: auto;
            width: 150px;
            height: 150px;
            align-self:center;
        }
        .user-avatar-img{
            margin-left: auto;
            margin-right: auto;
            max-width: 250px;
            max-height: 250px;
            align-self:center;
            border-radius: 10px;
        }
        .display-upload-photo-btn{
            width: 150px;
            height: 35px;
            color: white;
            box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -webkit-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -moz-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            background: linear-gradient(103.31deg, #0C8AFE -20.48%, #F806FD 118.31%);
            border-radius: 4.9359px;
            border: none;
            margin-top: 20px;

        }
        .myprofile-back{
            margin-right: auto;
            margin-left: auto;
            background: rgba(135, 234, 254, 0.3);
            border-radius: 10px;
            margin-top: 100px;
            padding-right: 90px;
            padding-left: 90px;
            padding-bottom: 50px;
            vertical-align: auto;
            position: relative;
        }
        .upload-photo-form{
            margin-top: 20px;
        }
        .upload-btn {
            background: #4AA8FF;
            border: none;
            border-radius: 5px;
            color: white;
            width: 105px;
            height: 38px;
            margin-top: 15px;
        }
        .group-span-filestyle{
            background: #4AA8FF;
            color: white !important;
        }
        .error{
            color: #e74c3c;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js"
            integrity="sha512-HfRdzrvve5p31VKjxBhIaDhBqreRXt4SX3i3Iv7bhuoeJY47gJtFTRWKUpjk8RUkLtKZUhf87ONcKONAROhvIw=="
            crossorigin="anonymous"></script>
</head>
<#include "../parts/head.ftl">
<body>
<div class="container">
    <div class="row">
        <div class="col-6">
            <div class="myprofile-back">
            <table style="width: 500px; margin-left: auto; margin-right: auto">
                <h1 style="text-align: center; margin-bottom: 30px" class="display-4">My profile</h1>

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
                            <#if role == "ROLE_ADMIN">Admin</#if>
                            <#if role == "ROLE_NURSE">Nurse</#if>
                            <#if role == "ROLE_DOCTOR">Doctor</#if>
                        </#list>
                    </td>
                </tr>
            </table>
            </div>
            <button class="display-upload-photo-btn" type="button" onclick="openUploadPhoto()">Upload photo</button>

            <form class="upload-photo-form" id="uploadPhotoForm" hidden="true" action="/profile?${_csrf.parameterName}=${_csrf.token}"
                    method="post"enctype="multipart/form-data">

                <input class="filestyle" type="file" name="file" accept="image/jpeg,image/png,image/jpg">
                <button class="upload-btn" type="submit">Upload</button>
            </form>
            <div class="error">${(error)!}</div>




        </div>



        <div class="col-6">
            <div>
                <div class="user-avatar-back">
                <#if user.imgName??>
                    <img class="user-avatar-img" src="/upload/${user.imgName}">
                    <#else>
                        <img class="user-avatar-default" src="/img/userdefault.png">
                </#if>
                </div>


            </div>
        </div>
    </div>

</div>

<script>
    function openUploadPhoto() {
        var uploadForm = document.getElementById("uploadPhotoForm");
        if(uploadForm.getAttribute("hidden") === "true") {
            uploadForm.removeAttribute("hidden");
        } else{
            uploadForm.setAttribute("hidden", "true");
        }
    }

    $(":file").filestyle('btnClass', 'group-span-filestyle');

</script>
</body>
</html>