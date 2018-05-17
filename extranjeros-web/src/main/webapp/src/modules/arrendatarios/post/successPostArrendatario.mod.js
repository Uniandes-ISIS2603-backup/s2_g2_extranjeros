(function (ng) {
    var mod = ng.module("succesPostArrendatarioModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/arrendatarios/post/';
            $urlRouterProvider.otherwise("/successPost");

            $stateProvider.state('successPost', {
                url: '/post/welcome',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'successPostArrendatario.html'
                    }
                },
                data: {
                    requireLogin: false
                }
            });
        }
    ]);
})(window.angular);


