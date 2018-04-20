(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "api/viviendas/11/facturas");
    mod.controller('facturaCtrl', ['$scope', '$http', 'facturaContext',
        function ($scope, $http, facturaContext) {
            $http.get(facturaContext).then(function (response) {
                $scope.facturasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

