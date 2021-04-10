<%-- 
    Document   : editProduct
    Created on : Jan 19, 2021, 11:28:02 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<main>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-7">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header" style="background: black">
                        <p class="text-center font-weight-light my-4" style="font-size: xx-large;color: white">UPDATE PRODUCT</p>
                    </div>
                    <div class="card-body">
                        <form action="MainController" method="POST">
                            <c:if test="${requestScope.infoProduct != null}">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Product name</label>
                                        <input type="text" name="txtProductName" class="form-control" value="${requestScope.infoProduct.name}" required="required">
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Image</label>
                                        </div>
                                        <input type="text" name="txtImage" class="form-control" value="${requestScope.infoProduct.image}" required="required">
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Price</label>
                                        </div>
                                        <input type="text" name="txtPrice" class="form-control" value="${requestScope.infoProduct.price}" required="required">
                                        <small style="color: red">
                                            ${requestScope.INVALID.priceError}
                                        </small>
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Category</label>
                                        </div>
                                        <select name="cbCategory" class="form-control">
                                            <c:forEach var="category" items="${sessionScope.listCate}">
                                                <option <c:if test="${category.categoryName eq requestScope.infoProduct.category}">selected="true"</c:if>>${category.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Quantity</label>
                                        </div>
                                        <input type="text" name="txtQuantity" class="form-control" value="${requestScope.infoProduct.quantity}" required="required">
                                        <small style="color: red">
                                            ${requestScope.INVALID.quantityError}
                                        </small>
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Status</label>
                                        </div>
                                        <select name="cbStatus" class="form-control">
                                            <option selected="true">active</option>
                                            <option>inactive</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <div class="clearfix">
                                            <label>Description</label>
                                        </div>
                                        <input type="text" name="txtDescription" class="form-control" value="${requestScope.infoProduct.description}" required="required">
                                    </div>
                                    <input type="hidden" name="txtProductID" value="${requestScope.infoProduct.productID}"/>
                                </div>
                            </c:if>
                            <div class="modal-footer justify-content-between">
                                <input name="action" type="submit" class="btn btn-primary btn-block"  value="Update">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
