(function (ng) {
    var mod = ng.module("estudianteModule");
    mod.constant("estudianteContext", "api/estudiante");
    mod.controller('estudianteDetailCtrl', ['$scope', '$http', 'estudianteContext', '$state', '$filter',
        function ($scope, $http, estudianteContext, $state, $filter) {

            if (($state.params.servicioId !== undefined) && ($state.params.servicioId !== null)) {
                $http.get(estudianteContext).then(function (response) {
                    $scope.estudianteRecords = response.data;
                    $scope.currentEstudiante = $filter('filter')($scope.estudianteRecords, {id: $state.params.estudianteId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

