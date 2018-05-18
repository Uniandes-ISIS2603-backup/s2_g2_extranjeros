/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/eventos");
    mod.controller('eventoCtrl', ['$scope', '$http', 'eventoContext','$rootScope',
        function ($scope, $http, eventoContext, $rootScope) {
            if($rootScope.filtrado === "undefined")
            {
                $rootScope.filtrado = false;
            }
            if($rootScope.filtrado !== true )
            {
               $http.get(eventoContext).then(function (response) {
                $rootScope.eventosRecords = response.data;
            }); 
            }
            $rootScope.filtrado = false;
            
        }
    ]);
}
)(window.angular);


