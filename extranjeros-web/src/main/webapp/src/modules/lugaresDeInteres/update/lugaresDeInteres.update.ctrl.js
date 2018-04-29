(function (ng) {
    var mod = ng.module("lugarDeInteresModule");
    mod.constant("lugaresDeInteresContext", "api/lugares");
    mod.controller('lugarDeInteresUpdateCtrl', ['$scope', '$http', 'lugaresDeInteresContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name lugaresDeInteres.controller:lugarDeInteresUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar lugares de interes. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} lugaresDeInteresContext Constante injectada que contiene la ruta
         * donde se encuentra el API de extranjeros en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, lugaresDeInteresContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idLugarDeInteres = $state.params.lugarDeInteresId;

            //Consulto el lugar de interes a editar.
            $http.get(lugaresDeInteresContext + '/' + idLugarDeInteres).then(function (response) {
                var lugarDeInteres = response.data;
                $scope.data.nombre = lugarDeInteres.nombre;
                $scope.data.telefono = lugarDeInteres.telefono;
                $scope.data.tipo = lugarDeInteres.tipo;
                $scope.data.direccion = lugarDeInteres.direccion;
                
            });

            /**
             * @ngdoc function
             * @name createLugarDeInteres
             * @methodOf lugaresDeInteres.controller:lugarDeInteresUpdateCtrl
             * @description
             * Crea un nuevo lugar de interes con los datos nuevo y la información del
             * $scope.
             */
            $scope.createLugarDeInteres = function () {
                $http.put(lugaresDeInteresContext + "/" + idLugarDeInteres, $scope.data).then(function (response) {
                    $state.go('lugaresDeInteresList', {lugarDeInteresId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

