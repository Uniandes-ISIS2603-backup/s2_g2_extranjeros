(function (ng) {
    var mod = ng.module("valoracionModule");
    mod.constant("valoracionContext", "valoraciones");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('valoracionDetailCtrl', ['$scope', '$http', 'valoracionContext', 'viviendaContext', '$state', '$filter',
        function ($scope, $http, valoracionContext,viviendaContext, $state, $filter) {

            if (($state.params.valoracionId !== undefined) && ($state.params.valoracionId !== null)) {
                $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+valoracionContext).then(function (response) {
                    $scope.valoracionesRecords = response.data;
                    $scope.currentValoracion = $filter('filter')($scope.valoracionesRecords, {id: $state.params.valoracionId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

