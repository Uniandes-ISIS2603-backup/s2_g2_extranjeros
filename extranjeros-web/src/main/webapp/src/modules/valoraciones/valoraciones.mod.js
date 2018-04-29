(function (ng) {
    
    var mod = ng.module("valoracionModule", ['viviendasModule','ui.router']);
    mod.constant("valoracionesContext", "valoraciones");
    mod.constant("viviendaContext", "api/viviendas");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/valoraciones/';
            
            $urlRouterProvider.otherwise("/valoracionesList");
            
            $stateProvider.state('valoraciones', {
                
                url: '/{viviendaId:int}/valoraciones',
                abstract:true,
                
                param: {viviendaId: null},
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'valoraciones.html',
                        controller: 'valoracionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('valoracionesList', {
                url: '/list',
                parent: 'valoraciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'valoraciones.list.html',
                        
                    }
                }
            }).state('valoracionDetail', {
                url: '/{valoracionId:int}/detail',
                parent: 'valoraciones',
                param: {valoracionId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'valoraciones.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'valoraciones.detail.html',
                        controller: 'valoracionDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('valoracionCreate', {
                url: '/create',
                parent: 'valoraciones',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'valoraciones.new.html',
                        controller: 'valoracionNewCtrl'
                    }
                }
            }).state('valoracionUpdate', {
                url: '/update/{valoracionId:int}',
                parent: 'valoraciones',
                param: {
                    valoracionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/valoraciones.new.html',
                        controller: 'valoracionUpdateCtrl'
                    }
                }
            }).state('valoracionDelete', {
                url: '/delete/{valoracionId:int}',
                parent: 'valoraciones',
                param: {
                    valoracionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/valoraciones.delete.html',
                        controller: 'valoracionDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


