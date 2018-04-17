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
            
            $stateProvider.state('tarjetasList', {
                
                url: '/tarjetas/list',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.list.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);





