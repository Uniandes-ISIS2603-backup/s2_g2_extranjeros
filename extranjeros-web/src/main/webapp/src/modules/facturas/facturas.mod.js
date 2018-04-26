(function (ng) {
    
    var mod = ng.module("facturaModule", ['viviendasModule','ui.router']);
    mod.constant("facturasContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/facturas/';
            
            $urlRouterProvider.otherwise("/facturasList");
            
            $stateProvider.state('facturas', {
                
                url: '/facturas',
                abstract:true,
                parent:'viviendaDetail',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.html'
                    }
                }
            }).state('facturasList', {
                url: '/list',
                parent: 'facturas',
                param: {viviendaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html',
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturaDetail', {
                url: '/{facturaId:int}/detail',
                parent: 'facturas',
                param: {facturaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'facturas.detail.html',
                        controller: 'facturaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('facturaCreate', {
                url: '/create',
                parent: 'facturas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/facturas.new.html',
                        controller: 'facturaNewCtrl'
                    }
                }
            }).state('facturaUpdate', {
                url: '/update/{facturaId:int}',
                parent: 'facturas',
                param: {
                    facturaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/facturas.new.html',
                        controller: 'facturaUpdateCtrl'
                    }
                }
            }).state('facturaDelete', {
                url: '/delete/{facturaId:int}',
                parent: 'facturas',
                param: {
                    facturaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/facturas.delete.html',
                        controller: 'facturaDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


