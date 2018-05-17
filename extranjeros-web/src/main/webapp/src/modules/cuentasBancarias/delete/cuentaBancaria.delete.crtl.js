(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('cuentaBancariaDeleteCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', 'cuentaBancariaContext',

        function ($scope, $http, arrendatarioContext, $state, cuentaBancariaContext) {
            var idCuentaBanco = $state.params.cuentaBancariaId;

            $scope.deleteCuentaBancaria = function () {
                $http.delete(arrendatarioContext + '/' + $state.params.arrendatarioId + '/' + cuentaBancariaContext + '/' + idCuentaBanco).then(function (response) {
                    $state.go('cuentasBancariasList', {cuentaBancariaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);


