(function (ng) {
    var mod = ng.module("arrendatarioModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('arrendatarioPostCtrl', ['$scope', '$http', 'arrendatarioContext', '$state', '$rootScope',
        function ($scope, $http, arrendatarioContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.createArrendatario = function () {
                $http.post(arrendatarioContext, $scope.data).then(function (response) {
                    if($scope.data.imagen === undefined || $scope.data.imagen === null){
                        console.log($scope.data.imagen)
                        $scope.data.imagen = 'https://ceslava.com/blog/wp-content/uploads/2016/04/mistery-man-gravatar-wordpress-avatar-persona-misteriosa-510x510.png';
                    }
                    $state.go('arrendatariosList', {arrendatarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(window.angular);


