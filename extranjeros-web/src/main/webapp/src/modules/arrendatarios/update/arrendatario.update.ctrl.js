(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioPutCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', '$rootScope',
        
        function ($scope, $http, arrendatarioContext, $state, $rootScope) {
            
            $rootScope.edit = true;
            $scope.data = {};
            $scope.selectedItems = [];
            $scope.availableItems = [];
            var idArrendatario = $state.params.arrendatarioId;

            $scope.createArrendatario = function () {
                $http.put(arrendatarioContext + "/" + idArrendatario, $scope.data).then(function (response) {
                    $state.go('arrendatariosList', {arrendatarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


