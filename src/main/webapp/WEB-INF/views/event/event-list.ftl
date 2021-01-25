<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <!-- Jquery -->


    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>

<#--    <!-- tablesorter &ndash;&gt;-->
<#--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js" integrity="sha512-qzgd5cYSZcosqpzpn7zF2ZId8f/8CHmFKZ8j7mU4OUXTNRd5g+ZHBPsgKEwoqxCtdQvExE5LprwwPAgoicguNg==" crossorigin="anonymous"></script>-->



    <!-- dateFormat -->
    <script src="https://momentjs.com/downloads/moment-with-locales.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/css/styleEvent-list.css">
    <style type="text/css">
        .popup-window{
            display: none;
            box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -webkit-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -moz-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            padding: 20px;
            background: white;
            z-index: 500;
            -webkit-border-radius: 5px!important;
            -moz-border-radius: 5px!important;
            border-radius: 5px!important;
        }
        .open{

            cursor: pointer;
        }
        .backpopup{
            display:none;
            width: 100%;
            height: 100%;
            position: fixed;
            background: rgb(105, 105, 105);
            opacity: 0.7;
            top: 0;
            left: 0;
            z-index: 400;
            cursor: pointer;
        }
        .close{
        float: right;
            cursor: pointer;
            right: 5px;
            top: 0px;
            position: absolute;
            padding: 4px;
        }
        .btn-hide-close{
            width: 150px;
            height: 35px;
            color: white;
            box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -webkit-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            -moz-box-shadow: 0px 0px 4px 0px rgb(70, 70, 70);
            background: linear-gradient(103.31deg, #0C8AFE -20.48%, #F806FD 118.31%);
            border-radius: 4.9359px;
            border: none;
        }


    </style>








</head>
<#include "../parts/head.ftl">
<body onload="hideClosed()">




<div class="container">
    <div class="row">
        <div class="col"> <h1 style="text-align: center" class="display-4">Events</h1></div>
    </div>
    <div class="row">
        <div class="col-8">
            <input class="form-control" style="margin-bottom: 30px" type="text" placeholder="Search" id="search-text" onkeyup="eventsTableSearch()">
        </div>
        <div class="col-2">
            <input onchange="filterDate()" class="form-control" id="inputDateOfBirth" type="date" name="dateOfBirth" placeholder="age" value="${(patient.dateOfBirth?string('yyyy-MM-dd'))!}"/>
        </div>
        <div class="col-1">
            <button id="btn-hideClosed" class="btn-hide-close" hidden type="button" onclick="hideClosed()">Hide closed</button>
            <button id="btn-displayClosed" class = "btn-hide-close" type="button" onclick="displayClosed()">Display closed</button>
        </div>


    </div>

<div class="row">
    <div class="col-12">
<table id="tableListEvents" class="table sortable" style="background: #E0FDFF;">
    <thead>
    <tr>
        <td>Date</td>
        <td>Time</td>
        <td>Type</td>
        <td>Patient Name</td>
        <td>Prescribing description</td>
        <td>Status</td>

        <td></td>
    </tr>
    </thead>
<#--    <#assign ls = events?sort>-->

<#--    <#list ls as event>-->
    <tbody>

<#--<#list events as event>-->
    <#list events?sort_by("date") as event>

        <tr>
            <td>${(event.date?datetime?string('dd.MM.yyyy'))!}</td>
            <td>${(event.time)!}</td>
            <td>${(event.prescribing.type)!}</td>
            <td>${(event.prescribing.patient.name)!}</td>
            <td>${(event.prescribing.name)!}</td>
            <td>${(event.status)!} <#if event.reason??>-</#if> ${(event.reason)!}</td>

            <td><#if event.status = "open"><a href="/event/done/${event.id}"><button class="btn-done" type="button">Done</button> </a></#if>
                <#if event.status = "open"><button onclick="setEventIdPostForm(${(event.id)!})" class="btn-done open" style="background: #e74c3c" type="button">Cancel</button></#if>
            </td>
        </tr>
</#list>
    </tbody>
</table>

</div>

</div>

</div>



<div class="popup-window">
    <p class="close">x</p>

    <form method="post">
    <table>
        <tr>
            <td>
                <p style="margin-left:  100px; margin-right: 100px;">Enter the reason for canceling the event</p>
            </td>
        </tr>
        <tr>
            <td>
                <input name="reason" style="margin-top: 20px" class="form-control" type="text"/>
                <input id="postInputId" name="id" hidden type="text">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" class="btn-done" value="Event cancel" style="background: #e74c3c; margin-top: 20px">
            </td>
        </tr>
    </table>
    </form>
</div>

<div class="backpopup"></div>


<script type="text/javascript">
    $.fn.popup = function() { 	//функция для открытия всплывающего окна:
        this.css('position', 'absolute').fadeIn();	//задаем абсолютное позиционирование и эффект открытия
        //махинации с положением сверху:учитывается высота самого блока, экрана и позиции на странице:
        this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px');
        //слева задается отступ, учитывается ширина самого блока и половина ширины экрана
        this.css('left', ($(window).width() - this.width()) / 2  + 'px');
        //открываем тень с эффектом:
        $('.backpopup').fadeIn();
    }
    $(document).ready(function(){	//при загрузке страницы:
        $('.open').click(function(){	//событие клик на нашу ссылку
            $('.popup-window').popup();	//запускаем функцию на наш блок с формой
        });
        $('.backpopup,.close').click(function(){ //событие клик на тень и крестик - закрываем окно и тень:
            $('.popup-window').fadeOut();
            $('.backpopup').fadeOut();
        });
    });
</script>






<script>
 //   var date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");

    function setEventIdPostForm(id) {
        document.getElementById("postInputId").value = id;
    }
    function hideClosed() {

        var table = document.getElementById("tableListEvents");
      //  var date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");


        for(var x = 1; x < table.rows.length; x ++){

            document.getElementById("btn-hideClosed").setAttribute("hidden", "true");
            document.getElementById("btn-displayClosed").removeAttribute("hidden");


            if (document.getElementById("inputDateOfBirth").value.length === 0) {

                if (table.rows[x].cells[5].innerText === "close" ||
                    table.rows[x].cells[5].innerText.includes("Cancel")) {

                    table.rows[x].style.display = "none";

                    //       table.rows[x].style.removeProperty("visibility");
                    //   style.visibility = "inherit";
                }
            } else {

                date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");
                if (table.rows[x].cells[5].innerText === "close" &&
                    table.rows[x].cells[0].innerText === date
                    ||
                    table.rows[x].cells[5].innerText.includes("Cancel") &&
                    table.rows[x].cells[0].innerText === date
                ) {
                    table.rows[x].style.display = "none";

                    //       table.rows[x].style.removeProperty("visibility");
                    //   style.visibility = "inherit";
                }
            }

        }
    }

    function displayClosed() {
      //  var date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");
        var table = document.getElementById("tableListEvents");


        for(var x = 1; x < table.rows.length; x ++) {
            document.getElementById("btn-displayClosed").setAttribute("hidden", "true");
            document.getElementById("btn-hideClosed").removeAttribute("hidden");

            if (document.getElementById("inputDateOfBirth").value.length === 0) {

                if (table.rows[x].cells[5].innerText === "close  " ||
                    table.rows[x].cells[5].innerText.includes("Cancel")) {

                    table.rows[x].style.display = "";
                    //        table.rows[x].style.visibility = "collapse";
                }
            } else{
                date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");

                if (table.rows[x].cells[5].innerText === "close  " &&
                    table.rows[x].cells[0].innerText === date

                    ||
                    table.rows[x].cells[5].innerText.includes("cancel") &&
                    table.rows[x].cells[0].innerText === date

                ) {

                    table.rows[x].style.display = "";
                    //        table.rows[x].style.visibility = "collapse";
                }


            }




        }

    }

    function eventsTableSearch() {


        var phrase = document.getElementById('search-text');
        var table = document.getElementById('tableListEvents');
        var regPhrase = new RegExp(phrase.value, 'i');
        var flag = false;
        for (var i = 1; i < table.rows.length; i++) {
            flag = false;
            for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
                flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
                if (flag) break;
            }
            if (flag) {
               // table.rows[i].style.display = "";
                table.rows[i].style.visibility = " visible";
            } else {
             //   table.rows[i].style.display = "none";
                table.rows[i].style.visibility = "collapse";
            }

        }
    }

    function filterDate() {
        var table = document.getElementById("tableListEvents");
        var closedDisplay = document.getElementById("btn-displayClosed").hidden;


        if (document.getElementById("inputDateOfBirth").value.length != 0) {



            date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");

                for(var x = 1; x < table.rows.length; x ++){

                    table.rows[x].style.display = "";

                document.getElementById("btn-hideClosed").setAttribute("hidden", "true");
                document.getElementById("btn-displayClosed").removeAttribute("hidden");

                if (table.rows[x].cells[0].innerText === date &&
                    table.rows[x].cells[5].innerText === "open"
                    // table.rows[x].cells[0].innerText === date &&
                    // table.rows[x].cells[5].innerText.includes("cancel")

                ){

                    table.rows[x].style.display = "";

                    //       table.rows[x].style.removeProperty("visibility");
                    //   style.visibility = "inherit";
                } else {
                    table.rows[x].style.display = "none";
                }
            }
        } else{

            for(var h = 1; h < table.rows.length; h ++) {

                if (closedDisplay){
                    table.rows[h].style.display = "";
                } else {
                    if (table.rows[h].cells[5].innerText.trim() === "open") table.rows[h].style.display ="";
                }



            }


        }
    }






</script>

<#--<script>-->
<#--    $(document).ready(function() {-->
<#--        $("#tableListEvents").tablesorter();-->
<#--    });-->
<#--</script>-->

</body>
</html>