$(document).ready(function(){
  $("div.register").hide();
})

$('.message a').click(function (){
  $("div.login").toggle();
  $("div.register").fadeToggle("fast");
})

function loginRegisterSwitch() {
  $("div.register").toggle();
  $("div.login").fadeToggle("fast");
}

$("button.register")
    .click(
        function (event) {
            event.preventDefault();
            var firstName = $("form.registerForm input.userName").val();
            var lastName = $("form.registerForm input.userSurname").val();
            var email = $("form.registerForm input.registerEmail").val();
            var atposition=email.indexOf("@");
            var dotposition=email.lastIndexOf(".");
            var password = $("form.registerForm input.registerPassword").val();
            if (firstName == '' || lastName == '' || email == '' || password == '') {
                alert("Please fill all fields...!!!!!!");
            } else if (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length) {
                   alert("Please enter valid email");
            } else if ((password.length) < 4) {
                alert("Password should atleast 4 character in length...!!!!!!");
            } else {
                var userRegistration = {
                    firstName,
                    lastName,
                    email,
                    password
                };

                $.post("register", userRegistration,)
                    .done(function (data, textStatus, xhr) {
                        if (xhr.status === 201) {
                         $("form")[0].reset();
                         $("form")[1].reset();
                        loginRegisterSwitch();
                        } else {
                            alert("error while creating a user");
                        }
                    })
                    .fail(function () {
                        alert("error while creating a user");
                    });
                ;
            }
        });


$("button.login").click(function (event) {
    // need to prevent default behaviour of the button which caused page reload
    event.preventDefault();

    var email = $("form.loginForm input.userEmail").val();
    var password = $("form.loginForm input.loginPassword").val();

    if (email == '' || password == '') {
        alert("Please fill login form!");
    } else {
        var userLogin = {
            email,
            password
        };
        $.post("login", userLogin)
            .done(function (data, textStatus, xhr) {
                if (xhr.status === 200) {
                     window.location = window.origin + "/InternetShop_war/cabinet";
                } else {
                    alert("error while authorizing the user");
                }
            })
            .fail(function () {
                alert("error while authorizing the user");
            });
    }
});