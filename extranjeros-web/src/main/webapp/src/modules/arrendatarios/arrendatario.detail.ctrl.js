(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioDetailCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', '$filter',
        function ($scope, $http, arrendatarioContext, $state, $filter) {           
            if (($state.params.arrendatarioId !== undefined)&& ($state.params.arrendatarioId !== null)) {
                $http.get(arrendatarioContext).then(function (response) {
                    $scope.arrendatarioRecords = response.data;
                    $scope.currentArrendatario = $filter('filter')($scope.arrendatarioRecords, {id: $state.params.arrendatarioId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);