<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/listpengguna/penggunalistpopup.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        grid();
    });

    
    function ambilskpd(id) {
        $('#pengguna', window.parent.document).val($("#namaPengguna" + id).val()).change();
        $('#idPengguna', window.parent.document).val(id).change();
        $ ('#kodeotor',window.parent.document).val($("#kodeOtoritas"+id).val()).change();
        //$('#namaSekolah', window.parent.document).val($("#namaSekolah" + id).val()).change();
        $('#nrk', window.parent.document).val($("#nrk" + id).val()).change();
        //$("#sekolah", window.parent.document).val($("#npsn" + id).val()+" / "+$("#namaSekolahpendek" + id).val()).change();
        parent.$.fancybox.close();
    }
</script>

<div class="portlet box tosca">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Pengguna</div>

    </div>
    <div class="portlet-body">
        <div class="form-horizontal" >
            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="nrk"  id="nrk"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    grid()" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Pengguna:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="namapengguna"  id="namapengguna"    class="form-control " size="30" onkeyup="if (event.keyCode == 13)
                                    grid()" />
                    </div>
                </div>
            </div>
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
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
                    <th>NRK</th>
                    <th>Nama</th>
                    <th>Sekolah</th>
                    <th>Pilih</th>
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table>
    </div>
</div>