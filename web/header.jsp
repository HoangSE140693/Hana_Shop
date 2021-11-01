<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Site Metas -->
        <title>Hana Shop</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive CSS -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>

    <body>
        <!-- Start Main Top -->

        <!-- Start Main Top -->
        <header class="main-header">
            <!-- Start Navigation -->
            <nav class="navbar navbar-expand-lg navbar-light navbar-default bootsnav" style="background-color: #dee2e6;">
                <div class="container">
                    <!-- Start Header Navigation -->
                    <div class="navbar-header">
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                            <i class="fa fa-bars"></i>
                        </button>
                        <a class="navbar-brand" href="index.html"><img src="images/logo.png" class="logo" alt="" style="width: 140px"></a>
                    </div>
                    <!-- End Header Navigation -->

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="navbar-menu">
                        <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                            <li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
                                <c:if test="${sessionScope.ROLE eq 'admin'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="MainController?action=viewProduct">Product Management</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.ROLE eq 'user'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="MainController?action=viewProduct">Shopping</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="MainController?action=showHistory">History</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="viewCart.jsp"><i class="fas fa-shopping-cart"></i> Cart</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->

                    <!-- Start Atribute Navigation -->
                    <div class="navbar-nav ml-auto">

                        <c:if test="${sessionScope.ROLE==null}"> 
                            <a class="nav-link" href="login.jsp">
                                <i class="fas fa-user fa-fw"></i> LOGIN
                            </a>
                        </c:if>
                        <c:if test="${sessionScope.ROLE!=null}"> 
                            <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                ${sessionScope.infoUser.userName} 
                                <i class="fas fa-user fa-fw"></i></a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                                <c:url value="/MainController" var="Logout">
                                    <c:param name="action" value="Logout"/>
                                </c:url>
                                <a class="dropdown-item" href="${Logout}">Logout</a>
                            </div>
                        </c:if>
                        
                    </div>
                    <!-- End Atribute Navigation -->
                   
                </div>
                <!-- End Side Menu -->
                <img src="images/contact.png" style="height: 80px;width: 150px; cursor: pointer;"/>
            </nav>
            
            <!-- End Navigation -->
        </header>
