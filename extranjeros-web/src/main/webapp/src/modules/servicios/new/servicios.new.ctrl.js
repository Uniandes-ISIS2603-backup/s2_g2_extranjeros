(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.controller('servicioNewCtrl', ['$scope', '$http', 'serviciosContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name servicios.controller:servicioNewCtrl
         * @description
         * Definición del controlador auxiliar para crear servicios. 
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
        function ($scope, $http, serviciosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createServicio
             * @methodOf editorials.controller:editorialNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la factura.
             * @param {Object} editorial Objeto con la nueva de la factura.
             */
            $scope.createServicio = function () {
                $http.post(serviciosContext, $scope.data).then(function (response) {
                    $state.go('serviciosList', {servicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);