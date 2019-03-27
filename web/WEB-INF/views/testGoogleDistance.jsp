<!DOCTYPE html>
<html>
<head>
    <title>Distance Matrix Service</title>
</head>
<body>
<div id="right-panel">
    <div id="inputs">
        <pre>
var origin2 = 'Greenwich, England';
var destinationA = 'Stockholm, Sweden';
        </pre>
    </div>
    <div>
        <strong>Results</strong>
    </div>
    <div id="output"></div>
</div>
<div id="map"></div>
<script>
    function initMap() {
        var markersArray = [];

        var origin2 = 'Greenwich, England';
        var destinationA = 'Stockholm, Sweden';

        var service = new google.maps.DistanceMatrixService;
        service.getDistanceMatrix({
            origins: [origin2],
            destinations: [destinationA],
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function(response, status) {
            if (status !== 'OK') {
                alert('Error was: ' + status);
            } else {
                var originList = response.originAddresses;
                var destinationList = response.destinationAddresses;
                var outputDiv = document.getElementById('output');
                outputDiv.innerHTML = '';


                for (var i = 0; i < originList.length; i++) {
                    var results = response.rows[i].elements;
                    for (var j = 0; j < results.length; j++) {
                        outputDiv.innerHTML += originList[i] + ' to ' + destinationList[j] +
                            ': ' + results[j].distance.text + ' in ' +
                            results[j].duration.text + '<br>';
                    }
                }
            }
        });
    }


</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBaebZ5i6230uvRvSiAqecuRv_pEqnXcA&callback=initMap">
</script>
</body>
</html>