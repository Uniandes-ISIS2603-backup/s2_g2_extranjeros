(function (ng) {
    var mod = ng.module("providenciaModule");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('providenciaUpdateCtrl', ['$scope', '$http', 'providenciaContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name servicios.controller:servicioUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar servicios. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} serviciosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de servicios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, providenciaContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idProvidencia = $state.params.servicioId;

            //Consulto la factura a editar.
            $http.get(providenciaContext + '/' + idProvidencia).then(function (response) {
                var providencia = providencia.data;
                $scope.data.pais = providencia.pais;
                $scope.data.region = providencia.region;
            });

            /**
             * @ngdoc function
             * @name createFactura
             * @methodOf facturas.controller:facturaUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createProvidencia= function () {
                $http.put(providenciaContext + "/" + idProvidencia, $scope.data).then(function (response) {
                    $state.go('providenciaList', {providenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

