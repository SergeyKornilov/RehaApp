<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        </style>
</head>
<body>
<p>Hello</p>


<p id="basicExample">
    Date start <input type="text" class="date start" />
    Date end <input type="text" class="date end" />
</p>



    <p>
        <input id="basicExampleTime" type="text" class="time ui-timepicker-input" autocomplete="off">
    </p>
    <div id="input0"></div>

<div class="add" onclick="addPrescribingInput()">+ medication intake</div>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- include input widgets; this is independent of Datepair.js -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.css" integrity="sha512-GgUcFJ5lgRdt/8m5A0d0qEnsoi8cDoF0d6q+RirBPtL423Qsj5cI9OxQ5hWvPi5jjvTLM/YhaaFuIeWCLi6lyQ==" crossorigin="anonymous" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script><script type="text/javascript" src="jquery.timepicker.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/datepair.js/0.4.16/datepair.js" integrity="sha512-rID0ls9BRjYTViswphwtM8n2d8eSykJklr9w23gRW94qwsFQnj2Syi/f2pvUcMa2H8P9Z0yqlIkth7Ma5G1mzg==" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.13.16/jquery.timepicker.js" integrity="sha512-znJmsCnj1pyv7QN2fn4UYqXw3Bp2KXMYPPbphEOkhGP8RQbePNSLUfZWd733MXIQsyaszx4PhVq9jadWa1fq5w==" crossorigin="anonymous"></script>





<!--  ADD Input  -->
<script>
    var x = 0;
    function addPrescribingInput() {
        if (x < 10) {
            var str = '<p><input id="basicExampleTime" type="text" class="time ui-timepicker-input" autocomplete="off"></p> <div id="input' + (x + 1) + '"></div>';
            document.getElementById('input' + x).innerHTML = str;
            x++;
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


<!-- jquery.timepicker script -->
<script>
    $('#basicExampleTime').timepicker({
        'minTime': '00:00',
        'maxTime': '23:59',
        'timeFormat': 'H:i',

        'noneOption': [
            {
                'label': 'Morning',
                'value': '28800'

            },
            {
                'label': 'Afternoon',
                'value': '50400'
            },
            {
                'label': 'Evening',
                'value': '72000'
            }

        ]


    });
</script>

</body>
</html>