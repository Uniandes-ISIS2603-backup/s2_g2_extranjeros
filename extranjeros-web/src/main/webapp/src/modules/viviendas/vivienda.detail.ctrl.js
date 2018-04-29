(function (ng) {
    var mod = ng.module("viviendasModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaDetailCtrl', ['$scope', '$http', 'viviendaContext', '$state', '$filter',
        function ($scope, $http, viviendaContext, $state, $filter) {

            if (($state.params.viviendaId !== undefined) && ($state.params.viviendaId !== null)) {
                $http.get(viviendaContext).then(function (response) {
                    $scope.viviendasRecords = response.data;
                    $scope.currentVivienda = $filter('filter')($scope.viviendasRecords, {id: $state.params.viviendaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

