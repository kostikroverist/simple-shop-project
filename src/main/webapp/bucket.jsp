<%--
  Created by IntelliJ IDEA.
  User: Костя
  Date: 27.10.2020
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #myInput {
            background-position: 10px 10px;
            background-repeat: no-repeat;
            width: 100%;
            font-size: 16px;
            padding: 12px 20px 12px 40px;
            border: 1px solid #ddd;
            margin-bottom: 12px;
        }

        #myTable {
            border-collapse: collapse;
            width: 100%;
            border: 1px solid #ddd;
            font-size: 18px;
        }

        #myTable th, #myTable td {
            text-align: left;
            padding: 12px;
        }

        #myTable tr {
            border-bottom: 1px solid #ddd;
        }

        #myTable tr.header, #myTable tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<h1> Hello its you bucket</h1>

<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

<table id="myTable">
</table>






<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script>
    function myFunction() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
    var buckets = null;
    $.get("buckets", function(data) {
        if (data !== '') {
            buckets = data;
        }
    }).done(function() {

        var tableContent = "<tr class='header'>"+
            "<th style='width: 20%;'>Name</th>"+
            "<th style='width: 20%;'>Description</th>"+
            "<th style='width: 20%;'>Price</th>"+
            "<th style='width: 20%;'>PurchaseDate</th>"+
            "<th style='width: 20%;'>Options</th>"+
            "</tr>";

        jQuery.each(buckets, function(i, value) {

            tableContent+="<tr>"+
                "<td>" + value.name + "</td>"+
                "<td>" + value.description + "</td>"+
                "<td>" + value.prise + "</td>"+
                "<td>" + value.purchaseDate + "</td>"+
                "<td><button onclick='deleteOrderFromBucket(" + value.bucketId + ")'>delete</button></td>"+
                "</tr>"

        });

        $('#myTable').html(tableContent);

    });
    function deleteOrderFromBucket(bucketId) {
        var customUrl = '';
        var urlContent = window.location.href.split('/');
        for (var i = 0; i < urlContent.length-1; i++) {
            customUrl+=urlContent[i]+'/'
        }
        customUrl+='bucket?bucketId='+bucketId;

        $.ajax({
            url: customUrl,
            type: 'DELETE',
            success: function(data) {
                if (data == 'Success') {
                    location.reload();
                }
            }
        });
    }

</script>

</body>
</html>
