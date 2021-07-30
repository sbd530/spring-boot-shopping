function fnCalCount(type, ths){
        var $input = $(ths).parents("td").find("input[name='pop_out']");
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

    function sendToPayment() {
            const f = document.toCartOrBuyForm;
            if(f.pop_out.value<1){
                alert("수량을 입력해주세요.");
                return;
            }
            f.action = "/orders/buy";
            f.submit();
        }

    function sendToCart() {
		const f = document.toCartOrBuyForm;
		if(f.pop_out.value<1){
			alert("수량을 입력해주세요.");
			return;
		}
		f.action = "/carts";
		f.submit();
	}

	$(function(){
    		$("#dialog").hide();

    		$("#btn1").click(function(){
    			$("#dialog").dialog();
    		});

    		$("#btn2").click(function(){
    			$("#dialog").dialog({
    				height:240,
    				width:300,
    				modal: true
    			});
    		});

    		$("#btn3").click(function(){
    			$("<div>").dialog({
    				modal:true,
    				open:function(){
    					$(this).load("addanswer_modal.html");
    				},
    				height:400,
    				width:400,
    				title:"외부파일 창 띄우기"
    			});
    		});


    	});