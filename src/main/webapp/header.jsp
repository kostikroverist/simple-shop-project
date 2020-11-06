<%--
  Created by IntelliJ IDEA.
  User: Костя
  Date: 26.10.2020
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        .view {
            background: url("https://mdbootstrap.com/img/Photos/Others/img (50).jpg") no-repeat center center;
        }

        html,
        body,
        header,
        .view {
            height: 100%;
        }

        div.container-fluid{
            min-height: 1000px;
        }
    </style>
</head>
<body>
<html lang="en" class="full-height">

<!--Main Navigation-->
<header>

    <nav class="navbar fixed-top navbar-expand-lg navbar-dark indigo">
        <a class="navbar-brand" href="#"><strong>Periodical</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav w-100  d-flex ">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item create-product-option">
                    <a class="nav-link" href="${pageContext.request.contextPath}/createProduct.jsp">add Periodical</a>
                </li>
                <li class="nav-item user-bucket-option">
                    <a class="nav-link" href="${pageContext.request.contextPath}/bucket.jsp">Bucket</a>
                </li>
                <li class="nav-item ml-auto">
                    <button class="product-logout" style="background-color: green">LogOut</button>
                </li>
            </ul>
        </div>
    </nav>

    <div class="view intro-2">
        <div class="full-bg-img">
            <div class="mask rgba-black-light flex-center">
                <div class="container text-center white-text">
                    <div class="white-text text-center wow fadeInUp">

                    </div>
                </div>
            </div>
        </div>
    </div>

</header>
<!--Main Navigation-->

<!--Main Layout-->
<main class="text-center py-5">

    <div class="container">
        <div class="row">
            <div class="col-md-12">


            </div>
        </div>
    </div>

</main>
<!--Main Layout-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script>

    $("button.product-logout").click(function () {

        $.get("logout", function (data) {
            if (data !== '') {
                var customUrl = '';
                var urlContent = window.location.href.split('/');
                for (var i = 0; i < urlContent.length - 1; i++) {
                    customUrl += urlContent[i] + '/'
                }
                customUrl += data;
                window.location = customUrl;
            }
        });

    });
    $(document).ready(function() {
        $.get("user-role", function(data) {
            if (data !== '') {
                userRole = data;
            }
        }).done(function() {
            if (userRole === 'ADMINISTRATOR') {
                $('li.user-bucket-option').hide();
            } else {
                $('li.create-product-option').hide();
            }
        });
    });
</script>

</body>


</html>
