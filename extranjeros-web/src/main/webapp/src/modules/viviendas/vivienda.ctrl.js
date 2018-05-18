/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("viviendasModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaCtrl', ['$scope', '$http', 'viviendaContext',
        function ($scope, $http, viviendaContext) {
            $scope.data2={};
            $scope.data3={};
            //Consulto los servicios de la vivienda a facturar.
            $http.get("api/servicios").then(function (response) {
                $scope.data2= response.data;
            });
            $http.get("api/universidades").then(function (response) {
                $scope.data3= response.data;
            });
            $http.get(viviendaContext
                    ).then(function (response) {
                $scope.viviendasRecords = response.data;
            });
           $scope.filtro1="";
           $scope.filtro2="";
           $scope.filtro3="";
           $scope.filtro4="";
           $scope.filtro5="";
           $scope.cambiarFiltro1=function(){
               $scope.filtro1=":1";
               $scope.filtroNuevo();
           };
           $scope.cambiarFiltro3=function(temp){
              console.log(temp);
              if($scope.filtro3==="")
                  $scope.filtro3=":"+temp;
              else
                  $scope.filtro3=filtro3+";"+temp;
              $scope.filtroNuevo();
           };
           $scope.cambiarFiltro2=function(){
               temp=parseInt($scope.text2.value);
               $scope.filtro2=":"+temp;
               $scope.filtroNuevo();
           };
           $scope.cambiarFiltro4=function(temp)
           {
               $scope.filtro4=":"+temp;
               $scope.filtroNuevo();
           };
           $scope.cambiarFiltro51=function()
           {
               $scope.filtro5=$scope.fecha1.value;
           };
           $scope.cambiarFiltro52=function()
           {
               $scope.filtro5=$scope.filtro5+";"+$scope.fecha2.value;
               $scope.filtroNuevo();
           };
           $scope.filtroNuevo = function () {
                $http.get(viviendaContext+'?filter=1'+$scope.filtro1+',2'+$scope.filtro2+',3'+$scope.filtro3+',4'+$scope.filtro4+',5').then(function (response) {
                    $scope.viviendasRecords = response.data;
                });
            } 
        }
    ]);
}
)(window.angular);