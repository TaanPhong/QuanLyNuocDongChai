
$(document).ready(function(){
    $("#table").on('click', '.btnXoa', function () {
        var id = $(this).val(); 
        var tmp= this.id; 
        $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "KhachHang/GioHang/delete.htm",
        data: {"id": id},
        dataType: "text",
        timeout: 40000,
        success: function (data) {
        $(`#${tmp}`).closest('tr').remove(); 
        document.getElementById("Total").innerHTML= data;
        },
        error: function (e) {
           console.log(e);
        }
    });        
})
})

