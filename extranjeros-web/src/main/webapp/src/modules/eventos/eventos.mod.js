/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("eventoModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/eventos/';
            
            $urlRouterProvider.otherwise("/eventosList");
            
            $stateProvider.state('eventos', {
                
                url: '/eventos',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'eventos.html',
                        controller: 'eventoCtrl',
                        controllerAs: 'ctrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('eventoCreate', {
                url: '/create',
                parent: 'eventos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'eventos.new.html',
                        controller: 'eventoNewCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('eventosList', {
                url: '/list',
                parent: 'eventos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('eventoDetail', {
                url: '/{eventoId:int}/detail',
                parent: 'eventos',
                param: {eventoId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'eventos.detail.html',
                        controller: 'eventoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }

            }).state('eventoDelete', {
                url: '/delete/{eventoId:int}',
                parent: 'eventos',
                param: {
                    eventoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'eventos.delete.html',
                        controller: 'eventoDeleteCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('eventoFilter', {
                url: '/buscar',
                parent: 'eventos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    },
                    'buscarView':{
                        templateUrl: basePath + 'eventos.buscar.html',
                        controller: 'eventoFilterCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('eventoUpdate', {
                url: '/update/{eventoId:int}',
                parent: 'eventos',
                param: {
                    eventoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'eventos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'eventos.update.html',
                        controller: 'eventoUpdateCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            });
        }
    ]);
})(window.angular);





