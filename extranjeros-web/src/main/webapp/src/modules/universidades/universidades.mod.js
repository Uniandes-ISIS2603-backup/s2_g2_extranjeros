/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("universidadModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/universidades/';
            
            $urlRouterProvider.otherwise("/universidadesList");
            
            $stateProvider.state('universidades', {
                
                url: '/universidades',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'universidades.html',
                        controller: 'universidadCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('universidadesList', {
                url: '/list',
                parent: 'universidades',
                views: {
                    'listView': {
                        templateUrl: basePath + 'universidades.list.html'
                    }
                }
            }).state('universidadDetail', {
                url: '/{universidadId:int}/detail',
                parent: 'universidades',
                param: {universidadId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'universidades.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'universidades.detail.html',
                        controller: 'universidadDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('universidadCreate', {
                url: '/universidad',
                parent: 'universidades',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/universidades.new.html',
                        controller: 'universidadNewCtrl'
                    }
                }
            }).state('universidadUpdate', {
                url: '/update/{universidadId:int}',
                parent: 'universidades',
                param: {
                    universidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/universidades.new.html',
                        controller: 'universidadUpdateCtrl'
                    }
                }
            }).state('universidadDelete', {
                url: '/delete/{universidadId:int}',
                parent: 'universidades',
                param: {
                   universidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/universidades.delete.html',
                        controller: 'universidadDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

