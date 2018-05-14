(function (ng) {
    var mod = ng.module("viviendasModule");
   mod.constant("viviendaContext", "api/viviendas"); 
   mod.constant("servicioContext", "api/servicios"); 
   mod.constant("lugaresDeInteresContext", "api/lugares"); 
   mod.constant("estudiantesContext", "api/estudiantes"); 
  
   mod.controller('viviendaNewCtrl', ['$scope', '$http','$state', '$rootScope', 'viviendaContext','servicioContext',
       'lugaresDeInteresContext','estudiantesContext',
        function($scope, $http, $state, $rootScope, viviendaContext,servicioContext,lugaresDeInteresContext,estudiantesContext) {

            $rootScope.edit = false;

            $scope.data = {};
            $scope.servicios = $http.get(servicioContext);
            
            $scope.createVivienda = function () {
                $http.post(viviendaContext, $scope.data).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
   }
    ]);
}
)(window.angular);

