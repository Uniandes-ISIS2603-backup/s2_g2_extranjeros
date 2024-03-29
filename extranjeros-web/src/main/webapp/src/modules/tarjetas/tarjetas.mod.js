/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("tarjetaModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/tarjetas/';
            
            $urlRouterProvider.otherwise("/tarjetasList");
            
            $stateProvider.state('tarjetas', {
                
                url: '/tarjetas',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('tarjetasList', {
                url: '/list',
                parent: 'tarjetas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('tarjetaUpdate', {
                url: '/update/{tarjetaId:int}',
                parent: 'tarjetas',
                param: {
                    tarjetaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'tarjetas.update.html',
                        controller: 'tarjetaUpdateCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('tarjetaDetail', {
                url: '/{tarjetaId:int}/detail',
                parent: 'tarjetas',
                param: {tarjetaId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'tarjetas.detail.html',
                        controller: 'tarjetaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }

            }).state('tarjetaCreate', {
                url: '/create',
                parent: 'tarjetas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'tarjetas.new.html',
                        controller: 'tarjetaNewCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
            }).state('tarjetaDelete', {
                url: '/delete/{tarjetaId:int}',
                parent: 'tarjetas',
                param: {
                    tarjetaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjetas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'tarjetas.delete.html',
                        controller: 'tarjetaDeleteCtrl'
                    }
                }, data: {
                    requireLogin: true, roles:['Admin', 'Arrendatario']
                }
});
        }
    ]);
})(window.angular);





