(function (ng) {
    var mod = ng.module("lugarDeInteresModule");
    mod.constant("lugarDeInteresContext", "api/lugares");
    mod.controller('lugarDeInteresCtrl', ['$scope', '$http', 'lugarDeInteresContext',
        function ($scope, $http, lugarDeInteresContext) {
            $http.get(lugarDeInteresContext).then(function (response) {
                $scope.lugaresDeInteresRecords = response.data;
            });
        }
    ]);
}
)

(window.angular);



