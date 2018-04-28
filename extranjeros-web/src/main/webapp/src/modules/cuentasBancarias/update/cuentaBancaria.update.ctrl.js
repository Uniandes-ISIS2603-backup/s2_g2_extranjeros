(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    
    mod.controller('cuentaBancariaUpdateCtrl', ['$scope', '$http', 'cuentaBancariaContext', 'arrendatarioContext', '$state', '$rootScope',
        
        function ($scope, $http, cuentaBancariaContext, arrendatarioContext, $state, $rootScope) {
            $rootScope.edit = true;
            $scope.data = {};
            $scope.selectedItems = [];
            $scope.availableItems = [];
            var idArrendatario = $state.params.arrendatarioId;
            var idCuentaBancaria = $state.params.cuentaBancariaId;

            $scope.createArrendatario = function () {
                $http.put(arrendatarioContext + "/" + idArrendatario + '/' + cuentaBancariaContext + '/' + idCuentaBancaria, $scope.data).then(function (response) {
                    $state.go('cuentasBancariasList', {cuentaBancariaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

