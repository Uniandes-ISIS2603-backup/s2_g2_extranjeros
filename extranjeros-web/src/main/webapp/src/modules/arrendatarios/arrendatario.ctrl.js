(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioCtrl', ['$scope', '$http', 'arrendatarioContext',
        function ($scope, $http, arrendatarioContext) {
            $http.get(arrendatarioContext).then(function (response) {
                $scope.arrendatarioRecords = response.data;
            });
        }
    ]);
}
)(window.angular);
