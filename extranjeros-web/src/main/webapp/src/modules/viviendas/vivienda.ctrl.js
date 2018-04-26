/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("viviendasModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaCtrl', ['$scope', '$http', 'viviendaContext',
        function ($scope, $http, viviendaContext) {
            $http.get(viviendaContext).then(function (response) {
                $scope.viviendasRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


