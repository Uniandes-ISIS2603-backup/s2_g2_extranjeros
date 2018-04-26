(function (ng) {
    var mod = ng.module("viviendasModule");
   mod.constant("viviendaContext", "api/viviendas"); 
   mod.controller('viviendaNewCtrl', ['$scope', '$http', 'viviendaContext', '$state', '$filter',
        function ($scope, $http, viviendaContext, $state, $filter) {

            $rootScope.edit = false;

            $scope.data = {};
            
            $scope.createVivienda = function () {
                $http.post(viviendaContext, $scope.data).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
   }
    ]);
}
)(window.angular);

