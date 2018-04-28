(function (ng) {
    var mod = ng.module("universidadModule");
    mod.constant("universidadContext", "api/universidades");
    mod.controller('universidadCtrl', ['$scope', '$http', 'universidadContext',
        function ($scope, $http, universidadContext) {
            $http.get(universidadContext).then(function (response) {
                $scope.universidadesRecords = response.data;
            });
        }
    ]);
}
)(window.angular);



