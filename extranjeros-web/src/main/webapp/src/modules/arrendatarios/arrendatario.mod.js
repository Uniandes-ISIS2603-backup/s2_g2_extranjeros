(function (ng) {
    var mod = ng.module("arrendatarioModule", ['ui.router']);
    mod.constant("arrendatarioContext", "api/arrendatarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/arrendatarios/';
            $urlRouterProvider.otherwise("/arrendatariosList");

            $stateProvider.state('arrendatarios', {
                url: '/arrendatarios',
                abstract: true,
                data: {
                        requireLogin: true,
                        roles: []
                    },
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
                        roles: ['Administrador']
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
                        roles: ['Arrendatario', 'Administrador']
                }

            }).state('arrendatarioCreate', {
                url: '/create',
                parent: 'arrendatarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/post/arrendatario.new.html',
                        controller: 'arrendatarioPostCtrl'
                    }
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
                    roles: ['Arrendatario', 'Administrador']
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
                    roles: ['Arrendatario', 'Administrador']
                }
            });
        }]);
})(window.angular);
