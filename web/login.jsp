<%-- 
    Document   : login
    Created on : Jan 7, 2021, 9:36:25 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <!--Made with love by Mutiullah Samim -->

        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Custom styles-->
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                    </div>
                    <div class="card-body">
                        <form action="MainController" method="POST">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="txtUsername" minlength="1" maxlength="10" class="form-control" placeholder="UserID" required="required">
                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="txtPassword" minlength="1" maxlength="10"  class="form-control" placeholder="Password" required="required">
                            </div>
                            <div class="row align-items-center remember">

                            </div>
                            <div class="form-group">
                                <a class="btn btn-danger google-btn social-btn" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/J3.L.P0013/LoginGoogleServlet&response_type=code
                                   &client_id=67644141261-plbevi0ssdbtdikv5jm7s3hcfug71hsm.apps.googleusercontent.com&approval_prompt=force"><span><i class="fab fa-google-plus-g"></i> Sign in with Google+</span> </a>
                                <input type="submit" name="action" value="Login" class="btn float-right login_btn">
                            </div>
                            <div>
                                <c:if test="${requestScope.FAILED !=null}">
                                    <div class="alert alert-danger" role="alert">
                                        ${requestScope.FAILED}
                                    </div>
                                </c:if>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-8 text-white text-sm-left"><span>Do you have an Account?</span></div>
                            <div class="col-4 float-right"><a href="signUp.jsp">Sign up</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
