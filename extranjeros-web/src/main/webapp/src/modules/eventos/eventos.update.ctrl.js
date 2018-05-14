/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/tarjetas");
    mod.controller('eventoUpdateCtrl', ['$scope', '$http', 'eventoContext', '$state', '$rootScope',
        function ($scope, $http, eventoContext, $state, $rootScope) {
            $http.get(eventoContext + '/' + $state.params.eventoId).then(function (response) {
            var evento = response.data;
            $scope.nombre = evento.nombreEvento;
            $scope.tipo = evento.tipoEvento;
            $scope.fecha = evento.fechaEvento;
            $scope.id = evento.id;
            $scope.longitud = evento.ubicacionLon;
            $scope.latitud = evento.ubicacionLat;
            $scope.privado = evento.privado;
            $scope.capacidad = evento.capacidad;
        $scope.updateEvento = function () {
                $http.put(eventoContext+ '/' + $state.params.eventoId, {
                    nombreEvento: $scope.nombre,
                    fechaEvento: $scope.fecha,
                    tipoEvento: $scope.tipo,
                    ubicacionLon : $scope.longitud,
                    ubicacionLat: $scope.latitud,
                    capacidad : $scope.capacidad,
                    privado : $scope.privado
                }).then(function (response) {
                    //Author created successfully
                    $state.go('eventosList', {eventoId: response.data.id}, {reload: true});
                    }).catch(function(data) {
                // Handle error here
                alert(data.data);
        });
                };
            });
            }
        ]);
}
)(window.angular);


