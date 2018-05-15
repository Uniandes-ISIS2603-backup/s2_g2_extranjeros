(function (ng) {
    var mod = ng.module("providenciaModule");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('providenciaPostCtrl', ['$scope', '$http', 'providenciaContext', '$state', '$rootScope',
        function ($scope, $http, providenciaContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.createProvidencia = function () {
                $http.post(providenciaContext, $scope.data).then(function (response) {
                    $state.go('providenciaList', {providenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);


