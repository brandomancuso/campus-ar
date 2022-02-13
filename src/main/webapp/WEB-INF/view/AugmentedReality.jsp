<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>AR</title>
		<script src="https://aframe.io/releases/0.9.2/aframe.min.js"></script>
	    <script src="https://raw.githack.com/AR-js-org/AR.js/master/aframe/build/aframe-ar-nft.js"></script>
	    <script src="https://unpkg.com/aframe-look-at-component@0.8.0/dist/aframe-look-at-component.min.js"></script>
	</head>
	<body>
		<a-scene id="scene" 
    	cursor="rayOrigin: mouse; fuse: true; fuseTimeout: 0;" 
    	raycaster="objects: [clickhandler];"
    	vr-mode-ui = "disabled" 
    	embedded 
    	arjs="sourceType: webcam; debugUIEnabled: false;">
    		<a-camera id="camera" gps-camera="positionMinAccuracy: 1000; 
    				simulateLatitude: 45.79831; simulateLongitude: 8.84807; 
    				minDistance: 5; maxDistance: 250;" rotation-reader></a-camera>
    	</a-scene>
    	<script type="text/javascript" src="scripts/clickHandler.js"></script>
	</body>
</html>