(function (ng) {
    var mod = ng.module("loginModule");
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.constant("estudianteContext","api/estudiante");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope', 'arrendatarioContext', 'estudianteContext',

        function ($scope, $http, $state, $rootScope, arrendatarioContext, estudianteContext) {


            $scope.user = {};

            $http.get(arrendatarioContext).then(function (response) {
                $scope.arrendatarioRecords = response.data;
            });
            $http.get(estudianteContext).then(function (response) {
                $scope.estudianteRecords = response.data;
            });

            $scope.autenticar = function () {
                $scope.data = {UserName: $scope.userName, Contrasenia: $scope.claveX};
                var flag = false;
                $http.post('api/login', $scope.data).then(function (response) {

                    for (var item in $scope.arrendatarioRecords) {

                        if (($scope.arrendatarioRecords[item].correo === $scope.data.UserName || $scope.arrendatarioRecords[item].usuario === $scope.data.UserName) && $scope.arrendatarioRecords[item].clave === $scope.data.Contrasenia) {
                            flag = true;
                            $scope.user = $scope.arrendatarioRecords[item];
                            $state.go('viviendasList', {}, {reload: true});
                            break;
                        }
                    }
                    for (var item in $scope.estudianteRecords) {

                        if (($scope.estudianteRecords[item].correo === $scope.data.UserName || $scope.estudianteRecords[item].usuario === $scope.data.UserName) && $scope.estudianteRecords[item].clave === $scope.data.Contrasenia) {
                            flag = true;
                            $scope.user = $scope.estudianteRecords[item];
                            $state.go('viviendasList', {}, {reload: true});
                            break;
                        }
                    }

                    if (!flag) {
                        $rootScope.alerts.push({type: "danger", msg: "El usuario o clave ingresada es incorrecta"});
                    } else {
                        sessionStorage.token = $scope.user.token;
                        sessionStorage.setItem("User", $scope.user.usuario);
                        sessionStorage.setItem("Correo", $scope.user.correo);
                        sessionStorage.setItem("rol", $scope.user.rol);
                        $rootScope.currentUsuarioLog = $scope.user;
                    }
                });
            };
        }
    ]);
}
)(window.angular);


