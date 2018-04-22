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
                    'listView': {
                        templateUrl: basePath + 'arrendatario.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'arrendatario.detail.html',
                        controller: 'arrendatarioDetailCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }]);
})(window.angular);
