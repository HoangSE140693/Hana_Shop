<%-- 
    Document   : shoppingHistory
    Created on : Jan 21, 2021, 9:28:27 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<c:if test="${requestScope.listOrderDetail != null}">
    <script>
        $(document).ready(function () {
            $("#myModal").modal('show');
        });
    </script>
</c:if>
<div id="myModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">BILL DETAIL</h3>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product name</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${requestScope.listOrderDetail}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.productName}</td>
                                        <td class="thumbnail-img">
                                            <img class="img-fluid"
                                                 src="${dto.image}"
                                                 alt="" />
                                        </td >
                                        <td class="price-pr">$ ${dto.price}</td>
                                        <td>
                                            ${dto.quantity}
                                        </td>
                                    </tr>
                                    </div>
                                </c:forEach>
                            </tbody>
                        </table>
                        <hr>
                        <div>
                            <h2><b>Receiver's information</b></h2>
                            <label style="font-weight: bold">Address: </label>
                            <span> 70/1/23/5 Phu Chau Address, Dist 5, HCMC</span>
                            <br>
                            <label style="font-weight: bold">Phone: </label>
                            <span> 0937917091 </span>
                            <br>
                            <label style="font-weight: bold">Name: </label>
                            <span> Diep Dang Huy Hoang </span>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Oke</button>
            </div>
        </div>
    </div>
</div>
<div class="shop-box-inner">
    <div class="container">
        <div class="row">
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-12 col-xs-12  sidebar-shop-right" style="padding: 50px 0px;">
                <div class="product-categorie-box">
                    <form action="MainController" method="POST">
                        <div class="search-product">
                            <input name="txtSearchName" class="form-control" placeholder="Search here..." type="text" value="">
                            <button name="action" type="submit" value="searchOrderedProductByName"> <i class="fa fa-search"></i> </button>
                        </div>
                    </form>
                    <div class="filter-price-left">
                        <form action="MainController" method="POST">
                            <div>
                                <div>
                                    <label style="font-weight: bold">From:</label><br>
                                    <input class="form-control" name="txtFrom" type="datetime-local" required/>
                                </div>
                                <div>
                                    <label style="font-weight: bold">To:</label><br>
                                    <input class="form-control" name="txtTo" type="datetime-local" required />
                                </div>
                            </div>
                            <p style=" padding-top: 20px;">
                                <small style="color: red">
                                    ${requestScope.INVALID}
                                </small>
                                <button name="action" class="btn hvr-hover" type="submit" value="searchByDate" style="float: right">Search</button>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-xl-9 col-lg-9 col-sm-12 col-xs-12">
                <div class="cart-box-main">
                    <c:if test="${requestScope.listOrder != null && sessionScope.listOrderSearch == null}">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="table-main table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>No</th>
                                                    <th>Date order</th>
                                                    <th>Total</th>
                                                    <th>Status</th>
                                                    <th>Detail</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="dto" items="${requestScope.listOrder}" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>${dto.dateOrder}</td>
                                                        <td>$ ${dto.total}</td>
                                                        <c:if test="${dto.status == true}">
                                                            <td style="color: green">paid</td>
                                                        </c:if>
                                                        <c:if test="${dto.status == false}">
                                                            <td style="color: red">unpaid</td>
                                                        </c:if>
                                                        <td>
                                                            <a class="btn btn-outline-success" href="MainController?action=viewDetail&orderID=${dto.orderID}">
                                                                Show Detail
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${(empty sessionScope.listOrderSearch) && requestScope.listOrder == null}">
                        <p class="text-center" style="
                           font-size: xx-large;
                           font-weight: bolder;">---------*No result*--------</p>
                    </c:if>
                    <div class="cart-box-main">
                        <c:if test="${sessionScope.listOrderSearch != null}">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="table-main table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Date order</th>
                                                        <th>Total</th>
                                                        <th>Detail</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="dto" items="${sessionScope.listOrderSearch}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>${dto.dateOrder}</td>
                                                            <td>$ ${dto.total}</td>
                                                            <td>
                                                                <a href="MainController?action=viewDetail&orderID=${dto.orderID}">
                                                                    Show Detail
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
