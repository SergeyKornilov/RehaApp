<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/styleEvent-list.css">


    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/0.3.4/sockjs.min.js"
    integrity="sha512-Tp59ajolL5b7bHPv8WsEfdNXqA0bq2NohJ9jK9yy+HRCvwGpjdpc8ay3F2KtbU9dC22QIOS7ek1n2Vep3ohqtQ=="
    crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"
    integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g=="
    crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="https://momentjs.com/downloads/moment.js"></script>

</head>
<script>
    var app = angular.module("eventList", []);

    var a = "${_csrf.parameterName}";
    var b = "${_csrf.token}";
    console.log(a);
    console.log(b);
    app.controller("eventListController", mainCtrl);



    // app.controller("MyCtrl", function ($scope) {
    //     $scope.dt = '2015-09-21 18:30:00';
    // });
    //
    app.constant("moment", moment);

    app.filter('formatDate', function(dateFilter, moment) {
        var formattedDate = '';
        return function(dt) {

            formattedDate = dateFilter(new moment(dt)).format('DD.MM.yyyy');
            return formattedDate;                  //.add('5', 'd')
        }

    });



    // app.controller("ctrl", function($scope, moment) {
    //     $scope.date = new moment().format("D/MMM/YYYY");
    //     var dat1 = new moment("2021-02-04 00:00:00.0");
    //     $scope.date3 = dat1.add('5', 'd').format('MMMM Do YYYY, h:mm:ss a');
    // });


    function mainCtrl ($scope, $http) {
            console.log("test hello");
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            //отмена события
            $scope.setEventIdPostForm = function(eventId) {
                console.log(eventId);
                $scope.data.id = eventId;
                    $('.popup-window').popup();
                    console.log("cliclclclcl");


                $('.backpopup,.close').click(function () { //click on shadow or X
                    $('.popup-window').fadeOut();
                    $('.backpopup').fadeOut();
                })
            };


            //TEST POST
            $scope.data = {};

            $scope.submitForm = function (form) {
                console.log(form);


                // $http.post('/rest/cancel', $scope.message)
                //
                //     .success(function (data) {
                //         console.log('data:', data);
                //     })
                //
                //     .error(function (data) {
                //         console.log('Error:', data);
                //     });

                console.log(a);
                console.log(b);


                var request = $http({
                    method: "post",
                    url: "http://localhost:8080/rest/cancel",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    transformRequest: function(){return a+"="+b;},
                    data: $scope.data
                    });


            };









            //event done
            $scope.setEventDone = function(eventId) {
                $http.get('/event/done/' + eventId);
            };

            //check status
            $scope.checkStatusEvent = function(eventStatus) {
                if(eventStatus === "open") {
                    return true;
                } else {
                    return false;
                }
            };


            $scope.events = [];

            $http.get('http://localhost:8080/test/get-events').then(function (data) {
                $scope.events = data.data;
                console.log($scope.events);
                console.log("Hello from MainCtrl");

            });

            stompClient.subscribe('/topic/messages', function(messageOutput) {

            $http.get('http://localhost:8080/test/get-events').then(function (data) {
                $scope.events = data.data;
                console.log($scope.events);
                console.log("Hello from MainCtrl");

            });
            });
        });
    }

    $.fn.popup = function () { 	//pop-up window open function
        console.log("pop-up window open function");
        this.css('position', 'absolute').fadeIn();
        this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px');
        this.css('left', ($(window).width() - this.width()) / 2 + 'px');
        $('.backpopup').fadeIn();
    };


</script>
<body ng-app="eventList" ng-controller="eventListController">

<#include "../parts/head.ftl">



<div class="container">
    <div class="row">
        <div class="col"><h1 style="text-align: center" class="display-4">Events</h1></div>
    </div>


    <div class="row">
        <div class="col-8">
            <input  ng-model="search" class="form-control" style="margin-bottom: 30px" type="text" placeholder="Search" id="search-text"
                   >
        </div>
        <div class="col-2">
            <input class="form-control" id="inputDateOfBirth" type="date" name="dateOfBirth"
                   placeholder="age" />
        </div>
        <div class="col-1">
            <button id="btn-hideClosed" class="btn-hide-close" hidden type="button" >Hide closed
            </button>
            <button id="btn-displayClosed" class="btn-hide-close" type="button">Display
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
<#--                <#list events?sort_by("date") as event>-->
                    <tr ng-repeat="ev in events | filter:search">
                        <td>{{ev.date | formatDate: ev.date:'dd.MM.yyyy'}}</td>
                        <td>{{ev.time}}</td>
                        <td>{{ev.type}}</td>
                        <td>{{ev.patient}}</td>
                        <td>{{ev.name}}</td>
                        <td>{{ev.status}}</td>
                     <td>
                        <#--<a href='/event/done/{{ev.id}}'>-->
                                    <button ng-if='checkStatusEvent(ev.status)' ng-click='setEventDone(ev.id)' class="btn-done" type="button">Done</button>
<#--                            </a>-->
                            <button ng-if='checkStatusEvent(ev.status)' ng-click='setEventIdPostForm(ev.id)' class="btn-done open"
                                    style="background: #e74c3c" type="button">Cancel</button>
                        </td>
                    </tr>

                </tbody>
            </table>

        </div>

    </div>

</div>


<#--TEST POST-->

<div class="popup-window">
    <p class="close">x</p>

    <form method="post" name="loginForm" ng-submit="submitForm(loginForm)">
        <table>
            <tr>
                <td>
                    <p style="margin-left:  100px; margin-right: 100px;">Enter the reason for canceling the event</p>
                </td>
            </tr>
            <tr>
                <td>
                    <input ng-model="data.reason" style="margin-top: 20px" class="form-control" type="text"/>
                    <input ng-model="data.id" id="postInputId" hidden type="text">
<#--                    <input type="hidden" ng-model="message._csrf" value="${_csrf.token}"/>-->
                    <input name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
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



<#--<div class="popup-window">-->
<#--    <p class="close">x</p>-->

<#--    <form method="post" >-->
<#--        <table>-->
<#--            <tr>-->
<#--                <td>-->
<#--                    <p style="margin-left:  100px; margin-right: 100px;">Enter the reason for canceling the event</p>-->
<#--                </td>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td>-->
<#--                    <input ng-model="reason" name="reason" style="margin-top: 20px" class="form-control" type="text"/>-->
<#--                    <input ng-model="id" id="postInputId" name="id" hidden type="text">-->
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--                </td>-->
<#--            </tr>-->
<#--            <tr>-->
<#--                <td colspan="2" align="center">-->
<#--                    <input type="submit" class="btn-done" value="Event cancel"-->
<#--                           style="background: #e74c3c; margin-top: 20px">-->
<#--                </td>-->
<#--            </tr>-->
<#--        </table>-->
<#--    </form>-->
<#--</div>-->

<div class="backpopup"></div>



<#--<table>-->
<#--    <tr>-->
<#--        <td>Name</td>-->
<#--        <td>Patient</td>-->
<#--        <td>Status</td>-->
<#--    </tr>-->
<#--    <tr ng-repeat="ev in events">-->
<#--        <td>{{ev.name}}</td>-->
<#--        <td>{{ev.patient}}</td>-->
<#--        <td>{{ev.status}}</td>-->

<#--    </tr>-->
<#--</table>-->

<#--<div ng-controller="ctrl">-->
<#--    {{date}}-->
<#--    </br>-->
<#--    </br>-->
<#--    {{date3}}-->
<#--</div>-->



<script>

</script>
</body>
</html>