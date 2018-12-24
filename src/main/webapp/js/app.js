$(document).ready(function(){
  $.getJSON("requests", function(data) {
    var items = [];
    var requestTable = $("#request-table");
    $.each(data, function(key, request) {
      requestTable.append("<tr><td>" + request.ip + "</td><td>" + request.dateTime + "</td><td>" + request.url + "</td></tr>");
    });
  }).fail(function() {
    alert("Failed to get data from server");
  });
});