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

    <!-- dateFormat -->
    <script src="https://momentjs.com/downloads/moment-with-locales.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/css/styleEvent-list.css">

</head>
<#include "../parts/head.ftl">
<body onload="hideClosed()">


<div class="container">
    <div class="row">
        <div class="col"><h1 style="text-align: center" class="display-4">Events</h1></div>
    </div>
    <div class="row">
        <div class="col-8">
            <input class="form-control" style="margin-bottom: 30px" type="text" placeholder="Search" id="search-text"
                   onkeyup="eventsTableSearch()">
        </div>
        <div class="col-2">
            <input onchange="filterDate()" class="form-control" id="inputDateOfBirth" type="date" name="dateOfBirth"
                   placeholder="age" value="${(patient.dateOfBirth?string('yyyy-MM-dd'))!}"/>
        </div>
        <div class="col-1">
            <button id="btn-hideClosed" class="btn-hide-close" hidden type="button" onclick="hideClosed()">Hide closed
            </button>
            <button id="btn-displayClosed" class="btn-hide-close" type="button" onclick="displayClosed()">Display
                closed
            </button>
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

                <tbody>
                <#list events?sort_by("date") as event>
                    <tr>
                        <td>${(event.date?datetime?string('dd.MM.yyyy'))!}</td>
                        <td>${(event.time)!}</td>
                        <td>${(event.prescribing.type)!}</td>
                        <td>${(event.prescribing.patient.name)!}</td>
                        <td>${(event.prescribing.name)!}</td>
                        <td>${(event.status)!} <#if event.reason??>-</#if> ${(event.reason)!}</td>

                        <td><#if event.status = "open"><a href="/event/done/${event.id}">
                                    <button class="btn-done" type="button">Done</button> </a></#if>
                            <#if event.status = "open">
                                <button onclick="setEventIdPostForm(${(event.id)!})" class="btn-done open"
                                        style="background: #e74c3c" type="button">Cancel</button></#if>
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
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" class="btn-done" value="Event cancel"
                           style="background: #e74c3c; margin-top: 20px">
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="backpopup"></div>

<script src="/js/event-list.js"></script>


</body>
</html>