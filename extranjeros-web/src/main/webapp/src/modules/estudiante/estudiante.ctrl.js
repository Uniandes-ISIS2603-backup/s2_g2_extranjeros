/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("estudianteModule");
    mod.constant("estudianteContext", "api/estudiante");
    mod.controller('estudianteCtrl', ['$scope', '$http', 'estudianteContext',
        function ($scope, $http, estudianteContext) {
            $http.get(estudianteContext).then(function (response) {
                $scope.estudianteRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

