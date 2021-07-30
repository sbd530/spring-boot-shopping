function updateTotalPrice(price,op) {

    let priceInt = parseInt(price);
    let $newTotalPrice = $("#cartTotalDiscountAppliedPrice").text();
    let indexOfWon = $newTotalPrice.indexOf("원");
    $newTotalPrice = $newTotalPrice.substring(0,indexOfWon-1);
    $newTotalPrice = $newTotalPrice.split(',').join("");
    let newTotalPrice = parseInt($newTotalPrice);

    if(op=='+'){
      newTotalPrice = newTotalPrice + priceInt;
    }
    if(op=='-'){
      newTotalPrice = newTotalPrice - priceInt;
    }
    let ntp = newTotalPrice.toLocaleString('en');
    $("#cartTotalDiscountAppliedPrice").text(ntp + ' 원');
  }

  function updateTotalAmount(op) {
    let totalDiv = $("#cartTotalProductQuantity");
    let oldTotalAmount = parseInt(totalDiv.text());
    if(op=='+') {
      let newTotalAmount = oldTotalAmount + 1;
      totalDiv.text(newTotalAmount + ' 개');
    }
    if(op=='-') {
      let newTotalAmount = oldTotalAmount - 1;
      totalDiv.text(newTotalAmount + ' 개');
    }
  }

  $(function () {

    $(".plusAmount").on("click", function() {
      let productId = $(this).parent().find(".productId").val();
      let price = $(this).parent().find(".price").val();

      let newOrderAmount = parseInt($(this).parent().find(".orderAmount").val())+1;
      if(newOrderAmount>$(".stock" + productId).val()) {
        alert("재고가 부족합니다!");
        return;
      }
      $(this).parent().find(".orderAmount").val(newOrderAmount);
      updateTotalAmount('+');
      updateTotalPrice(price,'+');
      let formData = new FormData();
      formData.append("productId", productId);
      formData.append("orderAmount", newOrderAmount);

      $.ajax({
        url: '/carts',
        data: formData,
        processData: false,
        contentType: false,
        type: 'PUT'
      });
    })

    $(".minusAmount").on("click", function() {
      let productId = $(this).parent().find(".productId").val();
      let price = $(this).parent().find(".price").val();

      let newOrderAmount = parseInt($(this).parent().find(".orderAmount").val()) - 1;
      if(newOrderAmount<1) {
        alert("1개 이상만 가능합니다.");
        return;
      }
      $(this).parent().find(".orderAmount").val(newOrderAmount);
      updateTotalAmount('-');
      updateTotalPrice(price,'-');
      let formData = new FormData();
      formData.append("productId", productId);
      formData.append("orderAmount", newOrderAmount);

      $.ajax({
        url: '/carts',
        data: formData,
        processData: false,
        contentType: false,
        type: 'PUT'
      });
    })

   // 장바구니 삭제요청
    $(".removeCartItemBtn").on("click", function() {
      let productId = $(this).parent().find(".productId").val();
      let price = $(this).parent().find(".price").val();
      let totalDiv = $("#cartTotalProductQuantity");
      let oldTotalAmount = parseInt(totalDiv.text());

      price = parseInt(price);
      let newTotalPrice = $("#cartTotalDiscountAppliedPrice").text();
      let indexOfWon = newTotalPrice.indexOf("원");
      newTotalPrice = newTotalPrice.substring(0,indexOfWon-1);
      newTotalPrice = newTotalPrice.split(',').join("");
      newTotalPrice = parseInt(newTotalPrice);

      let amount = parseInt($("#orderAmount" + productId).val());
      let newTotalAmount = oldTotalAmount - amount;
      newTotalPrice = newTotalPrice-(price*amount);
      let ntp = newTotalPrice.toLocaleString('en');
      $("#cartTotalDiscountAppliedPrice").text(ntp + ' 원');
      totalDiv.text(newTotalAmount + ' 개');

      $.ajax({
        url: "/carts?productId=" + productId,
        type: "DELETE"
      });
      $("#productRow" + productId).remove();
    });


    //주문으로 넘겻!
    $(".sendToOrder").on("click", function() {

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
          url: "/orders",
          type: "POST",
          data: JSON.stringify({"orderLineDtoList" : orderLineDtoList}),
          contentType: 'application/json'
        }).then(function(){
          location.href="/orders"
        })
    })
  });