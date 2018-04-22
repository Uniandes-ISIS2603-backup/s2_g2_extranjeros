(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('cuentaBancariaCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', 'cuentaBancariaContext',
        function ($scope, $http, arrendatarioContext, $state, cuentaBancariaContext) {
            $http.get(arrendatarioContext + '/' + $state.params.arrendatarioId  + '/' + cuentaBancariaContext).then(function (response) {
                $scope.cuentaBancariaRecords = response.data;
            });
        }
    ]);
})(window.angular);


