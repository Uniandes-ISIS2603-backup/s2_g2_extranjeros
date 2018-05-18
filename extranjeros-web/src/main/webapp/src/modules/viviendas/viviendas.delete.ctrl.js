(function (ng) {
    var mod = ng.module("viviendasModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaDeleteCtrl', ['$scope', '$http', '$state','viviendaContext',
    
        function ($scope, $http, $state, viviendaContext) {
           
            $scope.deleteVivienda = function () {
                $http.delete(viviendaContext+'/'+$state.params.viviendaId, {}).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
