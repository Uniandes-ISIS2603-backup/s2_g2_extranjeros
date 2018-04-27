(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturasContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('facturaNewCtrl', ['$scope', '$http', 'facturaContext', '$state','$rootScope','viviendaContext',
        /**
         * @ngdoc controller
         * @name facturas.controller:facturaNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Facturas. 
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
        function ($scope, $http, facturasContext, $state, $rootScope, viviendaContext) {
            $rootScope.edit = false;

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createFactura
             * @methodOf editorials.controller:editorialNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la factura.
             * @param {Object} editorial Objeto con la nueva de la factura.
             */
            $scope.createFactura = function () {
                $http.post(viviendaContext+'/'+$state.params.viviendaId+'/'+facturasContext, $scope.data).then(function (response) {
                    $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
