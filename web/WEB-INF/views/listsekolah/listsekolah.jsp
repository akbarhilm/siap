<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/listsekolah/sekolahlistpopup.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        tampil();
    });

    function tampil() {
        grid();

    }
    function ambilskpd(id) {
        $('#sekolah', window.parent.document).val($("#namaSekolahPendek" + id).val()).change();
        $('#idSekolah', window.parent.document).val(id).change();
        $('#namaSekolah', window.parent.document).val($("#namaSekolah" + id).val()).change();
        $('#npsn', window.parent.document).val($("#npsn" + id).val()).change();
        $('#idsekolah', window.parent.document).val(id).change();
        //$("#sekolah", window.parent.document).val($("#npsn" + id).val()+" / "+$("#namaSekolahpendek" + id).val()).change();
        parent.$.fancybox.close();
    }
</script>

<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Sekolah</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">Kode NPSN:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="npsn"  id="npsn"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Sekolah:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="sekolah"  id="namasekolah"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    tampil()" />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='tampil()'>Cari</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="portlet box">

    <div >
        <table id="skpdtable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Kode</th>
                    <th>Nama</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table>
    </div>
</div>