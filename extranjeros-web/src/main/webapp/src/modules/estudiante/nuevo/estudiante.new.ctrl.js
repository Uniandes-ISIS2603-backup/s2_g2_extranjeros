/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("estudianteModule");
    mod.constant("estudianteContext", "api/estudiante");
    mod.constant("universidadesContext", "api/universidades");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('estudiantePostCtrl', ['$scope', '$http', 'estudianteContext', '$state', '$rootScope', 'universidadesContext', 'providenciaContext',
        function ($scope, $http, estudianteContext, $state, $rootScope, universidadesContext, providenciaContext) {
            $rootScope.edit = false;
            $scope.universidadesRecords = [];
            $scope.providenciaRecords = [];
            $scope.data = {};
            $scope.pro={};
            $scope.uni={};

            $http.get(universidadesContext).then(function (response) {
                $scope.universidadesRecords = response.data;
            });

            $http.get(providenciaContext).then(function (response) {
                $scope.providenciaRecords = response.data;
            });
            
            $scope.createEstudiante = function () {
                
                $http.post(estudianteContext, $scope.data).then(function (response) {
                    $state.go('estudianteList', {estudianteId: response.data.id}, {reload: true});
                });
            };
            
           
        } 
    ]);
})(window.angular);

