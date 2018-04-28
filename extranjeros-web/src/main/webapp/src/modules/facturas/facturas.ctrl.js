(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('facturaICtrl', ['$scope', '$http', 'facturaContext', 'viviendaContext','$state',
        function ($scope, $http, facturaContext,  viviendaContext, $state) {
            $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+facturaContext).then(function (response) {
                $scope.facturasRecords = response.data;
                //$state.go('facturasList' ,{facturaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);


