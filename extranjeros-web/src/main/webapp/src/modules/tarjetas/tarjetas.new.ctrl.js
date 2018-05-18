/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.constant("estudianteContext", "api/estudiante");
    mod.controller('tarjetaNewCtrl', ['$scope', '$http', 'tarjetaContext', '$state', '$rootScope','estudianteContext',
        function ($scope, $http, tarjetaContext, $state, $rootScope, estudianteContext) {
            
            $scope.createTarjeta = function () {
                $http.post(tarjetaContext, {
                    numero: $scope.numero,
                    banco: $scope.banco,
                    fechaCaducidad: $scope.fechaCaducidad
                }).then(function (response) {
                    //Author created successfully
                    
                    $state.go('estudianteList', {tarjetaId: response.data.id}, {reload: true});
                   
                }).catch(function (data) {
                    // Handle error here
                    alert(data.data);
                });
            };
           
            $scope.createEstudiante = function () {
                $http.put(estudianteContext + "/" + $scope.actual.id, $scope.data).then(function (response) {
                    
                    $state.go('estudianteList', {estudianteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


