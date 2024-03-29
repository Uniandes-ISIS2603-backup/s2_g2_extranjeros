(function (ng) {
    
    var mod = ng.module("facturaModule", ['viviendasModule','ui.router','checklist-model']);
    mod.constant("facturasContext", "facturas");
    mod.constant("viviendaContext", "api/viviendas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/facturas/';
            
            $urlRouterProvider.otherwise("/facturasList");
            
            $stateProvider.state('facturas', {
                
                url: '/{viviendaId:int}/facturas',
                abstract:true,
                
                param: {viviendaId: null},
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.html',
                        controller: 'facturaICtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturasList', {
                url: '/list',
                parent: 'facturas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html',
                        
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


