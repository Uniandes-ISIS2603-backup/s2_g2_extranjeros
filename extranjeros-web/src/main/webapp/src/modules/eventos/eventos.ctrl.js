/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/eventos");
    mod.controller('eventoCtrl', ['$scope', '$http', 'eventoContext',
        function ($scope, $http, eventoContext) {
            $http.get(eventoContext).then(function (response) {
                $scope.eventosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


