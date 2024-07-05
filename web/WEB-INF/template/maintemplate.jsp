<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8" />
        <title>SIAP - BOP|BOS</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <%/*  <meta name="_csrf" content="${_csrf.token}"/>
             <meta name="_csrf_header" content="${_csrf.headerName}"/> */ %>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME STYLES -->
        <link href="${pageContext.request.contextPath}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/static/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
        <!-- <link href="${pageContext.request.contextPath}/static/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/> -->
        <link href="${pageContext.request.contextPath}/static/assets/css/custom.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-datepicker/css/datepicker.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/assets/plugins/fancybox/source/jquery.fancybox.css" />

        <!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/static/assets/plugins/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/static/assets/plugins/respond.min.js"></script>
<![endif]-->
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/scripts/hogan-2.0.0.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/data-tables/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/data-tables/DT_bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/scripts/maskedinput.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-serialize/jquery.serialize-object.compiled.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-arithmetics/jquery.basic_arithmetics.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-date/moment.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootsbox/bootbox.min.js"></script>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/ico/money.png" />
    </head>
    <body class="page-header-fixed page-full-width" >
        <!-- BEGIN HEADER -->
        <div id='header' class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="navbar-inner">

                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                    <img style="position: relative; right: 10px; bottom: 12px" src="${pageContext.request.contextPath}/static/assets/img/logo_siap.png"  alt="logo"   class="img-responsive" />
                </a>
                <c:set var="sekolah" value="${pengguna.sekolah}" scope="session" />
                <!-- BEGIN HORIZANTAL MENU -->
                <div class="hor-menu nav-collapse">
                    <ul class="nav navbar-nav">
                        <c:forEach items="${pengguna.menu}" var="menu">
                            <li>
                                <a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" class="dropdown-toggle" href="javascript:;">
                                    <span class="selected"></span>
                                    <c:out value="${menu.nama}" />
                                    <i class="icon-angle-down"></i>
                                </a>

                                <ul class="dropdown-menu">
                                    <c:forEach items="${menu.menu}" var="node">
                                        <c:set var="node" value="${node}" scope="request" />
                                        <c:choose>
                                            <c:when test="${node.kodeAdaSubMenu == 'Y'}">
                                                <jsp:include page="menu.jsp" />
                                            </c:when>
                                            <c:otherwise>
                                                <li><a href="${pageContext.request.contextPath}<c:out value='${node.link}' />"><c:out value='${node.nama}' /> </a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                </ul>
                            </li>
                            <c:remove var="node" scope="request"/>
                        </c:forEach>
                    </ul>
                </div>
                <!-- END HORIZANTAL MENU -->                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
                    <img src="${pageContext.request.contextPath}/static/assets/img/menu-toggler.png" alt="" />
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <ul class="nav navbar-nav pull-right">
                    <!-- BEGIN NOTIFICATION DROPDOWN -->


                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                            <span class="username">TA : ${tahunAnggaran}</span>|<span class="username">${sekolah.namaSekolahPendek}</span> / <span class="username">${pengguna.namaPengguna} </span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a  href="${pageContext.request.contextPath}/ubahpass/index"><i style="display:inline" class="icon-key"></i> Ganti Password</a></li>
                            <c:if test="${pengguna.kodeOtoritas==0}"  ><li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SIAP_Sudin.pdf"><i class="icon-key"></i> User Manual </a></li></c:if>
                            <c:if test="${pengguna.kodeOtoritas==1}"  ><li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SIAP_PA.pdf"><i class="icon-key"></i> User Manual </a></li></c:if>
                            <c:if test="${pengguna.kodeOtoritas==2}"  ><li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SIAP_PK.pdf"><i class="icon-key"></i> User Manual </a></li></c:if>
                            <c:if test="${pengguna.kodeOtoritas==8}"  ><li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SIAP_Admin.pdf"><i class="icon-key"></i> User Manual </a></li></c:if>
                            <c:if test="${pengguna.kodeOtoritas==9}"  ><li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SIAP_Super Admin.pdf"><i class="icon-key"></i> User Manual </a></li></c:if>
                                <li class="dropdown-submenu">
                                    <a href="#"><i class="icon-key"></i> Bahan Paparan <span class="caret" style="border-top:4px solid #999999"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/static/pdf/2018_SIAP_BOS_BOP_13_Maret_2018.pdf"><i class="icon-key"></i> Bahan Paparan Tanggal 13 Maret 2018 (PDF) </a></li>
                                    <li><a href="${pageContext.request.contextPath}/static/pdf/2018_SIAP_Flow_20180801.pdf"><i class="icon-key"></i> Bahan Paparan ToT 1 Agustus 2018 (PDF) </a></li>
                                    <li><a href="${pageContext.request.contextPath}/static/ppt/2018_SIAP_Flow_20180801.ppt"><i class="icon-key"></i> Bahan Paparan ToT 1 Agustus 2018 (PPT) </a></li>
                                    <li><a href="${pageContext.request.contextPath}/static/pdf/Sosialisasi_bendahara_nov_2018.pdf"><i class="icon-key"></i> Sosialisasi Pajak untuk Bendahara </a></li>
                                </ul></li>
                            <li><a href="${pageContext.request.contextPath}/static/pdf/Perpajakan_bendahara_pemerintah_2017.pdf"><i class="icon-key"></i> Perpajakan Bendahara Pemerintah Tahun 2017 </a></li>
                            <li><a href="${pageContext.request.contextPath}/logout"><i class="icon-key"></i> Log Out</a></li>
                            <!-- <li><a href="${pageContext.request.contextPath}/static/pdf/Petunjuk_Penggunaan_SPJ.pdf"><i class="icon-key"></i> User Manual </a></li>
                            <li><a href="${pageContext.request.contextPath}/static/pdf/PROSES_SIPKD_PENATAUSAHAAN.pdf"><i class="icon-key"></i> PROSES SIPKD PENATAUSAHAAN </a></li>
                            -->
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        <!-- END HEADER -->
        <div class="clearfix"></div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container" >
            <!-- BEGIN PAGE -->
            <div class="page-content">

                <!-- end sidebar-->
                <sitemesh:write property='body'/>
                <!-- END PAGE CONTENT-->
            </div>
            <!-- END PAGE -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER
        <div class="footer">  -->
        <%  /*    <div class="footer-inner">
             2014 &copy; Diskominfomas Pemprov DKI Jakarta.
             </div>
             <div class="footer-tools">
             <a class="go-top" href="#">
             <i class="icon-angle-up"></i>
             </a>
             </div>
             </div>*/ %>
        <!-- END FOOTER -->
        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/static/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/scripts/maskedinput.js"></script>
        <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-timer/jquery.timer.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/akunting.js"></script>
        <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/autoNumeric-2.0-BETA.js"></script>
        <script type='text/javascript' src="${pageContext.request.contextPath}/static/assets/plugins/jquery-numberformat/numericpack.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/scripts/app.js"></script>
        <script src="${pageContext.request.contextPath}/static/assets/scripts/sidebar.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {

                // App.init();
                /* var token = $("meta[name='_csrf']").attr("content");
                 var header = $("meta[name='_csrf_header']").attr("content");
                 $(document).ajaxSend(function (e, xhr, options) {
                 xhr.setRequestHeader(header, token);
                 });*/
                var nowTemp = new Date();
                var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
                $(".entrybulan").mask("99/" + ${sessionScope.tahunAnggaran});
                $(".dropdown-toggle").dropdown();
                $("#dp1").datepicker({endDate: "+0d", language: "id", autoclose: "true", todayHighlight: "true"});
                $("#dp2").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp3").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp4").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp5").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp6").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp7").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp8").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp9").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#dp10").datepicker({endDate: "+0d", language: "id", autoclose: "true"});
                $("#loading").append("<div class='loadingAjax' id='ajaxBusy'><p><img src='${pageContext.request.contextPath}/static/ico/ajaxLoading.gif'></p></div>");
                $(document).ajaxStart(function () {
                    $("#ajaxBusy").show()
                }).ajaxStop(function () {
                    $("#ajaxBusy").hide()
                });
                $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
                //  $(".inputinvoice").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
