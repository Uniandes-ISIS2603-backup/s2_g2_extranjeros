(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('facturaCtrl', ['$scope', '$http', 'facturaContext', '$state','viviendaContext',
        function ($scope, $http, facturaContext, $state, viviendaContext) {
            $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+facturaContext).then(function (response) {
                console.log($state.params.viviendaId);
                $scope.facturasRecords = response.data;
                $state.go('facturasList' ,{facturaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);


