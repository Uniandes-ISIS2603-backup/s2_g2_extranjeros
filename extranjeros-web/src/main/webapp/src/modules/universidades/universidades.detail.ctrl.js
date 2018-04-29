(function (ng) {
    var mod = ng.module("universidadModule");
    mod.constant("universidadContext", "api/universidades");
    mod.controller('universidadDetailCtrl', ['$scope', '$http', 'universidadContext', '$state', '$filter',
        function ($scope, $http, universidadContext, $state, $filter) {
            
            if (($state.params.universidadId !== undefined) && ($state.params.universidadId !== null)) {
                $http.get(universidadContext).then(function (response) {
                    $scope.universidadesRecords = response.data;
                    $scope.currentUniversidad = $filter('filter')($scope.universidadesRecords, {id: $state.params.universidadId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

