 <link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
		var altitud = <?php echo $altitud;?>;
 		var latitud = <?php echo $latitud;?>;
		 var stockholm = new google.maps.LatLng(latitud,altitud);
		 var parliament = new google.maps.LatLng(latitud, altitud);
		 var marker;
		 var map;
		
		 function initialize() {
		   var mapOptions = {
		     zoom: 13,
		     mapTypeId: google.maps.MapTypeId.ROADMAP,
		     center: stockholm
		   };
		
		   map = new google.maps.Map(document.getElementById("map_canvas"),
		           mapOptions);
		         
		   marker = new google.maps.Marker({
		     map:map,
		     draggable:true,
		     animation: google.maps.Animation.DROP,
		     position: parliament
		   });
		   google.maps.event.addListener(marker, 'click', toggleBounce);
		 }
		
		 function toggleBounce() {
		
		   if (marker.getAnimation() != null) {
		     marker.setAnimation(null);
		   } else {
		     marker.setAnimation(google.maps.Animation.BOUNCE);
		   }
		 }
      google.maps.event.addDomListener(window, 'load', initialize);
    </script>  
    
<div id="map_canvas" style="height: 500px;width:500px;"></div>



