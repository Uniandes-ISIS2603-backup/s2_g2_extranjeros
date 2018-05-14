(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/eventos");
    mod.controller('eventoDeleteCtrl', ['$scope', '$http', 'eventoContext', '$state', '$filter',
        function ($scope, $http, eventoContext, $state, $filter) {

            var idEvento = $state.params.eventoId;
            $scope.deleteEvento = function () {
                $http.delete(eventoContext + '/' + idEvento, {}).then(function (response) {
                    $state.go('eventosList', {eventoId: response.data.id}, {reload: true});
                });
};
        }
    ]);
}
)(window.angular);


