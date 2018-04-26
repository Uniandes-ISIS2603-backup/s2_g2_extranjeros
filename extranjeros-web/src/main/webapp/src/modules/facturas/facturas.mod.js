(function (ng) {
    
    var mod = ng.module("facturaModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/facturas/';
            
            $urlRouterProvider.otherwise("/facturasList");
            
            $stateProvider.state('facturas', {
                
                url: '/facturas',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.html',
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('facturasList', {
                url: '/list',
                parent: 'facturas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'facturas.list.html'
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


