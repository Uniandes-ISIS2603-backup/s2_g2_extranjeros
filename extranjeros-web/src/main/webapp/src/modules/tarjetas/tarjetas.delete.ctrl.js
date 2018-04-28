(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaDeleteCtrl', ['$scope', '$http', 'tarjetaContext', '$state', '$filter',
        function ($scope, $http, tarjetaContext, $state, $filter) {

            var idTarjeta = $state.params.tarjetaId;
            $scope.deleteTarjeta = function () {
                $http.delete(tarjetaContext + '/' + idTarjeta, {}).then(function (response) {
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
};
        }
    ]);
}
)(window.angular);


