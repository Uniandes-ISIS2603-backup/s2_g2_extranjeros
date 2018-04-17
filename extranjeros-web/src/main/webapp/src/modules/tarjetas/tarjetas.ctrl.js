/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaCtrl', ['$scope', '$http', 'tarjetaContext',
        function ($scope, $http, tarjetaContext) {
            $http.get(tarjetaContext).then(function (response) {
                $scope.tarjetasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


