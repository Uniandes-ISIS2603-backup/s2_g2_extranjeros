(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('cuentaBancariaPostCtrl', ['$scope', '$http', 'cuentaBancariaContext', 'arrendatarioContext', '$state', '$rootScope',
        function ($scope, $http, cuentaBancariaContext, arrendatarioContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.createCuentaBanco = function () {
                $http.post(arrendatarioContext + '/' + $state.params.arrendatarioId + '/' + cuentaBancariaContext, $scope.data).then(function (response) {
                    $state.go('cuentasBancariasList', {cuentaBancariaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);


