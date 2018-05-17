(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('logoutCtrl', ['$rootScope', '$state',
        function ($rootScope, $state) {
            if (sessionStorage.getItem("User")) {
                sessionStorage.clear();
            } else {
                $state.go('viviendasList', {}, {reload: true});
            }
        }
    ]);
}
)(window.angular);


