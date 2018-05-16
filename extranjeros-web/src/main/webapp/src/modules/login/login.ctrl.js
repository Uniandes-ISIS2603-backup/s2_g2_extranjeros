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
                $http.post('api/login', $scope.data).then(function(response){
                    console.log(response.data.Contrasenia);
                    
                for (var item in $scope.arrendatarioRecords) {
                    if (($scope.arrendatarioRecords[item].correo === response.data.UserName || $scope.arrendatarioRecords[item].usuario === response.data.UserName)&& $scope.arrendatarioRecords[item].clave === response.data.Contrasenia){
                        flag = true;
                        $scope.user = $scope.arrendatarioRecords[item];
                        $state.go('arrendatariosList', {}, {reload: true});
                        break;
                    }
                }
                console.log(flag);
                
                if (!flag) {
                    $rootScope.alerts.push({type: "danger", msg: "El usuario o clave ingresada es incorrecta"});
                } 
                
                else {
                    console.log("Niceeee");
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


