    var src = (location.protocol == "https:" ? "https://ssl.daumcdn.net/dmaps" : "http://dmaps.daum.net")
        + "/map_js_init/postcode.v2.js?autoload=false";
    var scriptTagElem = document.createElement("script");
    scriptTagElem.setAttribute("type", "text/javascript");
    scriptTagElem.setAttribute("src", src);
    scriptTagElem.setAttribute("onerror", "require(['common/common'], function (common) { common.onErrorLoadPostcodeSdk(); });");
    document.write(scriptTagElem.outerHTML);

// 우편번호 찾기 iframe을 넣을 element
    var element = document.getElementById("daum_postcode_layer");

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element.style.display = 'none';
        $("#btnCloseLayer").addClass("hide");
    }

    function showDaumPostcode(no, deviceType, layerNo, callbackFromRedesignPage) {
        $("#btnCloseLayer").removeClass("hide");
        if ($(event.target).hasClass("notWorkingButton")) {
            return
        }
        if (deviceType != "mobile") {
            try {
                var top = window.getParentScrollTopMinusHeaderHeight ? getParentScrollTopMinusHeaderHeight() : 180;

                $(element).css("top", top + "px");
            } catch (e) {

            }

            if (layerNo == 1) {
                document.getElementById("daum_postcode_layer_wrapper1").appendChild(element);
            } else if (layerNo == 2) {
                document.getElementById("daum_postcode_layer_wrapper2").appendChild(element);
            }

            daum.postcode.load(function () {
                new daum.Postcode({
                    oncomplete: function (data) {
                        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                        var fullAddr = data.address; // 최종 주소 변수
                        var extraAddr = ''; // 조합형 주소 변수

                        // 기본 주소가 도로명 타입일때 조합한다.
                        if (data.addressType === 'R') {
                            //법정동명이 있을 경우 추가한다.
                            if (data.bname !== '') {
                                extraAddr += data.bname;
                            }
                            // 건물명이 있을 경우 추가한다.
                            if (data.buildingName !== '') {
                                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                            }
                            // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                            fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
                        }

                        if (no == 1) {
                            require("display/shopOrderRequest").checkAndApplyDeliveryRemoteArea2(data.zonecode, fullAddr, "SENDER", false);

                        } else if (no == 2) {
                            if (callbackFromRedesignPage) {
                                callbackFromRedesignPage(data.zonecode, fullAddr);
                            } else {
                                require("display/shopOrderRequest").checkAndApplyDeliveryRemoteArea2(data.zonecode, fullAddr, "RECIPIENT", false);
                            }
                        } else if (no == 3) {
                            if ($("#shopOrderDetail #deliveryPrice").attr("value") == -1 || $(
                                "#shopOrderDetailPage .payment-info #deliveryPrice").attr("value") == -1) {
                                document.getElementById('post1').value = data.zonecode;
                                document.getElementById('post2').value = fullAddr;

                                $("#post3").focus();
                            } else {
                                require("common/common").commerce.checkDeliveryRemoteArea(
                                    document.getElementById('post1').value, function (postArea) {
                                        var beforePostArea = postArea;
                                        require("common/common").commerce.checkDeliveryRemoteArea(data.zonecode,
                                            function (postArea) {
                                                var newPostArea = postArea;
                                                if (beforePostArea == newPostArea) {
                                                    document.getElementById('post1').value = data.zonecode;
                                                    document.getElementById('post2').value = fullAddr;
                                                    try {
                                                        document.getElementById('showPost2').innerHTML = fullAddr;
                                                    } catch (e) {
                                                        // 방어코드 다른 페이지에 영향을 확인할 수 없어 추가함.
                                                    }
                                                    $("#post3").focus();
                                                } else {
                                                    alert("원래 주문과 배송비가 다른 지역으로 주소를 변경할 수 없습니다. 환불 후 다시 주문하시기 바랍니다.");
                                                }
                                            });
                                    });
                            }
                        } else if (no == 4) {
                            document.getElementById('post1').value = data.zonecode;
                            document.getElementById('post2').value = fullAddr;
                            $("#post3").focus();

                        }
                        // iframe을 넣은 element를 안보이게 한다.
                        element.style.display = 'none';

                    },
                    width: '100%',
                    height: '100%'
                }).embed(element);
                // iframe을 넣은 element를 보이게 한다.

                element.style.display = 'block';
            })

        } else {

            /* new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색 결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                    // 법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                    if(fullRoadAddr !== ''){
                        fullRoadAddr += extraRoadAddr;
                    }

                    if (no == 1 || no == 3) {
                        document.postCode.post1.value = data.postcode1;
                        document.postCode.post2.value = data.postcode2;
                        document.postCode.post3.value = data.address;
                        $("#post4").focus();
                    } else if (no == 2) {
                        document._postCode._post1.value = data.postcode1;
                        document._postCode._post2.value = data.postcode2;
                        document._postCode._post3.value = data.address;
                        $("#_post4").focus();
                    }
                }
            }).open();

             */
        }

    }