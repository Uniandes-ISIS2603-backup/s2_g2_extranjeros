/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("providenciaModule");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('providenciaCtrl', ['$scope', '$http', 'providenciaContext',
        function ($scope, $http, providenciaContext) {
            $http.get(providenciaContext).then(function (response) {
                $scope.providenciaRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

