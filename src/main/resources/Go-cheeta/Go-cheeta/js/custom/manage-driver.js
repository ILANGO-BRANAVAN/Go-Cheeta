$(document).ready(function () {
    var userId = 0;
    userId = localStorage.getItem("userId");
    console.log("userid-".userId);
    if (userId == 0 || userId == undefined) {
        window.location.href = "login.html";
    }

    // FOR GET ALL DRIVERS AND APPEND IN TABLE --START
    $.ajax({
        url: 'http://localhost:8086/vehicle/get/categories',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            if(data.statusCode == 1000){
                var obj = data.data;
                $.each(obj, function(i,v){
                    // console.log(v.vehicleCategoryId);
                    // console.log(v.vehicleCategoryName);

                    $('#vechileCatTable').find('tbody')
                        .append('<tr>')
                        .append('<td>' + v.vehicleCategoryId + '</td>')
                        .append('<td>' + v.vehicleCategoryName + '</td>')
                        .append('</tr>');


                    $('#vehicleCategoryId').append('<option value="' + v.vehicleCategoryId + '">' + v.vehicleCategoryName + '</option>');
                });
            }else{
                alert('Error');
            }
        }
    });
    // FOR GET ALL DRIVERS AND APPEND IN TABLE --END
// FOR GET VEHICLE DETAILS AND APPEND IN DROPDOWN --START
    $.ajax({
        url: 'http://localhost:8086/vehicle/get/detail/list',
        type: 'POST',
        dataType: 'json',
        success: function (data) {
            if(data.statusCode == 1000){
                var obj = data.data;
                var x=1;
                $('#vehicleTable').find('tbody').html("");
                $.each(obj, function(i,v){
                    //console.log("vechile id is " + v.vehicleId);
                    var vehicle=v.brandName+"-"+v.modelName
                    $('#vehicleDetailId').append('<option value="' + v.vehicleId + '">' + vehicle + '</option>');

                    x++;
                });
            }else{
                alert('Error');
            }
        }
    });
// FOR GET VEHICLE DETAILS AND APPEND IN DROPDOWN --END
});

// FOR CREATE DRIVER  --START
$('#createDriver').submit(function(e){
    console.log('start');
    var formData = $("#createDriver").serialize();
    e.preventDefault();

    var formData = $(this).serializeArray();
    var values = {};
    $.map(formData, function(n, i){
        values[n['name']] = n['value'];
    });

    console.log(values);

    $.ajax({
        url: 'http://localhost:8086/create/customer',
        type: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        dataType:'json',
        data: JSON.stringify(values),
        success: function (data) {
            console.log(data);
            if(data.statusCode == 1000){
                alert(data.message);
                location.reload();
            }
        }
    });
    console.log('end');
});
// FOR CREATE DRIVER  --END
