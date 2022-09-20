

//LOGIN FUNCTION -START
$('#loginForm').submit(function (e) {
    console.log('start');

    var txtInputEmail = $("#txtInputEmail").val();
    var txtInputPassword = $("#txtInputPassword").val();
    //VALIDATION START
    if (txtInputEmail == "") {
        $("#txtInputEmail").focus();
        $.alert({
            title: "Alert!",
            content: "Please Insert User Mail address.",
            icon: "fa fa-exclamation-triangle",
            type: "red",
            buttons: {
                okay: {
                    text: "Okay",
                    btnClass: "btn-red",
                },
            },
        });
        return false;
    }

    if (txtInputPassword == "") {
        $("#txtInputPassword").focus();
        $.alert({
            title: "Alert!",
            content: "Please Insert Password.",
            icon: "fa fa-exclamation-triangle",
            type: "red",
            buttons: {
                okay: {
                    text: "Okay",
                    btnClass: "btn-red",
                },
            },
        });
        return false;
    }
    //VALIDATION END

    var formData = $("#loginForm").serialize();
    e.preventDefault();

    var formData = $(this).serializeArray();
    var values = {};
    $.map(formData, function (n, i) {
        values[n['name']] = n['value'];
    });

    $.ajax({
        url: 'http://localhost:8085/login/user',
        type: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        dataType: 'json',
        data: JSON.stringify(values),
        success: function (data) {
            console.log(data);
            if (data.statusCode == 1000) {
                alert(data.message);
                if (data.data.userType == 2) {
                    localStorage.setItem("userId", data.data.userId);
                    window.location.href = "customer-view.html";
                }else if(data.data.userType == 1) {
                    localStorage.setItem("userId", data.data.userId);
                    window.location.href = "index.html";
                }
            } else {
                alert(data.message);
            }
        }
    });
    console.log('end');
});
//LOGIN FUNCTION -END