<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' />
    <title>CampusAR</title>
    <link rel="stylesheet" type = "text/css" href="/css/style.css"/>
  </head>

  <body>
  	<div id="header">
  		<a href="https://www.uninsubria.it/">Università degli studi dell'Insubria</a>
 	</div>
  	<div id="search" class="dropdown">
		<input id="searchbar" class="searchbar" type="search" placeholder="Search for buildings...">
		<div class="dropdown-content">
			<ul id="buildingsList">
			</ul>
		</div>
  	</div>
  	<iframe id="ar-camera" src="/ar-view" sandbox="allow-scripts allow-same-origin" scrolling="no" tabindex="1">
  	</iframe>
  	
  	<div id="buttons">
	  	<button class="button credit" onClick="btnCredits()"></button>
		<button class="button info" onClick="btnInfo()"></button>
		<button class="button map" onClick="btnMap()"></button>
  	</div>
	
  	<div id="info-panel" tabindex="-1">
		<script type="text/javascript" src="scripts/search_filter.js"></script>
		<script type="text/javascript" src="scripts/btnClick.js"></script>
		<script type="text/javascript" src="scripts/load_places.js"></script>
		<script type="text/javascript" src="scripts/displayInfoPanel.js"></script>
  	</div>	
  </body>
</html>

 
