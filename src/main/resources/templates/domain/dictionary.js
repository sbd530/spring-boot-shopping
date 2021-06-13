//dictionary.js 구현
$(document).ready(function() {

  $('#load').click(function() {
    $('#dictionary').load("load.html");
  });
  
   $('#ajaxs').click(function() {
      $('#dictionary').load("loads.html");
  });
  return true;
});

