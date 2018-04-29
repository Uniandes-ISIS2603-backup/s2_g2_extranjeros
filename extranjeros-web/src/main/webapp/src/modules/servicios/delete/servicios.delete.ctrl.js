(function (ng){
    var mod= ng.module("servicioModule");
    mod.constant("serviciosContext","api/servicios");
    mod.controller('servicioDeleteCtrl', ['$scope', '$http', 'serviciosContext', '$state',
        /**
         * @ngdoc controller
         * @name authors.controller:authorDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Autores. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} authorsContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Autores en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, serviciosContext, $state){
            var idServicio=$state.params.servicioId;
            /**
             * @ngdoc function
             * @name deleteServicio
             * @methodOf servicios.controller:servicioDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar el servicio.
             * @param {String} id El ID del servicio a eliminar.
             */
            $scope.deleteServicio=function(){
                $http.delete(serviciosContext+'/'+idServicio,{}).then(function(response){
                    $state.go('serviciosList', {servicioId: response.data.id}, {reload: true});
                });
        };
    }
    ]);
})(window.angular);
