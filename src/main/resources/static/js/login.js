$(function(){
           $("#loginBtn").on("click", function() {
             var json = {email: $("#email").val(),password: $("#password").val()};
             $.ajax({
                url: "/auth/login/check",
                type:"post",
                data: JSON.stringify(json),
                contentType: "application/json",
                success: function(data) {
                    if(data=="SUCCESS"){
                        $("#loginForm").submit();
                    }else{
                        var resultDiv = document.getElementById("loginSignupErrorMsg");
                        resultDiv.innerHTML = "존재하지 않는 아이디입니다.";
                    }
                },
                error: function() {
                    var resultDiv = document.getElementById("loginSignupErrorMsg");
                    resultDiv.innerHTML = "존재하지 않는 아이디입니다.";
                }
             })
           })
        });