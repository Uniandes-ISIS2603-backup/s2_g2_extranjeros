/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    
    var mod = ng.module("estudianteModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            
            var basePath = 'src/modules/estudiante/';
            
            $urlRouterProvider.otherwise("/estudianteList");
            
            $stateProvider.state('estudianteList', {
                
                url: '/estudiante/list',
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'estudiante.list.html',
                        controller: 'estudianteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


