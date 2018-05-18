(function (ng) {
    var mod = ng.module("eventoModule");
    mod.constant("eventoContext", "api/eventos");
    mod.controller('eventoDetailCtrl', ['$scope', '$http', 'eventoContext', '$state', '$filter',
        function ($scope, $http, eventoContext, $state, $filter) {

            if (($state.params.eventoId !== undefined) && ($state.params.eventoId !== null)) {
                $http.get(eventoContext).then(function (response) {
                    $scope.eventosRecords = response.data;
                    $scope.currentEvento = $filter('filter')($scope.eventosRecords, {id: $state.params.eventoId}, true)[0];
                
                var mapOptions = {
                center: new google.maps.LatLng($scope.currentEvento.ubicacionLat, $scope.currentEvento.ubicacionLon ),
                zoom: 17,
                mapTypeId: google.maps.MapTypeId.terrain 
                }
                var myCenter = new google.maps.LatLng($scope.currentEvento.ubicacionLat, $scope.currentEvento.ubicacionLon );
                var map = new google.maps.Map(document.getElementById("map"), mapOptions);
                var marker = new google.maps.Marker({position: myCenter});

                marker.setMap(map);
                });
            }
        }
    ]);
}
)(window.angular);


