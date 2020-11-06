<%--
  Created by IntelliJ IDEA.
  User: Костя
  Date: 08.10.2020
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
    <title>Cabinet</title>
<style>
    div.container-fluid{
        min-height: 1000px;
    }
</style>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <div id="productCards">
        </div>
    </div>
</div>



<jsp:include page="footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    let products = null;

    $.get("products", function (data) {
        if (data !== ' ') {
            products=data;
        }
    }).done(function () {
        let cardsContent = "";
        jQuery.each(products, function(i, value) {

            cardsContent+="<div class='col'>" +
                "<div class='card'>" +
                "<div class='card-body'>" +
                "<h5 class='card-title'>" + value.name + "</h5>"+
                "<p class='card-text'>" + value.description + "</p>"+
                "<h6 class='card-subtitle mb-2 text-muted'>" + value.prise + "</h6>"+
                "<a href='product?id=" + value.id + "' class='card-link'>link</a>"+
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>"
        });

        $('#productCards').html(cardsContent);
    }).done(function() {
        $.get("user-role", function(data) {
            if (data !== '') {
                userRole = data;
            }
        }).done(function() {
            if(userRole === 'ADMINISTRATOR'){
                $('a.card-link').hide();
            }
        });
    });

</script>

</body>
</html>
