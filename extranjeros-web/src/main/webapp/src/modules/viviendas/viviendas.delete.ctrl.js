(function (ng) {
    var mod = ng.module("viviendaModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaDeleteCtrl', ['$scope', '$http', '$state','viviendaContext',
    
        function ($scope, $http, $state, viviendaContext) {
            var idVivienda = $state.params.viviendaId;
           
            $scope.deleteVivienda = function () {
                $http.delete(viviendaContext+'/'+$state.params.viviendaId, {}).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
