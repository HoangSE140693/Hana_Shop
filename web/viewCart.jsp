<%-- 
    Document   : viewOrder
    Created on : Jan 20, 2021, 9:33:22 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<c:if test="${not empty requestScope.AVAILABLE}">        
    <style>
        body {
            font-family: 'Varela Round', sans-serif;
        }
        .modal-confirm {
            color: #434e65;
            width: 525px;
        }
        .modal-confirm .modal-content {
            padding: 20px;
            font-size: 16px;
            border-radius: 5px;
            border: none;
        }
        .modal-confirm .modal-header {
            background: #e85e6c;
            border-bottom: none;
            position: relative;
            text-align: center;
            margin: -20px -20px 0;
            border-radius: 5px 5px 0 0;
            padding: 35px;
        }
        .modal-confirm h4 {
            text-align: center;
            font-size: 36px;
            margin: 10px 0;
        }
        .modal-confirm .form-control, .modal-confirm .btn {
            min-height: 40px;
            border-radius: 3px;
        }
        .modal-confirm .close {
            position: absolute;
            top: 15px;
            right: 15px;
            color: #fff;
            text-shadow: none;
            opacity: 0.5;
        }
        .modal-confirm .close:hover {
            opacity: 0.8;
        }
        .modal-confirm .icon-box {
            color: #fff;
            width: 95px;
            height: 95px;
            display: inline-block;
            border-radius: 50%;
            z-index: 9;
            border: 5px solid #fff;
            padding: 15px;
            text-align: center;
        }
        .modal-confirm .icon-box i {
            font-size: 58px;
            margin: -2px 0 0 -2px;
        }
        .modal-confirm.modal-dialog {
            margin-top: 80px;
        }
        .modal-confirm .btn, .modal-confirm .btn:active {
            color: #fff;
            border-radius: 4px;
            background: #eeb711 !important;
            text-decoration: none;
            transition: all 0.4s;
            line-height: normal;
            border-radius: 30px;
            margin-top: 10px;
            padding: 6px 20px;
            min-width: 150px;
            border: none;
        }
        .modal-confirm .btn:hover, .modal-confirm .btn:focus {
            background: #eda645 !important;
            outline: none;
        }
        .trigger-btn {
            display: inline-block;
            margin: 100px auto;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#myModalWarning").modal('show');
        });
    </script>
    <div id="myModalWarning" class="modal fade">
        <div class="modal-dialog modal-confirm">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <div class="icon-box">
                        <i class="material-icons">&#xE5CD;</i>
                    </div>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body text-center">
                    <h4>Ooops!</h4>	
                    <p>${requestScope.AVAILABLE}</p>
                    <button class="btn btn-success" data-dismiss="modal">Try Again</button>
                </div>
            </div>
        </div>
    </div>    
</c:if>
<c:if test="${sessionScope.shoppingCart == null}">
    <p class="text-center" style="
       font-size: xx-large;
       font-weight: bolder;">Don't have any product in cart</p>
