(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.controller('cuentaBancariaDetailController', ['$scope', '$http', 'arrendatarioContext', '$state', 'cuentaBancariaContext',
        function ($scope, $http, arrendatarioContext, $state, cuentaBancariaContext) {
            if (($state.params.cuentaBancariaId !== undefined) && ($state.params.cuentaBancariaId !== null)) {
                $http.get(arrendatarioContext + '/' + $state.params.arrendatarioId + '/'+ cuentaBancariaContext).then(function (response) {
                    $scope.cuentaBancariaRecords = response.data;
                    $scope.currentCuentaBancaria = $filter('filter')($scope.cuentaBancariaRecords, {id: $state.params.cuentaBancariaId}, true)[0];
                });
            }
        }
    ]);
})(window.angular); 


