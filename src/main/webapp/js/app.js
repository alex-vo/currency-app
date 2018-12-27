$(document).ready(function(){

  var currentPage = 0;
  var requestNumber = 1;
  var requestTable = $("#request-table");
  var dateTime = new Date().toISOString();

  var retrievePage = function(){
    $.getJSON("requests/" + currentPage + "?dateTime=" + dateTime, function(data) {

      $.each(data, function(key, request){
        requestTable.append("<tr><td>" + requestNumber++ + "</td><td>" + request.ip + "</td><td>"
          + request.currencyCode + "</td><td>" + request.dateTime + "</td><td>" + request.url + "</td></tr>");
      });

      if(data.length == 10){
        currentPage++;
      }else{
        $("#load-more").off('click');
      }
    }).fail(function() {
      console.log("Failed to get data from server");
    });
  }

  $("#load-more").click(function(){
    retrievePage();
  });

  retrievePage();

});