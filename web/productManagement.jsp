<%-- 
    Document   : admin
    Created on : Jan 14, 2021, 8:42:57 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<div class="top-search">
    <div class="container">
        <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-search"></i></span>
            <input type="text" class="form-control" placeholder="Search">
            <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
        </div>
    </div>
</div>
<!-- End Top Search -->

<!-- Start All Title Box -->
<div style="display: flex">
    <image src="./images/sale_tet.png" alt="" style="height: 250px; margin: 0 auto; z-index: 100"/>
</div>
<div class="all-title-box" style="height: 60px; padding: 5px">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h2>Shop</h2>
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active">Shop</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End All Title Box -->

<!-- Start Shop Page  -->
<div class="shop-box-inner">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xl-2 col-lg-2 col-sm-12 col-xs-12 sidebar-shop-right">
                <div class="bannerScale">
                    <a href="#" title="Banenr Title">
                        <image src="./images/bannerFootball.png" style="width: 200px; height: 500px" alt="Banner Name"/>           
                </div>
            </div>
            <div class="col-xl-2 col-lg-2 col-sm-12 col-xs-12 sidebar-shop-right">
                <div class="product-categorie-box">
                    <c:if test="${sessionScope.ROLE eq 'admin'}">
                        <div class="form-control" style="margin-bottom: 20px;border-block-color: white;">
                            <a href="addNewProduct.jsp" class="btn hvr-hover" class="form-control" style="margin-left: -13px"><i class="fa fa-plus" aria-hidden="true"></i> Add new product</a>
                        </div>
                    </c:if>
                    <form action="MainController" method="POST">
                        <div class="search-product">
                            <input name="txtSearchName" class="form-control" placeholder="Search here..." type="text" value="${sessionScope.searchByName}">
                            <button name="action" type="submit" value="searchByName"> <i class="fa fa-search"></i> </button>
                        </div>
                    </form>

                    <div class="btn-group" style="display: flex;padding-top: 20px">
                        <button type="button" class="btn btn-secondary btn-block dropdown-toggle arrow" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categories
                        </button>
                        <div class="dropdown-menu form-control">
                            <form action="MainController" method="POST">
                                <c:if test="${sessionScope.listCate!=null}">
                                    <a href="MainController?action=searchCate&txtSearchCate=&txtSearchName=${sessionScope.searchByName}&<c:if test="${sessionScope.minPrice!=null}">txtMinPrice=${sessionScope.minPrice}&txtMaxPrice=${sessionScope.maxPrice}"</c:if><c:if test="${sessionScope.minPrice==null}">txtMinPrice=0&txtMaxPrice=${requestScope.maximumPrice}"</c:if>
                                       class="dropdown-item <c:if test="${sessionScope.searchCategoryID eq ''}">active</c:if>">All</a>
                                    <c:forEach var="category" items="${sessionScope.listCate}">
                                        <a href="MainController?action=searchCate&txtSearchCate=${category.categoryID}&txtSearchName=${sessionScope.searchByName}&<c:if test="${sessionScope.minPrice!=null}">txtMinPrice=${sessionScope.minPrice}&txtMaxPrice=${sessionScope.maxPrice}"</c:if><c:if test="${sessionScope.minPrice==null}">txtMinPrice=0&txtMaxPrice=${requestScope.maximumPrice}"</c:if>
                                           class="dropdown-item <c:if test="${sessionScope.searchCategoryID eq category.categoryID}">active</c:if>">${category.categoryName}</a>
                                    </c:forEach>
                                </c:if>
                            </form>
                        </div>
                    </div>
                    <div class="filter-price-left" style="
                         padding-top: 30px;
                         ">
                        <div class="title-left">
                            <h3>Price</h3>
                        </div>
                        <div class="price-box-slider">
                            <form action="MainController" method="POST">
                                <div class="form-row">

                                    <div class="col">
                                        <input name="txtMinPrice" type="number" class="form-control" 
                                               size="4"  min="0" max="${requestScope.maximumPrice}" step="1" class="c-input-text qty text"
                                               <c:if test="${sessionScope.minPrice == null}">
                                                   value="0"
                                               </c:if>
                                               <c:if test="${sessionScope.minPrice != null}">
                                                   value="${sessionScope.minPrice}"
                                               </c:if>
                                               required/>
                                    </div>
                                    <div class="col">
                                        <input name="txtMaxPrice" type="number" class="form-control" 
                                               size="4"  min="0" max="${requestScope.maximumPrice}" step="1" class="c-input-text qty text"
                                               <c:if test="${sessionScope.maxPrice == null}">
                                                   value="${requestScope.maximumPrice}"
                                               </c:if>
                                               <c:if test="${sessionScope.maxPrice != null}">
                                                   value="${sessionScope.maxPrice}"
                                               </c:if>
                                               required/>
                                    </div>

                                </div>
                                <p>
                                    <small style="color: red">
                                        ${requestScope.INVALID}
                                    </small>
                                    <button name="action" class="btn hvr-hover" type="submit" value="searchByPrice">Filter</button>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-xl-6 col-lg-6 col-sm-12 col-xs-12 shop-content-left">
                <div class="right-product-box">
                    <div class="product-categorie-box">
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade show active" id="grid-view">
                                <div class="row">
                                    <c:if test="${requestScope.listProduct!=null}">
                                        <c:forEach var="product" items="${requestScope.listProduct}">
                                            <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                                                <div class="products-single fix">
                                                    <div class="box-img-hover">
                                                        <div class="type-lb">
                                                            <p class="sale">Sale</p>
                                                        </div>
                                                        <img src="${product.image}" class="img-fluid" alt="Image">
                                                        <div class="mask-icon">
                                                            <c:if test="${not (sessionScope.ROLE eq 'admin')}">
                                                                <c:if test="${sessionScope.ROLE eq 'user'}">
                                                                    <a class="cart" href="MainController?action=addToCart&productID=${product.productID}">Add to Cart</a>
                                                                </c:if>
                                                                <c:if test="${sessionScope.ROLE == null}">
                                                                    <a class="cart" href="login.jsp">Add to Cart</a>
                                                                </c:if>
                                                                <ul>
                                                                    <li><a href="#Detail${product.productID}" data-toggle="modal" data-placement="right"
                                                                           title="Detail"><i class="fas fa-eye"></i></a></li>
                                                                </ul>
                                                            </c:if>
                                                            <c:if test="${sessionScope.ROLE eq 'admin'}">
                                                                <ul>
                                                                    <li><a  href="MainController?action=editProduct&productID=${product.productID}" data-toggle="tooltip"  data-placement="right" ><i class="fas fa-edit"></i> Edit</a></li>
                                                                    <li><a  href="#Delete${product.productID}" data-toggle="modal" data-placement="right" ><i class="fas fa-trash-alt"> Delete</i></a></li>
                                                                </ul>
                                                            </c:if>
                                                        </div>
                                                    </div>

                                                    <div class="why-text">
                                                        <h4>${product.name}</h4>
                                                        <h5>$ ${product.price}</h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="Detail${product.productID}" class="modal fade">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header flex-column">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;</button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="container">
                                                                <div class="row">
                                                                    <div class="col-xl-6 col-lg-6 col-md-6">
                                                                        <img class="d-block w-100" src="${product.image}">
                                                                    </div>
                                                                    <div class="col-xl-6 col-lg-6 col-md-6">
                                                                        <h2 style="
                                                                            font-size: 30px;
                                                                            font-weight: bold;
                                                                            color: #f17a00;
                                                                            ">${product.name}</h2>
                                                                        <h5 style="
                                                                            font-size: 20px;
                                                                            color: red;
                                                                            font-weight: bold;
                                                                            ">$ ${product.price}</h5>
                                                                        <p class="available-stock"><span>${product.quantity} available</span></p><br>
                                                                        <h4 style="font-weight: bold;">Short Description:</h4>
                                                                        <p>${product.description}</p>
                                                                        <form action="MainController" method="POST">
                                                                            <ul>
                                                                                <li>
                                                                                    <div class="form-group quantity-box">
                                                                                        <label class="control-label" style="font-weight: bold;">Quantity</label>
                                                                                        <input name="txtQuantity" class="form-control" value="1" min="1" max="${product.quantity}" type="number" required>
                                                                                    </div>
                                                                                </li>
                                                                            </ul>
                                                                            <div class="cart-and-bay-btn">
                                                                                <input type="hidden" name="productID" value="${product.productID}"/>
                                                                                <button class="btn hvr-hover" data-fancybox-close="" name="action" value="addToCart">Add to cart</button>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="Delete${product.productID}" class="modal fade">
                                                <div class="modal-dialog modal-confirm">
                                                    <div class="modal-content">
                                                        <div class="modal-header flex-column">
                                                            <div class="icon-box">
                                                                <i class="material-icons">&#xE5CD;</i>
                                                            </div>
                                                            <h4 class="modal-title w-100">Are you sure?</h4>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;</button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>Do you really want to delete these records? This process cannot
                                                                be undone.</p>
                                                        </div>
                                                        <div class="modal-footer justify-content-center">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-dismiss="modal">Cancel</button>
                                                            <a class="btn btn-danger" href="MainController?action=deleteProduct&productID=${product.productID}">
                                                                Delete
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${requestScope.listSearchedProduct.size()==0}">
                                        <div class="col-12" >
                                            <p class="text-center" style="
                                               font-size: xx-large;
                                               font-weight: bolder;">
                                                -----------NO RESULT--------</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.listSearchedProduct!=null}">
                                        <c:forEach var="product" items="${requestScope.listSearchedProduct}">
                                            <div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
                                                <div class="products-single fix">
                                                    <div class="box-img-hover">
                                                        <div class="type-lb">
                                                            <p class="sale">Sale</p>
                                                        </div>
                                                        <img src="${product.image}" class="img-fluid" alt="Image">
                                                        <div class="mask-icon">
                                                            <c:if test="${not (sessionScope.ROLE eq 'admin')}">
                                                                <c:if test="${sessionScope.ROLE eq 'user'}">
                                                                    <a class="cart" href="MainController?action=addToCart&productID=${product.productID}">Add to Cart</a>
                                                                </c:if>
                                                                <c:if test="${sessionScope.ROLE ==null}">
                                                                    <a class="cart" href="login.jsp">Add to Cart</a>
                                                                </c:if>
                                                                <ul>
                                                                    <li><a href="#Detail${product.productID}" data-toggle="modal" data-placement="right"
                                                                           title="Detail"><i class="fas fa-eye"></i></a></li>
                                                                </ul>
                                                            </c:if>
                                                            <c:if test="${sessionScope.ROLE eq 'admin'}">
                                                                <ul>
                                                                    <li><a  href="MainController?action=editProduct&productID=${product.productID}" data-toggle="tooltip"  data-placement="right" ><i class="fas fa-edit"></i> Edit</a></li>
                                                                    <li><a  href="#Delete${product.productID}" data-toggle="modal" data-placement="right" ><i class="fas fa-trash-alt"> Delete</i></a></li>
                                                                </ul>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="why-text">
                                                        <h4>${product.name}</h4>
                                                        <h5>$ ${product.price}</h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="Detail${product.productID}" class="modal fade">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header flex-column">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;</button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="container">
                                                                <div class="row">
                                                                    <div class="col-xl-6 col-lg-6 col-md-6">
                                                                        <img class="d-block w-100" src="${product.image}">
                                                                    </div>
                                                                    <div class="col-xl-6 col-lg-6 col-md-6">
                                                                        <h2 style="
                                                                            font-size: 30px;
                                                                            font-weight: bold;
                                                                            color: #f17a00;
                                                                            ">${product.name}</h2>
                                                                        <h5 style="
                                                                            font-size: 20px;
                                                                            color: red;
                                                                            font-weight: bold;
                                                                            ">$ ${product.price}</h5>
                                                                        <p class="available-stock"><span>${product.quantity} available</span></p><br>
                                                                        <h4 style="font-weight: bold;">Short Description:</h4>
                                                                        <p>${product.description}</p>
                                                                        <form action="MainController" method="POST">
                                                                            <ul>
                                                                                <li>
                                                                                    <div class="form-group quantity-box">
                                                                                        <label class="control-label" style="font-weight: bold;">Quantity</label>
                                                                                        <input name="txtQuantity" class="form-control" value="1" min="1" max="${product.quantity}" type="number" required>
                                                                                    </div>
                                                                                </li>
                                                                            </ul>
                                                                            <div class="cart-and-bay-btn">
                                                                                <input type="hidden" name="productID" value="${product.productID}"/>
                                                                                <button class="btn hvr-hover" data-fancybox-close="" name="action" value="addToCart">Add to cart</button>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="Delete${product.productID}" class="modal fade">
                                                <div class="modal-dialog modal-confirm">
                                                    <div class="modal-content">
                                                        <div class="modal-header flex-column">
                                                            <div class="icon-box">
                                                                <i class="material-icons">&#xE5CD;</i>
                                                            </div>
                                                            <h4 class="modal-title w-100">Are you sure?</h4>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;</button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <p>Do you really want to delete these records? This process cannot
                                                                be undone.</p>
                                                        </div>
                                                        <div class="modal-footer justify-content-center">
                                                            <button type="button" class="btn btn-secondary"
                                                                    data-dismiss="modal">Cancel</button>
                                                            <a class="btn btn-danger" href="MainController?action=deleteProduct&productID=${product.productID}">
                                                                Delete
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${sessionScope.numberPage>0}">
                    <ul class="pagination justify-content-center">
                        <li class="page-item <c:if test="${requestScope.index == 1 || requestScope.indexSearchPage == 1}">disabled</c:if>">
                            <a href="MainController?action=<c:if test="${requestScope.listProduct ==null}">search&indexSearchPage=${requestScope.indexSearchPage-1}</c:if><c:if test="${requestScope.listProductSearch==null}">viewProduct&index=${requestScope.index-1}</c:if>" tabindex="-1"
                               class="page-link">Previous</a>
                            </li>
                        <c:forEach begin="1" end="${sessionScope.numberPage}" var="i">
                            <li class="page-item <c:if test="${requestScope.index == i || requestScope.indexSearchPage == i}">active</c:if>">
                                <a class="page-link" href="MainController?action=<c:if test="${requestScope.listProduct ==null}">search&indexSearchPage=</c:if><c:if test="${requestScope.listProductSearch==null}">viewProduct&index=</c:if>${i}">${i}</a>
                                </li>
                        </c:forEach>
                        <li class="page-item <c:if test="${requestScope.index == sessionScope.numberPage || requestScope.indexSearchPage == sessionScope.numberPage}">disabled</c:if>">
                            <a  href="MainController?action=<c:if test="${requestScope.listProduct ==null}">search&indexSearchPage=${requestScope.indexSearchPage+1}</c:if><c:if test="${requestScope.listProductSearch==null}">viewProduct&index=${requestScope.index+1}</c:if>"
                                class="page-link" >Next</a>
                            </li>
                        </ul>
                </c:if>
            </div>
            <div class="col-xl-2 col-lg-2 col-sm-12 col-xs-12 sidebar-shop-right">
                <div class="bannerScale">
                    <a href="#" title="Banenr Title">
                        <image src="./images/bannerFootball.png" style="width: 200px; height: 500px" alt="Banner Name"/>          
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>

<script type="text/javascript">
    $(window).scroll(function () {
        var scrollTop = $(window).scrollTop();
        if (scrollTop >= 100)
            $('.bannerScale').css('top', '120px');
        else
            $('.bannerScale').css('top', '340px');
    });
</script>


