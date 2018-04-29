(function (ng) {
    var mod = ng.module("lugarDeInteresModule");
    mod.constant("lugarDeInteresContext", "api/lugares");
    mod.controller('lugarDeInteresDetailCtrl', ['$scope', '$http', 'lugarDeInteresContext', '$state', '$filter',
        function ($scope, $http, lugarDeInteresContext, $state, $filter) {
            
            if (($state.params.lugarDeInteresId !== undefined) && ($state.params.lugarDeInteresId !== null)) {
                $http.get(lugarDeInteresContext).then(function (response) {
                    $scope.lugaresDeInteresRecords = response.data;
                    $scope.currentLugarDeInteres = $filter('filter')($scope.lugaresDeInteresRecords, {id: $state.params.lugarDeInteresId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);

