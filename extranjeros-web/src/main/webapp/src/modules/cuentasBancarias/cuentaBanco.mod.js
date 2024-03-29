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
                },
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Administrador']
                }
            }).state('cuentaBancariaDetalle', {
                url: '/{cuentaBancariaId:int}/detalle',
                parent: 'cuentasBancarias',
                param: {cuentaBancariaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'cuentaBancaria.detail.html',
                        controller: 'cuentaBancariaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Administrador']
                }
            }).state('cuentaBancariaCreate', {
                url: '/createCuenta',
                parent: 'cuentasBancarias',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/post/cuentaBancaria.new.html',
                        controller: 'cuentaBancariaPostCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Administrador']
                }
                
            }).state('cuentaBancariaUpdate', {
                url: '/update/{cuentaBancariaId:int}',
                parent: 'cuentasBancarias',
                param: {
                    arrendatarioId: null,
                    cuentaBancariaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/cuentaBancaria.update.html',
                        controller: 'cuentaBancariaUpdateCtrl'
                    }
                },
                
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Administrador']
                }
            }).state('cuentaBancariaDelete', {
                url: '/delete/{cuentaBancariaId:int}',
                parent: 'cuentasBancarias',
                param: {
                    arrendatarioId: null,
                    cuentaBancariaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/cuentaBancaria.delete.html',
                        controller: 'cuentaBancariaDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Administrador']
                }
            });
        }]);
})(window.angular);


