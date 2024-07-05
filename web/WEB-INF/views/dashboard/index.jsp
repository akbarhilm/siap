<div class="row-fluid">
    <div class="span12">
        <h4 class="page-title">
            Sistem Informasi Akuntabilitas Pendidikan
            <!--<small>Sistem Informasi Pengelolaan Keuangan Daerah</small>-->
        </h4>
        <ul class="breadcrumb">
            <li><a href="#">HOME</a></li>
        </ul>

    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <div class="portlet">
            <img src="${pageContext.request.contextPath}/static/img/logo_grey.png" style="width: auto; height: 80%; position: absolute;
                 right: 0;
                 left: 0;
                 top: 50px;
                 bottom: 0;
                 margin: auto auto;"   >
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
//        getsisahari();
    });
    function getsisahari() {
        $.getJSON(getbasepath() + "/beranda/json/sisahari", {},
                function(result) {
                    if (result != null) {
                        if (result <= 5 && result > 0) {
                            bootbox.alert("Masa berlaku password anda adalah " + result + " hari lagi, silahkan ganti password untuk memperpanjang masa berlaku");
                        } else if (result <= 0) {
                            bootbox.alert("Masa berlaku password anda sudah habis, silahkan ganti password untuk memperpanjang masa berlaku");
                        }
                    }
                });
    }
</script>