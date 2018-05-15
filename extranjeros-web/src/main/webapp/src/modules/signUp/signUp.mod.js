(function (ng) {
    var mod = ng.module("signUpModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/signUp/';
            $urlRouterProvider.otherwise("/signUp");

            $stateProvider.state('signUp', {
                url: '/signUp',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'signup.html'
                    }
                }
            });
        }
    ]);
})(window.angular);

