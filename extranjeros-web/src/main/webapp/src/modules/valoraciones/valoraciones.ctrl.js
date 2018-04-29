(function (ng) {
    var mod = ng.module("valoracionModule");
    mod.constant("valoracionContext", "valoraciones");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('valoracionCtrl', ['$scope', '$http', 'valoracionContext', 'viviendaContext','$state',
        function ($scope, $http, valoracionContext,  viviendaContext, $state) {
            console.log("sapo");
            $http.get(viviendaContext+'/'+$state.params.viviendaId+'/'+valoracionContext).then(function (response) {
                $scope.valoracionesRecords = response.data;
            });
        }
    ]);
}
)(window.angular);


