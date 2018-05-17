(function (ng) {
    var mod = ng.module("estudianteModule");
    mod.constant("estudianteContext", "api/estudiante");
    mod.constant("universidadesContext", "api/universidades");
    mod.constant("providenciaContext", "api/providencia");
    mod.controller('estudianteUpdateCtrl', ['$scope', '$http', 'estudianteContext', '$state', '$rootScope', 'universidadesContext', 'providenciaContext',
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
        function ($scope, $http, estudianteContext, $state, $rootScope, universidadesContext, providenciaContext) {
            $rootScope.edit = false;
            $scope.universidadesRecords = [];
            $scope.providenciaRecords = [];
            $scope.data = {};
            $http.get(universidadesContext).then(function (response) {
                $scope.universidadesRecords = response.data;
            });

            $http.get(providenciaContext).then(function (response) {
                $scope.providenciaRecords = response.data;
            });

            $scope.selectedItems = [];

            $scope.availableItems = [];



            var idEstudiante = $state.params.estudianteId;

            //Consulto la universidad a editar.
            $http.get(estudianteContext + '/' + idEstudiante).then(function (response) {
                var estudiante = response.data;
                $scope.data.nombre = estudiante.nombre;
                $scope.data.usuario = estudiante.usuario;
                $scope.data.clave = estudiante.clave;
                $scope.data.correo = estudiante.correo;
                $scope.data.cedula = estudiante.cedula;
                $scope.data.edad = estudiante.edad;
                $scope.data.celular = estudiante.celular;

            });



            /**
             * @ngdoc function
             * @name createUniversidad
             * @methodOf universidades.controller:universidadUpdateCtrl
             * @description
             * Crea un nuevo autor con los libros nuevos y la información del
             * $scope.
             */
            $scope.createEstudiante = function () {
                $http.put(estudianteContext + "/" + idEstudiante, $scope.data).then(function (response) {
                    $state.go('estudianteList', {estudianteId: response.data.id}, {reload: true});
                });
            };
            
           
        }
    ]);
}
)(window.angular);


