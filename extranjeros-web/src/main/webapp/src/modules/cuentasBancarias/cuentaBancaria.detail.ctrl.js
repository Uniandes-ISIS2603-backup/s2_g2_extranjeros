(function (ng) {
    var mod = ng.module("cuentaBancariaModule");
    
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('cuentaBancariaDetailCtrl', ['$scope', '$http', 'cuentaBancariaContext', 'arrendatarioContext', '$state', '$filter',
        function ($scope, $http, cuentaBancariaContext,arrendatarioContext, $state, $filter) {
            if (($state.params.cuentaBancariaId !== undefined) && ($state.params.cuentaBancariaId !== null)) {
                $http.get(arrendatarioContext+'/'+$state.params.arrendatarioId+'/'+cuentaBancariaContext).then(function (response) {
                    $scope.cuentaBancariaRecords = response.data;
                    $scope.currentCuentaBanco = $filter('filter')($scope.cuentaBancariaRecords, {id: $state.params.cuentaBancariaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);


