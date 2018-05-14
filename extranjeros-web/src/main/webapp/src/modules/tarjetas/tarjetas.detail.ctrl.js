(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaDetailCtrl', ['$scope', '$http', 'tarjetaContext', '$state', '$filter',
        function ($scope, $http, tarjetaContext, $state, $filter) {

            if (($state.params.tarjetaId !== undefined) && ($state.params.tarjetaId !== null)) {
                $http.get(tarjetaContext).then(function (response) {
                    $scope.tarjetasRecords = response.data;
                    $scope.currentTarjeta = $filter('filter')($scope.tarjetasRecords, {id: $state.params.tarjetaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);


