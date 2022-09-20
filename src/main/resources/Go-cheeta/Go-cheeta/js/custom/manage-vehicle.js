$(document).ready(function () {
    var userId = 0;
    userId = localStorage.getItem("userId");
    console.log("userid-".userId);
    if (userId == 0 || userId == undefined) {
        window.location.href = "login.html";
    }

    // FOR GET VEHICLE CATEGORY --START
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
    // FOR GET VEHICLE CATEGORY --END

    // FOR GET VEHICLE DETAILS --START
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
                    $('#vehicleTable').find('tbody')
                        .append('<tr>')
                        .append('<td>' + x + '</td>')
                        .append('<td>' + v.brandName + '</td>')
                        .append('<td>' + v.modelName + '</td>')
                        .append('<td>' + v.vehicleCategoryName + '</td>')
                        .append('</tr>');
                    x++;
                });
            }else{
                alert('Error');
            }
        }
    });
    // FOR GET VEHICLE DETAILS --END

    // FOR CREATE VEHICLE CATEGORY --START
    $('#createVeCategory').submit(function(e){
        console.log('start');
        var formData = $("#createVeCategory").serialize();
        e.preventDefault();

        var formData = $(this).serializeArray();
        var values = {};
        $.map(formData, function(n, i){
            values[n['name']] = n['value'];
        });

        console.log(values);

        $.ajax({
            url: 'http://localhost:8085/vehicle/create/category',
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
    // FOR CREATE VEHICLE CATEGORY --END


    // FOR CREATE VEHICLE  --START
    $('#creteVehicle').submit(function(e){
        console.log('start');
        var formData = $("#creteVehicle").serialize();
        e.preventDefault();

        var formData = $(this).serializeArray();
        var values = {};
        $.map(formData, function(n, i){
            values[n['name']] = n['value'];
        });

        console.log(values);

        $.ajax({
            url: 'http://localhost:8086/vehicle/create/detail',
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
    // FOR CREATE VEHICLE  --END
});
