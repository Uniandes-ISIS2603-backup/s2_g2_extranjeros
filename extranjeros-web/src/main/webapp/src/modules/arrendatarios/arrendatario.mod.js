(function (ng) {
    var mod = ng.module("arrendatarioModule", ['ui.router']);
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/arrendatarios/';
            $urlRouterProvider.otherwise("/arrendatariosList");

            $stateProvider.state('arrendatarios', {
                url: '/arrendatarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'arrendatario.html',
                        controller: 'arrendatarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('arrendatariosList', {
                url: '/list',
                parent: 'arrendatarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'arrendatario.list.html'
                    }
                },
                data: {
                        requireLogin: true,
                        roles: ['Admin']
                }
                
            }).state('arrendatarioDetail', {
                url: '/{arrendatarioId:int}/detail',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'arrendatario.detail.html',
                        controller: 'arrendatarioDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                
                data: {
                        requireLogin: true,
                        roles: ['Arrendatario', 'Admin']
                }

            }).state('misViviendas', {
                url: '/{arrendatarioId:int}/detail/misViviendas',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'Img/misViviendas.html',
                    }
                },
                
                data: {
                        requireLogin: true,
                        roles: ['Arrendatario', 'Admin']
                }

            }).state('misFacturas', {
                url: '/{arrendatarioId:int}/detail/misFacturas',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'Img/misFacturas.html'
                    }
                },
                
                data: {
                        requireLogin: true,
                        roles: ['Arrendatario', 'Admin']
                }

            }).state('arrendatarioCreate', {
                url: '/create',
                parent: 'arrendatarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/post/arrendatario.new.html',
                        controller: 'arrendatarioPostCtrl'
                    }
                },
                                
                data: {
                        requireLogin: false
                }
                
            }).state('arrendatarioUpdate', {
                url: '/update/{arrendatarioId:int}',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/arrendatario.update.html',
                        controller: 'arrendatarioPutCtrl'
                    }
                },
                
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Admin']
                }
                
            }).state('arrendatarioDelete', {
                url: '/delete/{arrendatarioId:int}',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/arrendatario.delete.html',
                        controller: 'arrendatarioDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Arrendatario', 'Admin']
                }
            });
        }]);
})(window.angular);
