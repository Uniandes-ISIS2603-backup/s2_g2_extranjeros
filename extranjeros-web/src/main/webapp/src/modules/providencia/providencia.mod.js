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
            
            $stateProvider.state('providenciaList', {
                
                url: '/providencia/list',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'providencia.list.html',
                        controller: 'providenciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


