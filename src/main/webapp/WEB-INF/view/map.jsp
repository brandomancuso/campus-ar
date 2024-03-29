<!DOCTYPE html>

<html lang="en">
  <head>

    <meta charset="utf-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>CampusAR</title>
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css" 
   	integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A==" 
   	crossorigin=""/>
   	<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js" 
   	integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA==" 
   	crossorigin=""></script>
   
    <link rel="stylesheet" type = "text/css" href="/css/style.css"/>
  </head>

  <body>
  	<div id="header">
  		<a href="https://www.uninsubria.it/">Università degli studi dell'Insubria</a>
 	</div>
 	
 	<div id="mapid"></div>
 	<script type="text/javascript" src="scripts/map.js"></script>

  	<script type="text/javascript" src="scripts/btnClick.js"></script>
  	
	<div id="buttons">
	  	<button class="button credit" onClick="btnCredits()"></button>
		<button class="button info" onClick="btnInfo()"></button>
		<button class="button ar" onClick="btnAr()"></button>
  	</div>
  </body>
</html>

 
