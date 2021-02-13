$("#name").keyup((e) => {
    if ($("#name").val() == "") {
        if (!$("#name").hasClass("is-invalid")) {
            $("#name").addClass("is-invalid");
        }
    } else {
        if ($("#name").hasClass("is-invalid")) {
            $("#name").removeClass("is-invalid");
        }
        if (!$("#name").hasClass("is-valid")) {
            $("#name").addClass("is-valid");
        }
    }
});
$("#username").keyup((e) => {
    if ($("#username").val() == "") {
        if (!$("#username").hasClass("is-invalid")) {
            $("#username").addClass("is-invalid");
        }
    } else {
        if ($("#username").hasClass("is-invalid")) {
            $("#username").removeClass("is-invalid");
        }
        if (!$("#username").hasClass("is-valid")) {
            $("#username").addClass("is-valid");
        }
    }
});
$("#password").keyup((e) => {
    if ($("#password").val() == "") {
        if (!$("#password").hasClass("is-invalid")) {
            $("#password").addClass("is-invalid");
        }
    } else {
        if ($("#password").hasClass("is-invalid")) {
            $("#password").removeClass("is-invalid");
        }
        if (!$("#password").hasClass("is-valid")) {
            $("#password").addClass("is-valid");
        }
    }
});
$("#rePassword").keyup((e) => {
    if ($("#password").val() != $("#rePassword").val()) {
        if (!$("#rePassword").hasClass("is-invalid")) {
            $("#rePassword").addClass("is-invalid");
        }
        $("#passwordValidation").show();
    } else {
        if ($("#rePassword").hasClass("is-invalid")) {
            $("#rePassword").removeClass("is-invalid");
        }
        if (!$("#rePassword").hasClass("is-valid")) {
            $("#rePassword").addClass("is-valid");
        }
        $("#passwordValidation").hide();
    }
});
$("#image").change(() => {
    let file = $("#image").get(0).files[0];
    if (file) {
        let reader = new FileReader();
        reader.onload = function () {
            $("#previewImg").attr("src", reader.result);
        }
        reader.readAsDataURL(file);
    }
});