//                    $.validator.addMethod("accept", function(value, element, param) {
//                        return value.match(new RegExp("^" + param + "$")) || value == ""
//                    }, "Hanya boleh huruf dan angka!");
                $.validator.addMethod("ruleWajib", function (value, element) {
                    console.log(value);
                    return parseFloat(accounting.unformat(value)) > 0
                }, "Wajib diisi .");
                $.validator.addMethod("ruleA03", function (value, element) {
                    return this.optional(element) || /^[0-9.,]+$/.test(value)
                }, "Hanya boleh menggunakan angka dan simbol .");
                $.validator.addMethod("ruleA04", function (value, element) {
                    return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value)
                }, "Hanya boleh menggunakan huruf dan angka");
                $.validator.addMethod("ruleA05", function (value, element) {
                    return this.optional(element) || /^[a-zA-Z0-9/.,+: '"()-]*$/i.test(value)
                }, "Hanya boleh menggunakan huruf, angka dan simbol .,''':+-/() spasi");
                $.validator.addMethod("ruleAlamat", function (value, element) {
                    return this.optional(element) || /^[AC-IK-NP-TVWY\r\sa-zA-Z0-9/.,+: '"()-]*$/i.test(value)
                }, "Hanya boleh menggunakan huruf, angka dan simbol .,''':+-/() spasi enter");
                $.validator.addMethod("ruleCekKabupaten", function (value, element) {
                    return eval(value) > 0
                }, "Wajib diisi .");
                $.validator.addMethod("indoDate", function (value, element) {
                    return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/) || value == ""
                }, " format hanya boleh dd/mm/yyyy.");
                $.validator.addMethod("ruleCekBulan", function (value, element) {
                    var arrbulan = $.trim(value).length > 0 ? value.split("/") : "";
                    if (arrbulan.length == 2)
                        return eval(arrbulan[0]) > 0 && eval(arrbulan[0]) < 13
                    else
                        false;
                }, "Wajib diisi dengan bulan antara 01 - 12.");
                $.validator.addMethod("ruleCekBulanSekarang", function (value, element) {
                    var arrbulan = $.trim(value).length > 0 ? value.split("/") : "";
                    var tgl = new Date();
                    var bulan = tgl.getMonth() + 1
                    if (arrbulan.length == 2)
                        return eval(arrbulan[0]) > 0 && eval(arrbulan[0]) < 13 && eval(arrbulan[0]) <= bulan
                    else
                        false;
                }, "Wajib diisi dengan bulan antara 01 - 12, dan tidak boleh lebih besar dari bulan sekarang.");
                $.validator.addMethod("bulanLebihBesar",
                        function (value, element, params) {
                            var valBulanAwal = moment("01/" + value, "DD/MM/YYYY");
                            var valBulanAkhir = moment("01/" + $(params).val(), "DD/MM/YYYY");
                            return   (valBulanAwal.diff(valBulanAkhir, 'days') >= 0);
                        }, 'Harus lebih besar dari {0}  ');
                $.validator.addMethod("angkaLebihBesar",
                        function (value, element, params) {
                            console.log(" angkaLebihBesar ");
                            var nilaiSpd = accounting.unformat($(params).val(), ",");
                            var nilaiangg = accounting.unformat(value, ",");
                            return   (nilaiSpd > nilaiangg);
                        }, 'Nilai SPD harus lebih kecil dari nilai anggaran!');
                $.validator.addMethod("ruleCekNamaRekanan", function (value, element) {

                    var namarekan = value.toLowerCase();
                    var dkinamarekan; //= $("#dkinama").val().toLowerCase();
                    var kodeva = $("#kodeVA").val();
                    if ($("#kodeBank").val() !== "111" || kodeva == 1) {
                        dkinamarekan = namarekan;
                    } else {
                        dkinamarekan = $("#dkinama").val().toLowerCase();
                    }
                    console.log("ruleCekNamaRekanan dkinamarekan = " + dkinamarekan);
                    console.log("ruleCekNamaRekanan namarekan = " + namarekan);
                    return namarekan == dkinamarekan;
                }, "Nama Harus Sesuai dengan Nama dari Data Bank DKI");
                $.validator.addMethod("ruleCekNamaTujuan", function (value, element) {

                    var namatujuan = value.toLowerCase();
                    var cek; //= $("#dkinama").val().toLowerCase();

                    if (namatujuan == "" || namatujuan == null) {
                        cek = "kosong";
                    } else {
                        cek = namatujuan;
                    }
                    console.log("namatujuan = " + namatujuan);
                    console.log("cek = " + cek);
                    return namatujuan == cek;
                }, "Nama Tujuan Tidak Boleh Kosong");
                $.validator.addMethod("ceknourutltklexist", function (value, element) {
                    var result = false;
                    var noltklself = "${noltkl}";
                    if ($.trim(noltklself).length > 0) {
                        var arrltkl = noltklself.split("-");
                        result = arrltkl[2] == value ? true : false
                    }
                    if (!result) {
                        $.ajax({type: "POST", async: false, url: "${pageContext.request.contextPath}/historyltkl/json/ceknoltklisexist", data: {noltklsegmendua: value}, success: function (data) {
                                result = data
                            }})
                    }
                    return result
                }, "No urut   Sudah Digunakan");
                formatnumberonkeyup();
                accounting.settings = {currency: {symbol: "Rp", format: "%s%v", decimal: ",", thousand: ".", precision: 2}, number: {precision: 0, thousand: ".", decimal: ","}}
                $(".date-picker").datepicker({endDate: "+0d", language: "id", autoclose: "true", todayHighlight: "true"});
            <%



                final java.util.Date currentDate = new java.util.Date();
                final java.text.SimpleDateFormat DATE_FORMAT = new java.text.SimpleDateFormat("dd/MM/yyyy");
                final String date = DATE_FORMAT.format(currentDate);
            %>
                $(".date-picker").datepicker({
                    dateFormat: "dd/mm/yyyy",
                    "setDate": '<%=date%>',
                    "autoclose": true,
                    language: "id"

                });
                $(".date-picker").click(function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                });
                // $(".date-picker").val(myDate);
                $(".date-picker2").datepicker({language: "id", autoclose: "true", todayHighlight: "true"});
                $(".entrytanggal").mask('<%=date%>');
                $(".entrytanggal2").mask('99/99/9999');
            });
            $(document).ajaxStart(function () {
                $("#ajaxBusy").show()
            }).ajaxStop(function () {
                $("#ajaxBusy").hide()
            });
            function IsNumeric(b) {
                b = parseInt(b);
                return(b - 0) == b && b.length > 0
            }
            function popupwindow(i, k, j, h) {
                var l = (screen.width / 2) - (j / 2);
                var g = (screen.height / 2) - (h / 2);
                return window.open(i, k, "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width=" + j + ", height=" + h + ", top=" + g + ", left=" + l)
            }
            function preventBack() {
                window.history.forward()
            }
            function settextbootalert(d, f, e) {
                $("#" + f).empty();
                $("#" + d).show();
                $("#" + f).html(e)
            }
            function closewindow() {
                /* if (screenfull.enabled) {
                 screenfull.toggle(this);
                 }*/
            }
            function getbasepath() {
                return '${pageContext.request.contextPath}';
            }
            function formatnumberonkeyup() {
                $('.inputinvoice').autoNumeric('init', {aSep: '.', aDec: ','});
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            }
            function dateformatstring(format, date, newformat) {
                /*
                 format type:
                 ddslashfull - DD/MM/YYYY hh24:mm:ss
                 ddslashfirst - DD/MM/YYYY
                 mmslashfull - MM/DD/YYYY hh24:mm:ss
                 mmslash - MM/DD/YYYY
                 dddashfull - DD/MM/YYYY hh24:mm:ss
                 dddashfirst - DD/MM/YYYY
                 mmdashfull - MM/DD/YYYY hh24:mm:ss
                 mmdash - MM/DD/YYYY
                 dposting - yyyymmdd
                 */

                var dd, mm, yyyy, h, m, s;
                switch (format) {
                    case 'ddslashfull':
                    case 'dddashfull':
                        dd = date.substr(0, 2);
                        mm = date.substr(3, 2);
                        yyyy = date.substr(6, 4);
                        h = date.substr(11, 2);
                        m = date.substr(14, 2);
                        s = date.substr(17, 2);
                        break;
                    case 'ddslash':
                    case 'dddash':
                        dd = date.substr(0, 2);
                        mm = date.substr(3, 2);
                        yyyy = date.substr(6, 4);
                        break;
                    case 'mmslashfull':
                    case 'mmdashfull':
                        dd = date.substr(0, 2);
                        mm = date.substr(3, 2);
                        yyyy = date.substr(6, 4);
                        h = date.substr(11, 2);
                        m = date.substr(14, 2);
                        s = date.substr(17, 2);
                        break;
                    case 'mmslash':
                    case 'mmdash':
                        dd = date.substr(0, 2);
                        mm = date.substr(3, 2);
                        yyyy = date.substr(6, 4);
                        break;
                    case 'dposting':
                        dd = date.substr(6, 2);
                        mm = date.substr(4, 2);
                        yyyy = date.substr(0, 4);
                        break;
                    default:
                        break;
                }
                switch (newformat) {
                    case 'ddslashfull':
                        return dd + '/' + mm + '/' + yyyy + ' ' + h + ':' + m + ':' + s;
                    case 'dddashfull':
                        return dd + '-' + mm + '-' + yyyy + ' ' + h + ':' + m + ':' + s;
                    case 'ddslash':
                        return dd + '/' + mm + '/' + yyyy;
                    case 'dddash':
                        return dd + '-' + mm + '-' + yyyy;
                    case 'mmslashfull':
                        return mm + '/' + dd + '/' + yyyy + ' ' + h + ':' + m + ':' + s;
                    case 'mmdashfull':
                        return mm + '-' + dd + '-' + yyyy + ' ' + h + ':' + m + ':' + s;
                    case 'mmslash':
                        return mm + '/' + dd + '/' + yyyy;
                    case 'mmdash':
                        return mm + '-' + dd + '-' + yyyy;
                    case 'dposting':
                        return yyyy + mm + dd;
                    default:
                        break;
                }
            }

            function deletesession() {
                var varurl = getbasepath() + "/beranda/json/deletesession";
                var dataac = [];

                var datajour = {
                };
                dataac = datajour;
                return   $.ajax({
                    type: "POST",
                    url: varurl,
                    contentType: "text/plain; charset=utf-8",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(dataac)
                }).always(function (data) {
                    window.location.href = getbasepath() + "/logout";
                });
                window.location.href = getbasepath() + "/logout";
            }

        </script>
    </body>
</html>
