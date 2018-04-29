(function (ng) {
    var mod = ng.module("universidadModule");
    mod.constant("universidadesContext", "api/universidades");
    mod.controller('universidadNewCtrl', ['$scope', '$http', 'universidadesContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name servicios.controller:universidadNewCtrl
         * @description
         * Definición del controlador auxiliar para crear universidades. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Extranjeros en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, universidadesContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createUniversidad
             * @methodOf universidades.controller:universidadNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la universidad.
             * @param {Object} editorial Objeto con la nueva de la universidad.
             */
            $scope.createUniversidad = function () {
                $http.post(universidadesContext, $scope.data).then(function (response) {
                    $state.go('universidadesList', {universidadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);