<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login.css">
    <title>Hello, world!</title>
    <style>@import url(https://fonts.googleapis.com/css?family=Roboto:300);

    .login-page {
        width: 360px;
        padding: 8% 0 0;
        margin: auto;
    }

    .form {
        position: relative;
        z-index: 1;
        background: #FFFFFF;
        max-width: 360px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }

    .form input {
        font-family: "Roboto", sans-serif;
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 15px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }

    .form button {
        font-family: "Roboto", sans-serif;
        text-transform: uppercase;
        outline: 0;
        background: #4CAF50;
        width: 100%;
        border: 0;
        padding: 15px;
        color: #FFFFFF;
        font-size: 14px;
        -webkit-transition: all 0.3 ease;
        transition: all 0.3 ease;
        cursor: pointer;
    }

    .form button:hover, .form button:active, .form button:focus {
        background: #43A047;
    }

    .form .message {
        margin: 15px 0 0;
        color: #b3b3b3;
        font-size: 12px;
    }

    .form .message a {
        color: #4CAF50;
        text-decoration: none;
    }

    .form .register-form {
        display: none;
    }

    .container {
        position: relative;
        z-index: 1;
        max-width: 300px;
        margin: 0 auto;
    }

    .container:before, .container:after {
        content: "";
        display: block;
        clear: both;
    }

    .container .info {
        margin: 50px auto;
        text-align: center;
    }

    .container .info h1 {
        margin: 0 0 15px;
        padding: 0;
        font-size: 36px;
        font-weight: 300;
        color: #1a1a1a;
    }

    .container .info span {
        color: #4d4d4d;
        font-size: 12px;
    }

    .container .info span a {
        color: #000000;
        text-decoration: none;
    }

    .container .info span .fa {
        color: #EF3B3A;
    }

    body {
        font-family: "Roboto", sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

    div.login-page div.alert.alert-success {
        display: none;
    }
    </style>
</head>
<body>
<!-- Optional JavaScript; choose one of the two! -->

<div class="login-page">
    <div class="form">
        <form class="register-form">
            <input class="firstName" type="text" placeholder="first name"/> <input
                class="lastName" type="text" placeholder="last name"/> <input
                class="email" type="text" placeholder="email address"/> <input
                class="password" type="password" placeholder="password"/> <input
                class="cpassword" type="password" placeholder="confirm password"/>

            <button class="register">create</button>

            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form">
            <input class="email" type="text" placeholder="email address"/>
            <input class="password" type="password" placeholder="password"/>

            <button class="login">login</button>

            <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form>
    </div>
    <div class="alert alert-success" role="alert">
        <b>Success!</b> You are registred
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>


<script>
    function loginRegisterSwitch() {
        $('form').animate({
            height: "toggle",
            opacity: "toggle"
        }, "slow");
    }

    function showAlertAfterRegistration() {
        $('div.alert.alert-success').show();
    }

    $('.message a').click(function () {
        loginRegisterSwitch();
    });

    $("button.register")
        .click(
            function () {
                var firstName = $("form.register-form input.firstName")
                    .val();
                var lastName = $("form.register-form input.lastName").val();
                var email = $("form.register-form input.email").val();
                var password = $("form.register-form input.password").val();
                var cpassword = $("form.register-form input.cpassword")
                    .val();

                if (firstName == '' || lastName == '' || email == ''
                    || password == '' || cpassword == '') {
                    alert("Please fill all fields...!!!!!!");
                } else if ((password.length) < 8) {
                    alert("Password should atleast 8 character in length...!!!!!!");
                } else if (!(password).match(cpassword)) {
                    alert("Your passwords don't match. Try again?");
                } else {
                    var userRegistration = {
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        password: password
                    };

                    $.post("registration", userRegistration,
                        function (data) {
                            if (data == 'Success') {
                                $("form")[0].reset();
                                $("form")[1].reset();
                                loginRegisterSwitch();

                                showAlertAfterRegistration();
                            }
                            alert("asfasf")

                        });
                }
            });

    $("button.login")
        .click(
            function () {
                var email = $("form.login-form input.email").val();
                var password = $("form.login-form input.password").val();
                if (email == '' || password == '') {
                    alert("Please fill login form!...!!!!!!");
                } else {
                    var userLogin = {
                        email: email,
                        password: password
                    };

                    $.post("login", userLogin, function (data) {
                        if (data !== '') {
                            var customUrl = '';
                            var urlContent = window.location.href.split('/');
                            for (var i = 0; i < urlContent.length - 1; i++) {
                                customUrl += urlContent[i] + '/'
                            }
                            customUrl += data.destinationURL;
                            window.location = customUrl;
                        }
                        $("form")[1].reset();
                    });
                }
            });

</script>

<!-- Option 2: jQuery, Popper.js, and Bootstrap JS
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
-->
</body>
</html>
