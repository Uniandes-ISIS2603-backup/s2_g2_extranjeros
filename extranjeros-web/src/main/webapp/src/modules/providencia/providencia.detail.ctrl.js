(function (ng) {
    var mod = ng.module("providenciaModule");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('providenciaDetailCtrl', ['$scope', '$http', 'providenciaContext', '$state', '$filter',
        function ($scope, $http, providenciaContext, $state, $filter) {

            if (($state.params.providenciaId !== undefined) && ($state.params.providenciaId !== null)) {
                $http.get(providenciaContext).then(function (response) {
                    $scope.providenciaRecords = response.data;
                    $scope.currentProvidencia = $filter('filter')($scope.providenciaRecords, {id: $state.params.providenciaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

