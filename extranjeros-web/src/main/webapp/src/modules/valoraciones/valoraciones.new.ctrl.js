(function (ng) {
    var mod = ng.module("valoracionModule");
   mod.constant("viviendaContext", "api/viviendas"); 
    mod.constant("valoracionesContext", "valoraciones"); 
   
    mod.controller('valoracionNewCtrl', ['$scope', '$http','$state', '$rootScope', 'viviendaContext','valoracionesContext',
        function($scope, $http, $state, $rootScope, viviendaContext,valoracionesContext) {

            $rootScope.edit = false;

            $scope.data = {};
            
            $scope.createValoracion = function () {
                $http.post(viviendaContext+'/'+$state.params.viviendaId+'/'+valoracionesContext, $scope.data).then(function (response) {
                    $state.go('valoracionesList', {valoracionId: response.data.id}, {reload: true});
                });
            };
   }
    ]);
}
)(window.angular);

