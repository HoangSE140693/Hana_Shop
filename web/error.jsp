<%-- 
    Document   : error
    Created on : Jan 14, 2021, 8:43:23 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<div class="container">
    <div class="col-md-12">
        <style>
            .error-template {
                padding-top: 80px;
                padding-bottom: 100px;
                text-align: center;
            }

            .error-actions {
                margin-top: 15px;
            }

            .error-actions i {
                margin-right: 5px;
            }
        </style>
        <div class="error-template">
            <h1>Oops!</h1>
            <h2>Sorry, an error has occurred</h2>
            <div class="error-actions">
                <a href="home.jsp" class="btn btn-primary btn-lg">
                    <i class="fa fa-home" aria-hidden="true"></i>Take Me Home
                </a>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
