$(function () {
    $("#sendToOrder").on("click", function() {
      let productIdArray = document.getElementsByName("productId");
      let orderAmountArray = document.getElementsByClassName("orderAmount");
      let orderLineDtoList = new Array();
      for(let i=0;i<productIdArray.length;i++){

        let orderAmount = orderAmountArray[i].value;
        let productId = productIdArray[i].value;

        let json = {"productId" : productId, "orderAmount" : orderAmount};
        orderLineDtoList.push(json);
      }

        $.ajax({
          url: "/orders/execute",
          type: "POST",
          data: JSON.stringify({"orderLineDtoList" : orderLineDtoList}),
          contentType: 'application/json'
        }).then(function(){
            location.href="/orders/success";
        })
    });
  });