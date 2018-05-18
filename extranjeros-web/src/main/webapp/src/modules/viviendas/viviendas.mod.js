/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("viviendasModule", ['ui.router','checklist-model']);
    
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
                }, data: {requireLogin:false}
            }).state('viviendas', {
                
                url: '/viviendas',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: 'viviendaCtrl',
                        controllerAs: 'ctrl'
                    }
                },data: {requireLogin:false}
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
                },data: {requireLogin:false}

            }).state('viviendaUpdate', {
                url: '/update/{viviendaId:int}',
                parent: 'viviendas',
                param: {
                    viviendaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/viviendas.new.html',
                        controller: 'viviendaUpdateCtrl'
                    }
                },data: {requireLogin:true, roles:['Arrendatario']}
            }).state('viviendaDelete', {
                url: '/delete/{viviendaId:int}',
                parent: 'viviendas',
                param: {
                    viviendaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/viviendas.delete.html',
                        controller: 'viviendaDeleteCtrl'
                    }
                },data: {requireLogin:true, roles:['Arrendatario']}
            }).state('viviendasCreate',
            { url: '/create',
                parent: 'viviendas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viviendas.new.html',
                        controller: 'viviendaNewCtrl'
                    }
        },data: {requireLogin:true, roles:['Arrendatario']}});
    }]);
    
})(window.angular);





