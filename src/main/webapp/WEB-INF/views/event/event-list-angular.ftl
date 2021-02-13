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

    var statusFilterValue = ["open"];
    var dateFilterValue = '';

    <#list user.roles as role>
        var roleUser = "${role}";
    </#list>

    var a = "${_csrf.parameterName}";
    var b = "${_csrf.token}";

    app.controller("eventListController", mainCtrl);

    app.filter('statusFilter', function () {
        return function (events) {
            var opened = [];
            for(i = 0; i < events.length; i++){
                if (statusFilterValue.indexOf(events[i].status) > -1){
                    opened.push(events[i]);
                }
            }
            return opened;
        }
    });

    app.constant("moment", moment);

    app.filter('formatDate', function(dateFilter, moment) {
        var formattedDate = '';
        return function(dt) {

            formattedDate = dateFilter(new moment(dt)).format('DD.MM.yyyy');
            return formattedDate;                  //.add('5', 'd')
        }

    });

    app.filter('filterByDate', function() {
        return function (events) {
            for (i = 0; i < events.length; i++) {
            }

            if (dateFilterValue.length > 12) {
                var eventsOnDate = [];
                for (i = 0; i < events.length; i++) {
                    if (events[i].date === dateFilterValue) {
                        eventsOnDate.push(events[i]);
                    }
                }
                return eventsOnDate;
            } else return events;
        }
    });



    function mainCtrl ($scope, $http) {

            $scope.dateChangeAngular = function() {

                dateFilterValue = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("YYYY-MM-DD 00:00:00.0");

            };



        $scope.displayClosedAngular = function () {
            statusFilterValue.push('close');
            statusFilterValue.push('Cancel');
            document.getElementById('btn-displayClosed').setAttribute('hidden', 'true');
            document.getElementById('btn-hideClosed').removeAttribute('hidden');
        };
        $scope.hideClosedAngular = function() {
            for(var i = statusFilterValue.length - 1; i >= 0; i--) {
                if(statusFilterValue[i] === 'close' || statusFilterValue[i] === 'Cancel') {
                    statusFilterValue.splice(i, 1);
                }

                document.getElementById('btn-displayClosed').removeAttribute('hidden');
                document.getElementById('btn-hideClosed').setAttribute('hidden', 'true');
            }
        };



        var socket = new SockJS('/ang');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);


            //отмена события
            $scope.setEventIdPostForm = function(eventId) {
                $scope.data.id = eventId;
                    $('.popup-window').popup();


                $('.backpopup,.close, .cancelEvent').click(function () { //click on shadow or X
                    $('.popup-window').fadeOut();
                    $('.backpopup').fadeOut();
                })
            };

            //TEST POST
            $scope.data = {};

            $scope.submitForm = function (form) {
                var request = $http({
                    method: "post",
                    url: "http://localhost:8080/rest/cancel",
                    headers: {'Content-Type' : 'application/json',
                                'X-CSRF-TOKEN' : b},
                    data: $scope.data
                });
            };

            //event done
            $scope.setEventDone = function(eventId, eventDate) {

                var event = new Date(eventDate);
                event.setDate(event.getDate() + 1);
                var today = new Date();

                if(event.getTime() > today.getTime() || roleUser !== "ROLE_NURSE") {
                    $http.get('/event/done/' + eventId);
                } else {
                    alert("You can't change events on past date");
                }
            };

            //check status
            $scope.checkStatusEvent = function(eventStatus) {
                return eventStatus === "open";
            };
            $scope.checkStatusCancel = function(eventStatus) {
                return eventStatus === "Cancel";
            };

            $scope.events = [];

            $http.get('http://localhost:8080/get-all-events').then(function (data) {
                $scope.events = data.data;
            });

            stompClient.subscribe('/topic/messages', function(messageOutput) {

                console.log(messageOutput.body !== "update all");

                var idEvent = messageOutput.body;
                console.log(idEvent);

                if(messageOutput.body !== "update all"){
                    $http.get('http://localhost:8080/get-event/' + idEvent).then(function (data) {
                        for(i = 0; i < $scope.events.length; i ++){
                            var idid = $scope.events[i].id;
                            if (idid === idEvent){
                                $scope.events[i] = data.data[0];
                            }
                        }
                    });
                } else {
                    $http.get('http://localhost:8080/get-all-events').then(function (data) {
                        $scope.events = data.data;
                    });
                }
            });
        });

    }

    $.fn.popup = function () { 	//pop-up window open function
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
        <div class="col"><h1 style="text-align: center; margin-top: 30px" class="display-4">Events</h1></div>
    </div>


    <div class="row">
        <div class="col-8">
            <input  ng-model="search" class="form-control" style="margin-bottom: 30px" type="text" placeholder="Search" id="search-text"
                   >
        </div>
        <div class="col-2">
            <input ng-change="dateChangeAngular()" ng-model="inputDateValue" class="form-control" id="inputDateOfBirth" type="date" name="dateOfBirth"
                   placeholder="age" />
        </div>
        <div class="col-1">
            <button ng-click="hideClosedAngular()" id="btn-hideClosed" class="btn-hide-close" hidden type="button" >Hide closed
            </button>
            <button ng-click="displayClosedAngular()" id="btn-displayClosed" class="btn-hide-close" type="button">Display
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
                    <td style="min-width: 100px">Status</td>

                    <td></td>
                </tr>
                </thead>

                <tbody>
<#--                <#list events?sort_by("date") as event>-->
                    <tr ng-repeat="ev in events | orderBy: 'date' | filter:search | statusFilter | filterByDate track by $index">
                        <td>{{ev.date | formatDate: ev.date:'dd.MM.yyyy'}}</td>
                        <td>{{ev.time}}</td>
                        <td>{{ev.type}}</td>
                        <td>{{ev.patient}}</td>
                        <td>{{ev.name}}</td>
                        <td>{{ev.status}} <label ng-if="checkStatusCancel(ev.status)"> - {{ev.reason}}</label>
                        </td>
                     <td>
                        <#--<a href='/event/done/{{ev.id}}'>-->
                                    <button ng-if='checkStatusEvent(ev.status)' ng-click='setEventDone(ev.id, ev.date)' class="btn-done" type="button">Done</button>
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

                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" class="btn-done cancelEvent" value="Event cancel"
                           style="background: #e74c3c; margin-top: 20px">
                </td>
            </tr>
        </table>
    </form>
</div>




<div class="backpopup"></div>





<script>

</script>
</body>
</html>