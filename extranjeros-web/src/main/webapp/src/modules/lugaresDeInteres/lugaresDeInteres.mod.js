/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("lugarDeInteresModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/lugaresDeInteres/';
            
            $urlRouterProvider.otherwise("/lugaresDeInteresList");
            
            $stateProvider.state('lugaresDeInteres', {
                
                url: '/lugaresDeInteres',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'lugaresDeInteres.html',
                        controller: 'lugarDeInteresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('lugaresDeInteresList', {
                url: '/list',
                parent: 'lugaresDeInteres',
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugaresDeInteres.list.html'
                    }
                }
            }).state('lugarDeInteresDetail', {
                url: '/{lugarDeInteresId:int}/detail',
                parent: 'lugaresDeInteres',
                param: {lugarDeInteresId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'lugaresDeInteres.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'lugaresDeInteres.detail.html',
                        controller: 'lugarDeInteresDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('lugarDeInteresCreate', {
                url: '/create',
                parent: 'lugaresDeInteres',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/lugaresDeInteres.new.html',
                        controller: 'lugarDeInteresNewCtrl'
                    }
                }
            }).state('lugarDeInteresUpdate', {
                url: '/update/{lugarDeInteresId:int}',
                parent: 'lugaresDeInteres',
                param: {
                    lugarDeInteresId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/lugaresDeInteres.new.html',
                        controller: 'lugarDeInteresUpdateCtrl'
                    }
                }
            }).state('lugarDeInteresDelete', {
                url: '/delete/{lugarDeInteresId:int}',
                parent: 'lugaresDeInteres',
                param: {
                   lugarDeInteresId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/lugaresDeInteres.delete.html',
                        controller: 'lugarDeInteresDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

