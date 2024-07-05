<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html class="no-js lt-ie9" lang="en" > <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en" > <!--<![endif]-->

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="SIAP - BOP|BOS"/>
        <meta name="author" content=""/>
        <link rel="shortcut icon" href="favicon.png">

        <title>SIAP - BOP|BOS</title>
        <!--
            <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/login.css" rel="stylesheet">
        <link href="css/animate-custom.css" rel="stylesheet">
            <link href="css/icon.css" rel="stylesheet" />

            <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
            <script type="text/javascript" src="js/jquery.form.js"></script>
            <script type="text/javascript" src="js/navigation.js"></script>
            <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        -->

        <!--CSS-->
        <link href="${pageContext.request.contextPath}/static/assets/css/css/animate-custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/css/icon.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/pages/image-popup.css" rel="stylesheet" type="text/css"/>

        <!--JS-->
        <script src="${pageContext.request.contextPath}/static/js/js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/js/js/jquery.form.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/js/js/navigation.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/js/js/jquery-ui-1.8.18.custom.min.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
        <%-- idns , buat captcha  --%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>


        <script>
            var app_opt = {
                target: '#page-inner',
                beforeSubmit: function () {
                },
                success: function () {
                },
                url: 'index/',
                type: 'post',
                resetForm: false
            }
        </script>

        <style>
            .bg-login {
                //background-image: url("../images/bg-login1.jpg");
                background-repeat: no-repeat;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            .login-box {
                background-color: rgba(255,255,255,0.2);
                background-image: linear-gradient(to bottom, rgba(66, 139, 202, 0.7) 0, rgba(0, 0, 0, 0.0001) 50%);
                padding:30px;
                box-shadow:0;
                -webkit-box-shadow: 2px 2px 5px transparent;
                border-radius:3px;
            }

            .login-box h3{
                font-weight:bold;
                text-align:center;
                margin-left:0;
                font-size:23px;
                text-shadow:0;
            }

            .login-logo {
                text-align: center;
                padding: 20px 0 0;
            }

            .login-box p{
                font-family:Stencil Std	;
                text-align:center;
                margin-left:0;
                font-size:23px;
                color:#2073bb;
                background:transparent;
                background-image: linear-gradient(to bottom, rgba(66, 139, 202, 0.5) 0, rgba(0, 0, 0, 0.001) 80%);
                padding-top:10px;
                border-radius:1px;
            }

            .page-icon{
                box-shadow: inset 1px 3px 35px #428bca;
                border: 0;
            }
            .page-icon img {
                vertical-align: top;
                padding:1px 0 0 1px;
            }
        </style>


    </head>
    <body class="bg-login">
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate var="year" value="${now}" pattern="yyyy" />
        <div class="container" id="login-block" style="background:transparent">
            <!-- Trigger the Modal -->
            <img id="myImg" src="${pageContext.request.contextPath}/static/assets/img/logo_e-sipkd-big.png" width="3" height="2">
            <!-- The Modal -->
            <div id="myModal" class="modal">

                <!-- The Close Button -->
                <span class="close" onclick="document.getElementById('myModal').style.display = 'none'">&times;</span>

                <!-- Modal Content (The Image) -->
                <img class="modal-content" id="img01">

                <!-- Modal Caption (Image Text) -->
                <div id="caption"></div>
            </div>

            <div class="row">
                <div class="col-sm-6 col-md-4 col-sm-offset-4 col-md-offset-4">

                    <div class="login-box clearfix animated flipInY">
                        <div class="page-icon animated bounceInDown">
                            <img class="img-responsive" src="${pageContext.request.contextPath}/static/assets/css/images/logo.png" alt="Key icon" />
                        </div>
                        <div class="login-logo">

                            <h3>Sistem Informasi Akuntabilitas Pendidikan</h3>
                            <?php
                            //$username  = $_REQUEST['username'];
                            //$password  = $_REQUEST['password'];
                            ?>
                        </div>
                        <hr />
                        <form:form id="formlogin" action="${pageContext.request.contextPath}/login" method="POST"  modelAttribute="loginForm">
                            <h3 class="form-title">Login</h3>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-error">
                                    <!--<button type="button" class="close" data-dismiss="alert">&times;</button>-->
                                    <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                        <span id="errormessage"><strong>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong></span>
                                            </c:if>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    Anda telah log out.
                                </div>
                            </c:if>

                            <div style="margin-bottom: 15px" class="input-group">
                                <span class="input-group-addon"> <i class="glyphicon glyphicon-user"></i></span>
                                    <form:input path="username" placeholder="Username" autocomplete="off" class="form-control" id="username" />
                            </div>
                            <div style="margin-bottom: 15px" class="input-group">
                                <span class="input-group-addon"> <i class="glyphicon glyphicon-lock"></i></span>
                                    <form:input path="password" placeholder="Password" type="password" autocomplete="off" class="form-control" id="password" />

                            </div>
                            <div class="form-group">
                                <label class="control-label visible-ie8 visible-ie9">Tahun Anggaran</label>
                                <div style="margin-bottom: 15px" class="input-icon">
                                    <!--<div style="margin-bottom: 15px" class="input-group">-->
                                    <jsp:useBean id="skrg" class="java.util.Date" />
                                    <fmt:formatDate var="tahun" value="${skrg}" pattern="yyyy" />
                                    <form:select id="tahun" path="tahun" cssClass="m-wrap placeholder-no-fix" placeholder="Tahun Anggaran" cssStyle="width:100%;height:30px;" >
                                        <!--<option ${tahun - 2} >${tahun - 2}</option>-->
                                        <option ${tahun - 1} >${tahun - 1}</option>
                                        <option ${tahun} selected>${tahun}</option>

                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group"> <%-- idns , 08-11-2017 --%>
                                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                                <div class="">
                                    <input type="text"  name="captchaCode" id="captchaCode" cssClass="m-wrap placeholder-no-fix  required"  placeholder="Kode Verifikasi"  size="22"   autocomplete="off" cssStyle="width:100%;height:30px;" onkeypress="cekcaptcha(this.input)"  />

                                    <table cellpadding="4" id="captca">
                                        <tr><td ><span id="numb1" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive; font-weight: 600; font-stretch: expanded; "></span></td>
                                            <td><span id="numb2" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded; "></span></td>
                                            <td><span id="numb3" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded;"></span></td>
                                            <td><span id="numb4" cssClass="m-wrap placeholder-no-fix required" style="width: 100%;font-family: cursive;font-weight: 600; font-stretch: expanded; "></span></td>
                                        </tr>


                                    </table>

                                    <%--&nbsp; <a onclick='getrandomnum()' ><i class="fa fa-refresh"></i> </a> --%>
                                    &nbsp; <a onclick='getrandomnum()' ><i class="glyphicon glyphicon-refresh"></i> </a>

                                </div>

                            </div>
                            <div class="form-actions">
                                <!--<a href="javascript:veri();" >
                                    <button class="btn btn-primary btn-block" onclick="veri()">Login <i class="icon-enter"></i></button>
                                -->
                                <!--</a>-->
                                <div class="form-actions">
                                    <a href="javascript:veri();" style="text-decoration: none;" class="btn btn-primary btn-block">
                                        Login <i class="m-icon-swapright m-icon-white"></i>
                                    </a>
                                </div>
                                <div class="form-actions">
                                    <div class="alert alert-success" style="opacity: .75">
                                        <p style="opacity: 1;font-size: 12px;font-family: inherit;  color: #3c763d;
                                           background-color: #dff0d8;">
                                            Waktu Operasional Pembayaran : <br>
                                            Senin - Jumat pukul 07.00 - 16.00 (Non Bank DKI)
                                            Senin - Jumat pukul 07.00 - 21.00 (Bank DKI)</p>
                                    </div>
                                </div>

                                <!--<button type="submit" class="btn btn-primary btn-block"><i class="icon-enter"></i>&nbsp;&nbsp;LOGIN</button> -->
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
            <!--<script type="text/javascript">
                $(document).ready(function() {
                    $("#formlogin").validate();
                    /*  var token = $("meta[name='_csrf']").attr("content");
                     var header = $("meta[name='_csrf_header']").attr("content");
                     $(document).ajaxSend(function (e, xhr, options) {
                     xhr.setRequestHeader(header, token);
                     });*/
                });

            </script> -->

            <%-- idns , 08-11-2017 [--%>
            <script>
                $(document).ready(function () {
                    $("#formlogin").validate();
                    getrandomnum();
                    popup();
                });

                var a;
                var b;
                var c;
                var d;
                var e;
                var f;

                function popup() {
                    var modal = document.getElementById('myModal');
                    var img = document.getElementById('myImg');
                    var modalImg = document.getElementById("img01");
                    var captionText = document.getElementById("caption");

                    img.src = "/BKUS/static/img/alert.jpg";

                    modal.style.display = "block";
                    modalImg.src = img.src;
                    captionText.innerHTML = img.alt;


// Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";
                        img.style.display = "none";
                    };

                }
                function getrandomnum() {
                    a = Math.round(Math.random() * (9 - 0));
                    b = Math.round(Math.random() * (9 - 0));
                    c = Math.round(Math.random() * (9 - 0));
                    d = Math.round(Math.random() * (9 - 0));
                    e = Math.round(Math.random() * (6 - 0) + 1);
                    f = a.toString() + b.toString() + c.toString() + d.toString();
                    document.getElementById("numb1").innerHTML = a;
                    document.getElementById("numb2").innerHTML = b;
                    document.getElementById("numb3").innerHTML = c;
                    document.getElementById("numb4").innerHTML = d;

                    document.getElementById("captca").style = "width:35%;background-image:url('${pageContext.request.contextPath}/static/assets/img/capca" + e + ".jpg'); float:right;"
                }
                function veri() {

                    var b = $("#captchaCode").val();
                    if (f !== b) {
                        alert("Kode Verifikasi Salah");
                        console.log("salah");
                        window.location = "${pageContext.request.contextPath}/login";

                    } else {
                        $("#formlogin").submit();
                    }

                }

                function cekcaptcha(evt) {
                    var e = evt || window.event;
                    var key = e.keyCode || e.which;

                    if (!e.shiftKey && !e.altKey && !e.ctrlKey && key == 13) {
                        veri();
                    }
                }

            </script>

            <%-- idns , 08-11-2017 --%>

    </body>
</html>
