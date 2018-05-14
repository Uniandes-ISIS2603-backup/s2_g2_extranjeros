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
            $scope.data2={};
            //Consulto los servicios de la vivienda a facturar.
            $http.get(viviendaContext+'/'+$state.params.viviendaId).then(function (response) {
                var vivienda = response.data;
                $scope.data2.serviciosIncluidos = vivienda.serviciosIncluidos;
                $scope.data2.serviciosExtra = vivienda.serviciosExtra;
            });
            /**
             * @ngdoc function
             * @name createFactura
             * @methodOf editorials.controller:editorialNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la factura.
             * @param {Object} editorial Objeto con la nueva de la factura.
             */
            var day;
            var month;
            var year;
            var date = new Date();
            day = date.getDate();
            month = date.getMonth() + 1;
            year = date.getFullYear();
            if (month < 10) month = "0" + month;
            if (day < 10) day = "0" + day;
            var today = year + "-" + month + "-" + day;
            document.getElementById('fechaHoyIN').min = today;
            document.getElementById("fechaHoy").innerHTML=today;
            
            var newDay=date.getDate();
            var newMonth;  
            if(date.getMonth() + 2==13)
            {
                newMonth=1;
                newYear=date.getFullYear()+1;
            }
            else
            { 
                newMonth = date.getMonth()+2;
            }
            if(newDay==31&&(newMonth==4||newMonth==6||newMonth==9||newMonth==11))
            {
                newMonth++;
                newDay=1;
            }
            if(newDay>28&&newMonth==2)
            {
                newMonth++;
                newDay+=newDay-28;
            }
            if (newMonth < 10) newMonth = "0" + newMonth;
            if (newDay < 10) newDay = "0" + newDay;
            var notoday = year + "-" + newMonth + "-" + newDay;
            document.getElementById('fechaMesDespuesIN').min = notoday;
            document.getElementById("fechaMesDespues").innerHTML=notoday;
            $scope.createFactura = function () {
                $http.post(viviendaContext+'/'+$state.params.viviendaId+'/'+facturasContext, $scope.data).then(function (response) {
                    $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
