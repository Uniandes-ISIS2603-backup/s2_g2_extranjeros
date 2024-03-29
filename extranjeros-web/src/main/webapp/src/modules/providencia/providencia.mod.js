/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("providenciaModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/providencia/';
            
            $urlRouterProvider.otherwise("/providenciaList");
            
            $stateProvider.state('providencia', {
                url: '/providencia',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'providencia.html',
                        controller: 'providenciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                
            }).state('providenciaList', {
                url: '/list',
                parent: 'providencia',
                views: {
                    'listView': {
                        templateUrl: basePath + 'providencia.list.html'
                    }
                },
                data: {
                    requireLogin: false
                }
                
            }).state('providenciaCreate', {
                url: '/create',
                parent: 'providencia',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/nuevo/providencia.nuevo.html',
                        controller: 'providenciaPostCtrl'
                    }
             },
             data: {
                    requireLogin: true,
                    roles: ['Admin']
                }
            }).state('providenciaDelete', {
                url: '/delete/{providenciaId:int}',
                parent: 'providencia',
                param: {
                   providenciaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/providencia.delete.html',
                        controller: 'providenciaDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Admin']
                }
            }).state('providenciaUpdate', {
                url: '/update/{providenciaId:int}',
                parent: 'providencia',
                param: {
                    estudianteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/nuevo/providencia.nuevo.html',
                        controller: 'providenciaUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['Admin']
                }
            }).state('providenciaDetail', {
                url: '/{providenciaId:int}/detail',
                parent: 'providencia',
                param: {providenciaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'providencia.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'providencia.detail.html',
                        controller: 'providenciaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
            
    }
    ]);
})(window.angular);
        


