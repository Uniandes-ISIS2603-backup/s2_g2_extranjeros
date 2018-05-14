
(function (ng) {
    var mod = ng.module("valoracionModule");
    mod.constant("valoracionContext", "valoracion");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('valoracionesUpdateCtrl', ['$scope', '$http', 'valoracionContext', '$state','$rootScope','viviendaContext',
       
        function ($scope, $http, valoracionContext, $state, $rootScope, viviendaContext) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idValoracion = $state.params.valoracionId;

            //Consulto la factura a editar.
            $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+valoracionContext+'/'+idValoracion).then(function (response) {
                var valoracion = response.data;
                $scope.data.valoracion = valoracion.valoracion;
                $scope.data.comentario = valoracion.comentario;
                 });

            $scope.createValoracion = function () {
                $http.put(viviendaContext+'/'+$state.params.viviendaId+'/'+valoracionContext + "/" + idValoracion, $scope.data).then(function (response) {
                    $state.go('valoracionesList', {valoracionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

