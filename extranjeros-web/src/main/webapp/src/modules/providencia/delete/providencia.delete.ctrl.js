(function (ng){
    var mod= ng.module("providenciaModule");
    mod.constant("providenciaContext","api/providencia");
    mod.controller('providenciaDeleteCtrl', ['$scope', '$http', 'providenciaContext', '$state',
        /**
         * @ngdoc controller
         * @name authors.controller:authorDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Lugares de Interes. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} authorsContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Lugares de Interes en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, providenciaContext, $state){
            var idProvidencia=$state.params.providenciaId;
            /**
             * @ngdoc function
             * @name deleteLugarDeInteres
             * @methodOf lugaresDeInteres.controller:lugarDeInteresDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar un lugar de interes.
             * @param {String} id El ID del lugar de interes a eliminar.
             */
            $scope.deleteProvidencia=function(){
                $http.delete(providenciaContext+'/'+idProvidencia,{}).then(function(response){
                    $state.go('providenciaList', {providenciaId: response.data.id}, {reload: true});
                });
        };
    }
    ]);
})(window.angular);



