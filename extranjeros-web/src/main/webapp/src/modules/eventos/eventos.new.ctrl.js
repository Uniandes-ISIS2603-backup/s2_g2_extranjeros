/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/eventos");
    mod.controller('eventoNewCtrl', ['$scope', '$http', 'eventoContext', '$state', '$rootScope',
        function ($scope, $http, eventoContext, $state, $rootScope) {
            
        $scope.createEvento = function () {
                $http.post(eventoContext, {
                    nombreEvento: $scope.nombre,
                    tipoEvento: $scope.tipo,
                    fechaEvento: $scope.fecha,
                    capacidad: $scope.capacidad,
                    ubicacionLat: $scope.latitud,
                    ubicacionLon: $scope.longitud,
                    privado: $scope.privado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('eventosList', {eventoId: response.data.id}, {reload: true});
                    }).catch(function(data) {
                // Handle error here
                alert(data.data);
        });
                };
            }
        ]);
}
)(window.angular);


