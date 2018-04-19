(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "api/viviendas/11/facturas");
    mod.controller('facturaDetailCtrl', ['$scope', '$http', 'facturaContext', '$state', '$filter',
        function ($scope, $http, facturaContext, $state, $filter) {

            if (($state.params.facturaId !== undefined) && ($state.params.facturaId !== null)) {
                $http.get(facturaContext).then(function (response) {
                    $scope.facturasRecords = response.data;
                    $scope.currentFactura = $filter('filter')($scope.facturasRecords, {id: $state.params.facturaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

