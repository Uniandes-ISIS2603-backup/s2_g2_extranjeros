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

            $stateProvider.state('estudiante', {
                url: '/estudiante',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'estudiante.html',
                        controller: 'estudianteCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }).state('estudianteList', {
                url: '/list',
                parent: 'estudiante',
                views: {
                    'listView': {
                        templateUrl: basePath + 'estudiante.list.html'
                    }
                }
            }).state('estudianteIngresar', {
                url: '/signEstudiante',
                parent: 'estudiante',
                views: {
                    'listView': {
                        templateUrl: basePath + 'loginEstudiante.html'
                    }
                }
            }).state('estudianteUpdate', {
                url: '/update/{estudianteId:int}',
                parent: 'estudiante',
                param: {
                    estudianteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/update/estudiante.update.html',
                        controller: 'estudianteUpdateCtrl'
                    }
                }
            }).state('estudianteCreate', {
                url: '/create',
                parent: 'estudiante',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/nuevo/estudiante.new.html',
                        controller: 'estudiantePostCtrl'
                    }
                }
            }).state('estudianteDelete', {
                url: '/delete/{estudianteId:int}',
                parent: 'estudiante',
                param: {
                    estudianteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/estudiante.delete.html',
                        controller: 'estudianteDeleteCtrl'
                    }
                }
            }).state('estudianteDetail', {
                url: '/{estudianteId:int}/detail',
                parent: 'estudiante',
                param: {estudianteId: null},
                views: {
                    'listView': {
                        templateUrl: basePath + 'estudiante.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'estudiante.detail.html',
                        controller: 'estudianteDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });

        }
    ]);
})(window.angular);


