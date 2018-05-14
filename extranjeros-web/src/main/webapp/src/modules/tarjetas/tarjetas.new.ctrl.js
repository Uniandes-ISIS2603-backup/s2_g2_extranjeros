/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaNewCtrl', ['$scope', '$http', 'tarjetaContext', '$state', '$rootScope',
        function ($scope, $http, tarjetaContext, $state, $rootScope) {
            
        $scope.createTarjeta = function () {
                $http.post(tarjetaContext, {
                    numero: $scope.numero,
                    banco: $scope.banco,
                    fechaCaducidad: $scope.fechaCaducidad
                }).then(function (response) {
                    //Author created successfully
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                    }).catch(function(data) {
                // Handle error here
                alert(data.data);
        });
                };
            }
        ]);
}
)(window.angular);


