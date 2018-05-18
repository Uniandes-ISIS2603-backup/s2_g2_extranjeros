/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContextBuscar", "api/eventos/buscar/");
    mod.controller('eventoFilterCtrl', ['$scope', '$http', 'eventoContextBuscar', '$state', '$rootScope',
        function ($scope, $http, eventoContextBuscar, $state, $rootScope) {
            
        $scope.filtrarEvento = function () {
            var fechas = $scope.fechaFiltro;
                var nuevaFecha = fechas.replace("/", "-");
                var nuevaFecha2 = nuevaFecha.replace("/", "-");;
                $http.get(eventoContextBuscar + nuevaFecha2
                ).then(function (response) {
                    //Author created successfully
                    
                    $rootScope.eventosRecords = response.data;
                    $rootScope.filtrado = true;
                    $state.go('eventoFilter', {reload: true});
                    }).catch(function(data) {
                // Handle error here
                alert(data.data);
        });
                };
            }
        ]);
}
)(window.angular);


