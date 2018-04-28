(function (ng) {
    var mod = ng.module("universidadModule");
    mod.constant("universidadesContext", "api/universidades");
    mod.controller('universidadUpdateCtrl', ['$scope', '$http', 'universidadesContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name universidades.controller:universidadUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar universidades. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} universidadesContext Constante injectada que contiene la ruta
         * donde se encuentra el API de universidades en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $filter Dependencia injectada para hacer filtros sobre
         * arreglos.
         */
        function ($scope, $http, universidadesContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            $scope.selectedItems = [];

            $scope.availableItems = [];

            var idUniversidad = $state.params.universidadId;

            //Consulto la universidad a editar.
            $http.get(universidadesContext + '/' + idUniversidad).then(function (response) {
                var universidad = response.data;
                $scope.data.nombre = universidad.nombre;
                $scope.data.imagen = universidad.imagen;
                $scope.data.direccion = universidad.direccion;
                
            });

            /**
             * @ngdoc function
             * @name createUniversidad
             * @methodOf universidades.controller:universidadUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createUniversidad = function () {
                $http.put(universidadesContext + "/" + idUniversidad, $scope.data).then(function (response) {
                    $state.go('universidadesList', {universidadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);

