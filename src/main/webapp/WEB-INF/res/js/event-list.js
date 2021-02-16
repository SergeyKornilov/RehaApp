// $.fn.popup = function () { 	//pop-up window open function
//     this.css('position', 'absolute').fadeIn();
//     this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px');
//     this.css('left', ($(window).width() - this.width()) / 2 + 'px');
//     $('.backpopup').fadeIn();
// };
//
// $(document).ready(function () {	//popup init
//     $('.open').click(function () {
//         $('.popup-window').popup();
//     });
//     $('.backpopup,.close').click(function () { //click on shadow or X
//         $('.popup-window').fadeOut();
//         $('.backpopup').fadeOut();
//     });
// });

function setEventIdPostForm(id) {
    document.getElementById("postInputId").value = id;
}

function hideClosed() {

    var table = document.getElementById("tableListEvents");


    for (var x = 1; x < table.rows.length; x++) {

        document.getElementById("btn-hideClosed").setAttribute("hidden", "true");
        document.getElementById("btn-displayClosed").removeAttribute("hidden");


        if (document.getElementById("inputDateOfBirth").value.length === 0) {

            if (table.rows[x].cells[5].innerText === "close" ||
                table.rows[x].cells[5].innerText.includes("Cancel")) {

                table.rows[x].style.display = "none";

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

            }
        }

    }
}

function displayClosed() {
    var table = document.getElementById("tableListEvents");


    for (var x = 1; x < table.rows.length; x++) {
        document.getElementById("btn-displayClosed").setAttribute("hidden", "true");
        document.getElementById("btn-hideClosed").removeAttribute("hidden");

        if (document.getElementById("inputDateOfBirth").value.length === 0) {

            if (table.rows[x].cells[5].innerText.trim() === "close" ||
                table.rows[x].cells[5].innerText.includes("Cancel")) {

                table.rows[x].style.display = "";
            }
        } else {
            date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");
            console.log("if - display ");
            if (table.rows[x].cells[5].innerText.trim() === "close" &&
                table.rows[x].cells[0].innerText === date
                ||
                table.rows[x].cells[5].innerText.includes("Cancel") &&
                table.rows[x].cells[0].innerText === date
            ) {
                console.log("in if - display ");
                table.rows[x].style.display = "";

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
            table.rows[i].style.visibility = " visible";
        } else {
            table.rows[i].style.visibility = "collapse";
        }

    }
}

function filterDate() {
    var table = document.getElementById("tableListEvents");
    var closedDisplay = document.getElementById("btn-displayClosed").hidden;


    if (document.getElementById("inputDateOfBirth").value.length != 0) {


        date = moment(document.getElementById("inputDateOfBirth").value, "YYYY/MM/DD").format("DD.MM.YYYY");

        for (var x = 1; x < table.rows.length; x++) {

            table.rows[x].style.display = "";

            document.getElementById("btn-hideClosed").setAttribute("hidden", "true");
            document.getElementById("btn-displayClosed").removeAttribute("hidden");

            if (table.rows[x].cells[0].innerText === date &&
                table.rows[x].cells[5].innerText === "open"


            ) {

                table.rows[x].style.display = "";

            } else {
                table.rows[x].style.display = "none";
            }
        }
    } else {

        for (var h = 1; h < table.rows.length; h++) {

            if (closedDisplay) {
                table.rows[h].style.display = "";
            } else {
                if (table.rows[h].cells[5].innerText.trim() === "open") table.rows[h].style.display = "";
            }

        }
    }
}


