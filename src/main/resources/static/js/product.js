function fnCalCount(type, ths){
        var $input = $(ths).parents("td").find("input[name='orderAmount']");
        var tCount = Number($input.val());

        if(type=='p'){
            $input.val(Number(tCount)+1);

        }else{
            if(tCount >0) $input.val(Number(tCount)-1);
        }
    }

    function changeImage(url) {
          const mainImageDiv = document.getElementById("mainImage");
          mainImageDiv.style.backgroundImage = "url(" + url + ")";
    }

    function sendToCart() {
		f = document.toCartOrBuyForm;
		if(f.orderAmount.value<1){
			alert("수량을 입력해주세요.");
			return;
		}
		$.ajax({
          url: "/carts",
          method: "POST",
          data: {
              productId: $("#productId").val(),
              orderAmount: $("#orderAmount").val()
          },
          success: function(data){
              alert("장바구니에 담았습니다");
          },
          error: function(){
              alert("에러 발생");
          }
        });
	}

    $(function () {
      $("#sendToOrder").on("click", function() {

      let productId = document.getElementById("productId").value;
      let orderAmount = document.getElementById("orderAmount").value;
      let orderLineDtoList = new Array();
        let json = {"productId" : productId, "orderAmount" : orderAmount};
        orderLineDtoList.push(json);

        $.ajax({
          url: "/orders/buy",
          type: "POST",
          data: JSON.stringify({"orderLineDtoList" : orderLineDtoList}),
          contentType: 'application/json'
        }).then(function(){
          location.href="/orders/buy"
        })

    })
  });