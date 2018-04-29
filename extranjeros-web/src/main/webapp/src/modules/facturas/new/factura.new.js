/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var day;
var month;
var year;
function fechaActual() {
  var date = new Date();
  day = date.getDate();
  month = date.getMonth() + 1;
  year = date.getFullYear();
  if (month < 10) month = "0" + month;
  if (day < 10) day = "0" + day;
  var today = year + "-" + month + "-" + day;
  //document.getElementById('fechaHoyIN').min = today;
  //document.getElementById("fechaHoy").innerHTML=today;
}
function fechaMinimaDeSalida() {
  var date = new Date();
  var newMonth;
  var newDay=date.getDate();
  
  if(date.getMonth() + 2==13)
  {
      newMonth=1;
      newYear=date.getFullYear()+1;
  }
  else
  {
        newMonth = date.getMonth()+2;
  }
  if(newDay==31&&(newMonth==4||newMonth==6||newMonth==9||newMonth==11))
  {
      newMonth++;
      newDay=1;
  }
  if(newDay>28&&newMonth==2)
  {
      newMonth++;
      newDay+=newDay-28;
  }
  if (newMonth < 10) newMonth = "0" + newMonth;
  if (newDay < 10) newDay = "0" + newDay;
  var notoday = year + "-" + newMonth + "-" + newDay;
  //document.getElementById('fechaMesDespuesIN').min = notoday;
  //document.getElementById("fechaMesDespues").innerHTML=notoday;
}
fechaActual();
fechaMinimaDeSalida();