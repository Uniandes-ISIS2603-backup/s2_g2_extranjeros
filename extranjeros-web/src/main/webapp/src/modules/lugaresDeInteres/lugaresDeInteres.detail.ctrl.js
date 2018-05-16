(function (ng) {
    var mod = ng.module("lugarDeInteresModule");
    mod.constant("lugarDeInteresContext", "api/lugares");
    mod.controller('lugarDeInteresDetailCtrl', ['$scope', '$http', 'lugarDeInteresContext', '$state', '$filter',
        function ($scope, $http, lugarDeInteresContext, $state, $filter) {
            
            if (($state.params.lugarDeInteresId !== undefined) && ($state.params.lugarDeInteresId !== null)) {
                $http.get(lugarDeInteresContext).then(function (response) {
                    $scope.lugaresDeInteresRecords = response.data;
                    $scope.currentLugarDeInteres = $filter('filter')($scope.lugaresDeInteresRecords, {id: $state.params.lugarDeInteresId}, true)[0];
            var mapOptions = {
            center: new google.maps.LatLng($scope.currentLugarDeInteres.ubicacionLat, $scope.currentLugarDeInteres.ubicacionLon ),
            zoom: 17,
            mapTypeId: google.maps.MapTypeId.HYBRID
}
var myCenter = new google.maps.LatLng($scope.currentLugarDeInteres.ubicacionLat, $scope.currentLugarDeInteres.ubicacionLon );
var map = new google.maps.Map(document.getElementById("map"), mapOptions);
var marker = new google.maps.Marker({position: myCenter});

marker.setMap(map);
                });
            }

        }
    ]);
}
)(window.angular);

