/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("estudianteModule");
    mod.constant("estudianteContext", "api/estudiante");
    mod.controller('estudiantePutCtrl', ['$scope', '$http', 'estudianteContext', '$state', '$rootScope',
        
        function ($scope, $http, estudianteContext, $state, $rootScope) {
            
            $rootScope.edit = true;
            $scope.data = {};
            $scope.selectedItems = [];
            $scope.availableItems = [];
            var idEstudiante = $state.params.estudianteId;

            $scope.createEstudiante = function () {
                $http.put(estudianteContext + "/" + idEstudiante, $scope.data).then(function (response) {
                    $state.go('estudianteList', {estudianteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


