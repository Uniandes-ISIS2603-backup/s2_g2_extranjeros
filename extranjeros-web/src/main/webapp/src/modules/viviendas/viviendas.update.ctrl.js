(function (ng) {
    var mod = ng.module("viviendasModule");
    mod.constant("viviendaContext", "api/viviendas");
    mod.controller('viviendaUpdateCtrl', ['$scope', '$http', 'viviendaContext', '$state','$rootScope',
        /**
         * @ngdoc controller
         * @name facturas.controller:facturaUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar viviendas. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} viviendasContext Constante injectada que contiene la ruta
         * donde se encuentra el API de viviendas en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, viviendaContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idVivienda = $state.params.vivivendaId;

            //Consulto la factura a editar.
            $http.get(viviendaContext+'/'+$state.params.viviendaId).then(function (response) {
                var vivienda = response.data;
                $scope.data.direccion = vivienda.direccion;
                $scope.data.capacidad = vivienda.capacidad;
                $scope.data.disponible = vivienda.disponible;
                $scope.data.inquilinos = vivienda.inquilinos;
                $scope.data.latitud = vivienda.latitud;
                $scope.data.longitud= vivienda.longitud;
                $scope.data.tipoAlojamiento = vivienda.tipoAlojamiento;
                $scope.data.precioMensual = vivienda.precioMensual;
            });

            /**
             * @ngdoc function
             * @name createFactura
             * @methodOf facturas.controller:facturaUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createVivienda = function () {
                $http.put(viviendaContext+'/'+$state.params.viviendaId, $scope.data).then(function (response) {
                    $state.go('viviendasList', {viviendaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

