(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioDeleteCtrl', ['$scope', '$http', 'arrendatarioContext', '$state',

        function ($scope, $http, arrendatarioContext, $state) {
            var idArrendatario = $state.params.arrendatarioId;

            $scope.deleteArrendatario = function () {
                $http.delete(arrendatarioContext + '/' + idArrendatario).then(function (response) {
                    $state.go('arrendatariosList', {arrendatarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);

