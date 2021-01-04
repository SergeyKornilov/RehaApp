<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        </style>
</head>
<body>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<!-- bootstrap-formhelpers fro TimeInputs -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js" integrity="sha512-m4xvGpNhCfricSMGJF5c99JBI8UqWdIlSmybVLRPo+LSiB9FHYH73aHzYZ8EdlzKA+s5qyv0yefvkqjU2ErLMg==" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css" integrity="sha512-UPFdMcy+35cR5gyOgX+1vkDEzlMa3ZkZJUdaI1JoqWbH7ubiS/mhGrcM5C72QYouc2EascN3UtUrYnPoUpk+Pg==" crossorigin="anonymous" />


<!-- include input widgets; this is independent of Datepair.js -->
<!--
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.css" integrity="sha512-GgUcFJ5lgRdt/8m5A0d0qEnsoi8cDoF0d6q+RirBPtL423Qsj5cI9OxQ5hWvPi5jjvTLM/YhaaFuIeWCLi6lyQ==" crossorigin="anonymous" />
-->

<!--  css for Date Start/End-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />

<!--  js for Date Start/End  -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script><script type="text/javascript" src="jquery.timepicker.js"></script>

<!--  js for Date Start/End - connect fields -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datepair.js/0.4.16/datepair.js" integrity="sha512-rID0ls9BRjYTViswphwtM8n2d8eSykJklr9w23gRW94qwsFQnj2Syi/f2pvUcMa2H8P9Z0yqlIkth7Ma5G1mzg==" crossorigin="anonymous"></script>


<!--
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.js" integrity="sha512-znJmsCnj1pyv7QN2fn4UYqXw3Bp2KXMYPPbphEOkhGP8RQbePNSLUfZWd733MXIQsyaszx4PhVq9jadWa1fq5w==" crossorigin="anonymous"></script>
-->



<p>Hello</p>


<p id="basicExample">
    Date start <input type="text" class="date start" />
    Date end <input type="text" class="date end" />
</p>



<div id="timeInput1" class="bfh-selectbox" data-name="selectbox3" data-value="12" data-filter="true">
    <div data-value="1">Morning</div>
    <div data-value="2">Afternoon</div>
    <div data-value="3">Evening</div>
    <div data-value="4">8:00</div>
    <div data-value="5">9:00</div>
    <div data-value="5">10:00</div>
    <div data-value="5">11:00</div>
    <div data-value="5">12:00</div>
    <div data-value="5">13:00</div>
    <div data-value="5">14:00</div>
    <div data-value="5">15:00</div>
    <div data-value="5">16:00</div>
    <div data-value="5">17:00</div>
    <div data-value="5">18:00</div>
    <div data-value="5">19:00</div>
    <div data-value="5">20:00</div>
    <div data-value="5">21:00</div>
    <div data-value="5">22:00</div>
    <div data-value="5">23:00</div>
</div>
<div id="timeInput2" hidden class="bfh-selectbox" data-name="selectbox3" data-value="12" data-filter="true">
    <div data-value="1">Morning</div>
    <div data-value="2">Afternoon</div>
    <div data-value="3">Evening</div>
    <div data-value="4">8:00</div>
    <div data-value="5">9:00</div>
    <div data-value="5">10:00</div>
    <div data-value="5">11:00</div>
    <div data-value="5">12:00</div>
    <div data-value="5">13:00</div>
    <div data-value="5">14:00</div>
    <div data-value="5">15:00</div>
    <div data-value="5">16:00</div>
    <div data-value="5">17:00</div>
    <div data-value="5">18:00</div>
    <div data-value="5">19:00</div>
    <div data-value="5">20:00</div>
    <div data-value="5">21:00</div>
    <div data-value="5">22:00</div>
    <div data-value="5">23:00</div>
</div>
<div id="timeInput3" hidden class="bfh-selectbox" data-name="selectbox3" data-value="12" data-filter="true">
    <div data-value="1">Morning</div>
    <div data-value="2">Afternoon</div>
    <div data-value="3">Evening</div>
    <div data-value="4">8:00</div>
    <div data-value="5">9:00</div>
    <div data-value="5">10:00</div>
    <div data-value="5">11:00</div>
    <div data-value="5">12:00</div>
    <div data-value="5">13:00</div>
    <div data-value="5">14:00</div>
    <div data-value="5">15:00</div>
    <div data-value="5">16:00</div>
    <div data-value="5">17:00</div>
    <div data-value="5">18:00</div>
    <div data-value="5">19:00</div>
    <div data-value="5">20:00</div>
    <div data-value="5">21:00</div>
    <div data-value="5">22:00</div>
    <div data-value="5">23:00</div>
</div>
<div id="timeInput4" hidden class="bfh-selectbox" data-name="selectbox3" data-value="12" data-filter="true">
    <div data-value="1">Morning</div>
    <div data-value="2">Afternoon</div>
    <div data-value="3">Evening</div>
    <div data-value="4">8:00</div>
    <div data-value="5">9:00</div>
    <div data-value="5">10:00</div>
    <div data-value="5">11:00</div>
    <div data-value="5">12:00</div>
    <div data-value="5">13:00</div>
    <div data-value="5">14:00</div>
    <div data-value="5">15:00</div>
    <div data-value="5">16:00</div>
    <div data-value="5">17:00</div>
    <div data-value="5">18:00</div>
    <div data-value="5">19:00</div>
    <div data-value="5">20:00</div>
    <div data-value="5">21:00</div>
    <div data-value="5">22:00</div>
    <div data-value="5">23:00</div>
</div>
<div id="timeInput5" hidden class="bfh-selectbox" data-name="selectbox3" data-value="12" data-filter="true">
    <div data-value="1">Morning</div>
    <div data-value="2">Afternoon</div>
    <div data-value="3">Evening</div>
    <div data-value="4">8:00</div>
    <div data-value="5">9:00</div>
    <div data-value="5">10:00</div>
    <div data-value="5">11:00</div>
    <div data-value="5">12:00</div>
    <div data-value="5">13:00</div>
    <div data-value="5">14:00</div>
    <div data-value="5">15:00</div>
    <div data-value="5">16:00</div>
    <div data-value="5">17:00</div>
    <div data-value="5">18:00</div>
    <div data-value="5">19:00</div>
    <div data-value="5">20:00</div>
    <div data-value="5">21:00</div>
    <div data-value="5">22:00</div>
    <div data-value="5">23:00</div>
</div>



<div class="add" onclick="addPrescribingInput()">ADD medication intake</div>
<div class="delete" onclick="deletePrescribingInput()">DELETE medication intake</div>



<!--  ADD/DELETE InputTime  -->
<script>
    var x = 1;
    function addPrescribingInput() {
        if (x < 5) {
            x++;
            document.getElementById('timeInput' + x).removeAttribute("hidden");
        } else
        {
            alert('STOP it!');
        }
    }
    function deletePrescribingInput() {
        if (x > 1) {

            document.getElementById('timeInput' + x).setAttribute("hidden", "true");

            x--;
        } else
        {
            alert('STOP it!');
        }
    }
</script>


<!-- Datepair script -->
<script>
    $('#basicExample .date').datepicker({
        'format': 'd/m/yyyy',
        'autoclose': true
    });

    // initialize datepair
    var basicExampleEl = document.getElementById('basicExample');
    var datepair = new Datepair(basicExampleEl);
</script>



</body>
</html>