<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a>
        <span class="icon-angle-right"></span>
    </li>

    <li><a href="#">Ubah Password</a></li>
</ul>

<form:form   method="post" commandName="refbku"  id="refbku"   action="" class="form-horizontal">
    <div onload="" class="portlet box tosca">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Ubah Password Pengguna</div>

        </div>

        <div class="portlet-body flip-scroll">


            <div class="form-group">
                <label class="col-md-3 control-label">Pengguna :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input name="pengguna" id="pengguna" type="text" value="${pengguna.namaPengguna}" readonly="true" size="25" />
                        <input type="hidden" id="passpengguna" name="passpengguna"  value="${pengguna.passPengguna}">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label  class="col-md-3 control-label">Password Lama :</label>
                <div class="col-md-4">
                    <form:password path="passwordLama" cssClass="m-wrap placeholder-no-fix required"  placeholder="Password Lama" id="passwordLama"  autocomplete="off" size="25" />
                    <form:errors path="passwordLama" cssClass="error" cssStyle="color:red"  />
                </div>
            </div>

            <div class="form-group">
                <label  class="col-md-3 control-label">Password Baru :</label>
                <div class="col-md-4">
                    <form:password path="passwordBaru" cssClass="m-wrap placeholder-no-fix required"  placeholder="Password Baru" id="passwordBaru"  autocomplete="off" size="25"  />  &nbsp 5-16 karakter
                    <form:errors path="passwordBaru" cssClass="error" cssStyle="color:red"  />
                </div>
            </div>

            <div class="form-group">
                <label  class="col-md-3 control-label">Ulangi Password Baru :</label>
                <div class="col-md-4">
                    <form:password path="passwordBaruVerify" cssClass="m-wrap placeholder-no-fix required"  placeholder="Ketik Ulang Password Baru " id="passwordBaruVerify"  autocomplete="off" size="25"  />
                    <form:errors path="*" cssClass="error" cssStyle="color:red"  />
                </div>
            </div>

        </div>
    </div>

    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9" >
            <button type="button" id="btnSimpan" class="btn blue" onclick='checkpassword()'>Simpan</button>

        </div>
    </div>
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/ubahpass/ubahpass.js"></script>

