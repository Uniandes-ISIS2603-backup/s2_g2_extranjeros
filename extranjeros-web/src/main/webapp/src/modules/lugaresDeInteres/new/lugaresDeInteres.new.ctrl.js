(function (ng) {
    var mod = ng.module("lugarDeInteresModule");
    mod.constant("lugaresDeInteresContext", "api/lugares");
    mod.controller('lugarDeInteresNewCtrl', ['$scope', '$http', 'lugaresDeInteresContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name servicios.controller:lugarDeInteresNewCtrl
         * @description
         * Definición del controlador auxiliar para crear lugares de interes. 
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
        function ($scope, $http, lugaresDeInteresContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createLugarDeInteres
             * @methodOf lugaresDeInteres.controller:lugarDeInteresNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el lugar de interes.
             * @param {Object} editorial Objeto con la nueva del lugar de interes.
             */
            
            $scope.createLugarDeInteres = function () {
                $http.post(lugaresDeInteresContext, $scope.data).then(function (response) {
                    $state.go('lugaresDeInteresList', {lugarDeInteresId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);