<%--
  Created by IntelliJ IDEA.
  User: Костя
  Date: 27.10.2020
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products Create</title>

    <style>
        .productCreate{
            margin: 0 auto;
            width: 400px;
        }
        .productCreate{
            min-height: 500px;
            margin: auto;
            width: 50%;
            border: 3px solid white;
            padding: 10px;
        }
    </style>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>


<h1>Hello do you wont create some product?)</h1>
<div class="productCreate">

    <form class="createProduct">

    <div class="form-group">
        <input type="text" class="form-control productName" placeholder="enter product name">
    </div>
    <div class="form-group">
        <input type="text" class="form-control productDescription"  placeholder="enter product description">
    </div>

    <div class="form-group">
        <input type="number" class="form-control productprice"  placeholder="enter product price">
    </div>
    <button type="button" class="btn btn-primary createProduct">Submit</button>
</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>



<script>

    $("button.createProduct")
        .click(
            function() {

                var name = $("form.createProduct input.productName").val();
                var description = $("form.createProduct input.productDescription").val();
                var price = $("form.createProduct input.productPrice").val();

                var product = {
                    name : name,
                    description : description,
                    prise : price
                };
//add validation
                $.post("product", product,
                    function(data) {
                        if (data == 'Success') {
//										$("form")[0].reset();
//										$("form")[1].reset();
//										showAlertAfterRegistration();
                            alert('Success');
                        }
                    });

            });

</script>

</body>
</html>
