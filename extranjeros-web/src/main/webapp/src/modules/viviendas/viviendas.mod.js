/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("viviendasModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/viviendas/';
            
            $urlRouterProvider.otherwise("/viviendasList");
            
            $stateProvider.state('viviendasList', {
                
                url: '/viviendas/list',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.list.html',
                        controller: 'viviendaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('viviendas', {
                
                url: '/viviendas',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: 'viviendaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('viviendaDetail', {
                url: '/{viviendaId:int}/detail',
                parent: 'viviendas',
                param: {viviendaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'viviendas.detail.html',
                        controller: 'viviendaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('viviendasCreate',
            { url: '/create',
                parent: 'viviendas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/viviendas.new.html',
                        controller: 'viviendaNewCtrl'
                    }
        }});
    }]);
    
})(window.angular);





