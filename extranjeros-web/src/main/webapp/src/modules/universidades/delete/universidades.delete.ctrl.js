(function (ng){
    var mod= ng.module("universidadModule");
    mod.constant("universidadesContext","api/universidades");
    mod.controller('universidadDeleteCtrl', ['$scope', '$http', 'universidadesContext', '$state',
        /**
         * @ngdoc controller
         * @name authors.controller:authorDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Universidades. 
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
        function ($scope, $http, universidadesContext, $state){
            var idUniversidad=$state.params.universidadId;
            /**
             * @ngdoc function
             * @name deleteUniversidad
             * @methodOf universidades.controller:universidadDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar una universidad.
             * @param {String} id El ID de la universidad a eliminar.
             */
            $scope.deleteUniversidad=function(){
                $http.delete(universidadesContext+'/'+idUniversidad,{}).then(function(response){
                    $state.go('universidadesList', {universidadId: response.data.id}, {reload: true});
                });
        };
    }
    ]);
})(window.angular);
