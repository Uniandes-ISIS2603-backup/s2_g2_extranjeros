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
                }
            }).state('arrendatarioDetail', {
                url: '/{arrendatarioId:int}/detail',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
//                    'listView': {
//                        templateUrl: basePath + 'arrendatario.list.html'
//                    },
                    'detailView': {
                        templateUrl: basePath + 'arrendatario.detail.html',
                        controller: 'arrendatarioDetailCtrl',
                        controllerAs: 'ctrl'
                    }

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
                }
            }).state('arrendatarioDelete', {
                url: '/delete/{arrendatarioId:int}',
                parent: 'arrendatarios',
                param: {
                    arrendatarioId: null
                },
                views: {
                    'detailView': {
                        controller: 'arrendatarioDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
