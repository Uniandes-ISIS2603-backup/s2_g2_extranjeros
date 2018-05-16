(function (ng) {
    var mod = ng.module("viviendasModule");
   mod.constant("viviendaContext", "api/viviendas"); 
   mod.constant("servicioContext", "api/servicios"); 
  
   mod.controller('viviendaNewCtrl', ['$scope', '$http','$state', '$rootScope', 'viviendaContext','servicioContext',
       
        function($scope, $http, $state, $rootScope, viviendaContext,servicioContext) {

            $rootScope.edit = false;

            $scope.data = {};
            
            $scope.data2 = {};
            
            $http.get(servicioContext).then(function (response){
            $scope.data2.servicios = response.data;    
            });
           
            $scope.createVivienda = function () {
                $http.post(viviendaContext, $scope.data).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
   }
    ]);
}
)(window.angular);

