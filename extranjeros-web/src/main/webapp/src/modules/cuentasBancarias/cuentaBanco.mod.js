(function (ng) {
    var mod = ng.module("cuentaBancariaModule", ['arrendatarioModule', 'ui.router']);
    mod.constant("cuentaBancariaContext", "cuentasBancarias");
    mod.constant("arrendatarioContext", "api/arrendatarios");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/cuentasBancarias/';
            $urlRouterProvider.otherwise("/cuentasBancariasList");

            $stateProvider.state('cuentasBancarias', {
                url: '/cuentasBancarias',
                abstract: true,
                parent: 'arrendatarioDetail',
                views: {
                    childrenView: {
                        templateUrl: basePath + 'cuentaBancaria.html'
                    }
                }
            }).state('cuentasBancariasList', {
                url: '/list',
                parent: 'cuentasBancarias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'cuentaBancaria.list.html',
                        controller: 'cuentaBancariaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);


