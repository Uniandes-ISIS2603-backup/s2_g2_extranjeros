(function (ng) {
    var mod = ng.module("loginModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope', 'arrendatarioContext',
        
        function ($scope, $http, $state, $rootScope, arrendatarioContext) {
            $scope.user = {};
            $scope.data = {};
            
            $http.get(arrendatarioContext).then(function (response) {
                $scope.arrendatarioRecords = response.data;
            });
            
            $scope.autenticar = function () {
                var flag = false;
                $http.post('api/login',$scope.data).then(function(response){

                for (var item in $scope.arrendatarioRecords) {
                    if ( ($scope.arrendatarioRecords[item].correo === response.data.correo || $scope.arrendatarioRecords[item].usuario === response.data.usuario)&& $scope.users[item].clave === response.data.clave) {
                        flag = true;
                        $scope.user = $scope.arrendatarioRecords[item];
                        $state.go('arrendatariosList', {}, {reload: true});
                        break;
                    }
                }
                if (!flag) {
                    $rootScope.alerts.push({type: "danger", msg: "Incorrect username or password."});
                } else {
                    sessionStorage.token = $scope.user.token;
                    sessionStorage.setItem("User", $scope.user.usuario);
                    sessionStorage.setItem("Correo", $scope.user.correo);
//                    sessionStorage.setItem("rol", $scope.user.rol);
                    $rootScope.currentUser = $scope.user.nombre; 
                }
                });
            };
        }
    ]);
}
)(window.angular);


