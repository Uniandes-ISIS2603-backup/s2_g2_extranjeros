(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioPostCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', '$rootScope',
        function ($scope, $http, arrendatarioContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.createArrendatario = function () {
                $http.post(arrendatarioContext, $scope.data).then(function (response) {
                    $state.go('arrendatariosList', {arrendatarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);


