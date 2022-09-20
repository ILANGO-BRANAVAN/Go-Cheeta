$(document).ready(function () {
    var userId = 0;
    userId = localStorage.getItem("userId");
    console.log("userid-".userId);
    if (userId == 0 || userId == undefined) {
        window.location.href = "login.html";
    }
    $.ajax({
        url: 'http://localhost:8085/vehicle/get/categories',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            if(data.statusCode == 1000){
                var obj = data.data;
                $.each(obj, function(i,v){
                    console.log(v.vehicleCategoryId);
                    console.log(v.vehicleCategoryName);

                    $('#vechileCatTable').find('tbody')
                        .append('<tr>')
                        .append('<td>' + v.vehicleCategoryId + '</td>')
                        .append('<td>' + v.vehicleCategoryName + '</td>')
                        .append('</tr>');
                });
            }else{
                alert('Error');
            }
        }
    });


});
