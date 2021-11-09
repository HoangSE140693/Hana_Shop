<%-- 
    Document   : signUp
    Created on : Mar 8, 2021, 10:46:04 PM
    Author     : Asus
--%>
<%@include file="header.jsp" %>

<div class="ftco-blocks-cover-1">
    <div class="ftco-cover-1 overlay" style="background-image: url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');
         background-size: cover;
         background-repeat: no-repeat;
         height: 100%;">
        <div class="container">
            <div class="row align-items-center">
                <style>
                    body {
                        background-color: #E2E2E2 !important;
                    }

                    .navbar {
                        background: #563d7c;
                        color: white;
                    }

                    .navbar .brand-name {
                        font-size: 20px !important;
                    }

                    .signup-form {
                        width: 400px;
                        margin: 0 auto;
                        padding: 30px 0;
                    }

                    .signup-form h2 {
                        color: #636363;
                        margin: 0 0 15px;
                        text-align: center;
                    }

                    .signup-form form {
                        border-radius: 4px;
                        margin-bottom: 20px;
                        background: #fff;
                        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                        padding: 30px;
                    }

                    .signup-form .input-group-text {
                        border: none !important;
                        background-color: white !important;
                        font-size: 25px;
                    }

                    .signup-form .fa-envelope {
                        font-size: 18px;
                    }

                    .signup-form .fa-check {
                        font-size: 18px;
                    }

                    .signup-form .form-control {
                        box-shadow: none !important;
                        border-top: none;
                        border-left: none;
                        border-right: none;
                    }

                    .signup-form .btn {
                        margin-top: 30px;
                        font-size: 16px;
                        min-width: 120px;
                        font-weight: bold;
                        background: #3598DC;
                    }

                    .signup-form .btn:hover,
                    .login-form .btn:active,
                    .login-form .btn:focus {
                        background: #1D8EDB !important;
                    }

                    .signup-form a {
                        color: #3598DC;
                        text-decoration: none;
                    }

                    .signup-form a:hover {
                        color: #3598DC;
                        text-decoration: underline;
                    }
                </style>
                <div class="col-5">
                    <div class="signup-form">
                        <c:set var="account" value="${requestScope.NEW_ACCOUNT}" scope="page"></c:set>
                        <form action="login.jsp">
                                <h2>Create Account</h2>
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-user" aria-hidden="true"></i></span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="User name" minlength="2" maxlength="50" required name="txtUserID" value="${param.txtUserID}">
                                </div>
                            </div>

                            <c:if test="${not empty requestScope.INVALID.emailError}">
                                <div class="alert alert-danger" role="alert">
                                    <small> ${requestScope.INVALID.emailError}</small>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-address-card" aria-hidden="true"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Full Name" name="txtFullName" value="${param.txtFullName}" minlength="2" maxlength="50" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                    </div>
                                    <input type="password" class="form-control" placeholder="Password" name="txtPassword" value="${param.txtPassword}" minlength="3" maxlength="50" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fa fa-check" aria-hidden="true"></i></span>
                                    </div>
                                    <input type="password" class="form-control" placeholder="Confirm Password" name="txtConfirm" minlength="3" required>
                                </div>
                            </div>
                            <c:if test="${not empty requestScope.INVALID.confirmError}">
                                <div class="alert alert-danger" role="alert">
                                    <small> ${requestScope.INVALID.confirmError}</small>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block btn-lg" name="action" value="Sign Up">Sign Up</button>
                            </div>
                            <c:if test="${not empty requestScope.ACCOUNT_CREATION_SUCCESS}">
                                <div class="alert alert-primary" role="alert">
                                    <small> ${requestScope.ACCOUNT_CREATION_SUCCESS}</small>
                                </div>
                            </c:if>
                            <div class="text-center">Already have an account? <a href="login.jsp">Login here</a></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<c:if test="${(not empty requestScope.VERIFY) || (not empty requestScope.WRONG)}">
    <style>
        .modal-newsletter {	
            color: #999;
            width: 625px;
            max-width: 625px;
            font-size: 15px;
        }
        .modal-newsletter .modal-content {
            margin-top: 90px;
            padding: 30px;
            border-radius: 0;		
            border: none;
        }
        .modal-newsletter .modal-header {
            border-bottom: none;   
            position: relative;
            border-radius: 0;
        }
        .modal-newsletter h4 {
            color: #000;
            font-size: 30px;
            margin: 0;
            font-weight: bold;
        }
        .modal-newsletter .close {
            position: absolute;
            top: -15px;
            right: -15px;
            text-shadow: none;
            opacity: 0.3;
            font-size: 24px;
        }
        .modal-newsletter .close:hover {
            opacity: 0.8;
        }
        .modal-newsletter .icon-box {
            color: #7265ea;		
            display: inline-block;
            z-index: 9;
            text-align: center;
            position: relative;
            margin-bottom: 10px;
        }
        .modal-newsletter .icon-box i {
            font-size: 110px;
        }
        .modal-newsletter .form-control, .modal-newsletter .btn {
            min-height: 46px;
            border-radius: 0;
        }
        .modal-newsletter .form-control {
            box-shadow: none;
            border-color: #dbdbdb;
        }
        .modal-newsletter .form-control:focus {
            border-color: #f95858;
            box-shadow: 0 0 8px rgba(249, 88, 88, 0.4);
        }
        .modal-newsletter .btn {
            color: #fff;
            background: #f95858;
            text-decoration: none;
            transition: all 0.4s;
            line-height: normal;
            padding: 6px 20px;
            min-width: 150px;
            margin-left: 6px !important;
            border: none;
        }
        .modal-newsletter .btn:hover, .modal-newsletter .btn:focus {
            box-shadow: 0 0 8px rgba(249, 88, 88, 0.4);
            background: #f72222;
            outline: none;
        }
        .modal-newsletter .input-group {
            margin-top: 30px;
        }
        .hint-text {
            margin: 100px auto;
            text-align: center;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#myModal").modal('show');
        });
    </script>
</c:if>
<div id="myModal" class="modal fade">
    <div class="modal-dialog modal-newsletter">
        <div class="modal-content">
            <form action="MainController" method="post">
                <div class="modal-header">
                    <h4>VERIFY EMAIL</h4>	
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span>&times;</span></button>
                </div>
                <div class="modal-body">
                    <p>${requestScope.VERIFY}</p>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Enter code" required name="txtVerifyCode">
                        <div class="input-group-append">
                            <input type="submit" class="btn btn-primary" name="action" value="Verify">
                        </div>
                    </div>
                    <input type="hidden" name="txtEmail" value="${param.txtEmail}"/>
                    <c:if test="${not empty requestScope.WRONG}">
                        <div class="alert alert-primary" role="alert">
                            <small> ${requestScope.WRONG}</small>
                        </div>
                    </c:if>
                </div>
            </form>			
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
