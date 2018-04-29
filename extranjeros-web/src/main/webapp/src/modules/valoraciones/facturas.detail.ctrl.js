(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('facturaDetailCtrl', ['$scope', '$http', 'facturaContext', 'viviendaContext', '$state', '$filter',
        function ($scope, $http, facturaContext,viviendaContext, $state, $filter) {

            if (($state.params.facturaId !== undefined) && ($state.params.facturaId !== null)) {
                $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+facturaContext).then(function (response) {
                    $scope.facturasRecords = response.data;
                    $scope.currentFactura = $filter('filter')($scope.facturasRecords, {id: $state.params.facturaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

