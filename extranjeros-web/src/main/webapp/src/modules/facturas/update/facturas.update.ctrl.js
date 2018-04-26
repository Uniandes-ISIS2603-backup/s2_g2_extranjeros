(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturasContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('facturaCtrl', ['$scope', '$http', 'facturaContext', '$state','$rootScope','viviendaContext',
        /**
         * @ngdoc controller
         * @name facturas.controller:facturaUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar facturas. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} facturasContext Constante injectada que contiene la ruta
         * donde se encuentra el API de facturas en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, facturasContext, $state, $rootScope, viviendaContext) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idFactura = $state.params.facturaId;

            //Consulto la factura a editar.
            $http.get(facturasContext + '/' + idFactura).then(function (response) {
                var factura = response.data;
                $scope.data.costoFijo = factura.costoFijo;
                $scope.data.costosAdicionales = factura.costosAdicionales;
                $scope.data.dividirCuentaServicios = factura.dividirCuentaServicios;
                $scope.data.fechaEntrada = new Date(factura.fechaEntrada);
                $scope.data.fechaSalida= new Date(factura.fechaSalida)
                $scope.data.formaDePago = factura.formaDePago;
            });

            /**
             * @ngdoc function
             * @name createFactura
             * @methodOf facturas.controller:facturaUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createFactura = function () {
                $http.put(viviendaContext+'/'+$state.params.viviendaId+'/'+facturasContext + "/" + idFactura, $scope.data).then(function (response) {
                    $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

