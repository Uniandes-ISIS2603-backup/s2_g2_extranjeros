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
            
            $stateProvider.state('eventosList', {
                
                url: '/eventos/list',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'eventos.list.html',
                        controller: 'eventoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);