</c:if>
<div class="cart-box-main">
    <c:if test="${sessionScope.shoppingCart != null}">
        <div class="container">
            <div class="row">

                <div class="col-lg-12">
                    <div class="table-main table-responsive">


                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Images</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Update</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${sessionScope.shoppingCart.shoppingCart.values()}">
                                <form action="MainController" method="POST">
                                    <tr>
                                        <td class="thumbnail-img">
                                            <img class="img-fluid"
                                                 src="${dto.getProduct().image}"
                                                 alt="" />
                                        </td>
                                        <td class="name-pr">
                                            ${dto.getProduct().name}
                                        </td>

                                        <td class="price-pr">
                                            <p>$ ${dto.getProduct().price}</p>
                                        </td>
                                        <td class="quantity-box"><input name="txtQuantity" type="number" size="4" value="${dto.quantityInCart}" min="1" step="1" max="${dto.getProduct().quantity}"
                                                                        class="c-input-text qty text" required></td>
                                        <td class="total-pr">
                                            <p>$ ${dto.getProduct().price * dto.quantityInCart}</p>
                                        </td>
                                        <td>
                                            <input type="hidden" name="productID" value="${dto.getProduct().productID}"/>
                                            <button type="submit" name="action" value="updateCart">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                        </td>
                                        <td>
                                            <a href="#myModal" data-toggle="modal">
                                                <i class="fas fa-times"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </form> 
                                <div id="myModal" class="modal fade">
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
                                            <div class="modal-footer justify-content-center" >
                                                <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">Cancel</button>
                                                <a style="color: white" class="btn btn-danger" href="MainController?action=deleteProductFromCart&productID=${dto.getProduct().productID}">
                                                    Delete
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <c:if test="${sessionScope.shoppingCart.getTotal()!=0}">
                <div class="row my-5">
                    <div class="col-lg-8 col-sm-12">
                        <div>
                            <div>
                                <h3 style="font-weight: bold">Enter Information Before Checkout</h3>
                            </div>
                            <form id="myForm" action="MainController?action=checkOut" method="POST">
                                <div class="form-group">
                                    <label for="Name">Name</label>
                                    <input type="text" name="name" class="form-control" placeholder="Full Name">
                                </div>
                                <div class="form-group">
                                    <label for="Email">Email</label>
                                    <input type="email" name="email" class="form-control" id="exampleInputPassword1" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <label for="Address">Address</label>
                                    <input type="text" name="address" class="form-control" placeholder="Address">
                                </div>
                                <div class="form-group">
                                    <label for="Phone">Phone</label>
                                    <input type="tel" name="phone" class="form-control" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" placeholder="Phone Number">
                                </div>
                                <button type="submit" class="btn btn-primary">Check Out</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-12"> 
                        <div class="order-box">
                            <h3>Order summary</h3>
                            <div class="d-flex">
                                <h4>Sub Total</h4>
                                <div class="ml-auto font-weight-bold"> $ ${sessionScope.shoppingCart.getTotal()} </div>
                            </div>
                            <div class="d-flex">
                                <h4>Shipping Cost</h4>
                                <div class="ml-auto font-weight-bold"> Free </div>
                            </div>
                            <hr>
                            <div class="d-flex gr-total">
                                <h5>Grand Total</h5>
                                <div class="ml-auto h5"> $ ${sessionScope.shoppingCart.getTotal()} </div>
                            </div>
                            <hr>
                        </div>
                        <div>
                            <div id="paypal-button-container"></div>
                        </div>
                    </div> 


                    <!--                    <div id="submitForm" class="col-12 d-flex shopping-box"><a href="MainController?action=checkOut"
                                                                                                   class="ml-auto btn hvr-hover">Check Out</a> </div>-->


                </div>
                <a class="ml-auto btn hvr-hover" href="MainController?action=viewProduct"><i class="fas fa-long-arrow-alt-left"></i> Back to shopping</a>
            </c:if>
        </div>
    </c:if>
</div>
<%@include file="footer.jsp" %>

<script
    src="https://www.paypal.com/sdk/js?client-id=AaJXqPCjyL6tQ6B7mLmjG-Ajgi-_XfL7G1QU0aJAIVX9hU8lMJvtyh2YVAYpZYybkmfLRJhfB2Mppcir"
    >
        // Required. Replace YOUR_CLIENT_ID with your sandbox client ID.
</script>

<script>
    $("#myForm").validate({
        rules: {
            name: "required",
            email: {
                required: true,
                email: true
            },
            address: "required",
            phone: {
                required: true,
                maxlength: 10,
                number: true
            }
        },

        messages: {
            name: "Please enter your full name",
            email: {
                required: "Please enter your email",
                email: "Please enter a valid email address!"
            },
            address: "Please enter your address",
            phone: {
                required: "Please provide a phone number",
                maxlength: "Your phone nummer max length is 10 numbers",
                number: "Please enter number"
            },
        },

        submitHandler: function (form) {
            form.submit();
        }
    });

//    $(document).ready(function () {
//        $("#submitForm").click(function () {
//            $("#myForm").submit(); // Submit the form
//        });
//    });
</script>

<script>
    paypal
            .Buttons({
                createOrder: function (data, actions) {
                    // This function sets up the details of the transaction, including the amount and line item details.
                    return actions.order.create({
                        purchase_units: [
                            {
                                amount: {
                                    value: '${sessionScope.shoppingCart.getTotal()}',
                                },
                            },
                        ],
                    });
                },
                onApprove: function (data, actions) {
                    // This function captures the funds from the transaction.
                    return actions.order.capture().then(function (details) {
                        // This function shows a transaction success message to your buyer.
                        alert('Transaction completed by ' + details.payer.name.given_name);
                        window.location.replace("./MainController?action=checkOut");
                    });
                },
            })
            .render('#paypal-button-container');
    //This function displays Smart Payment Buttons on your web page.
</script>

