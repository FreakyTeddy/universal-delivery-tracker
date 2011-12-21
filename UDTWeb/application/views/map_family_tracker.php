<link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
 
 <script type="text/javascript">

 
 	var map;
 	var infowindow = null;
 	var markers = [];
 	
	function initialize(data) {
		 
		var mapOptions = {
			zoom: 10,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
	
		map = new google.maps.Map(document.getElementById("map_canvas"),mapOptions);
	
		for( var track in data ){
			var pos = new google.maps.LatLng(data[track]['lat']/1000000, data[track]['lon']/1000000);
			var track_description = jQuery.parseJSON(data[track]['descripcion']);
			var marker = new google.maps.Marker({
				map:map,
				draggable:false,
				animation: google.maps.Animation.DROP,
				position: pos,
				title: track_description.nombre
			});
			markers[track_description.nombre] = track_description;
			
			
			var infowindow = new google.maps.InfoWindow();
	        google.maps.event.addListener(marker, 'click', function() {
	        	infowindow.setContent('<div style="font-size:14px;float:left;">'+markers[this.title].nombre+'</div><img src='+markers[this.title].img+'/>');
	            infowindow.open(map, this);
	        });
		}
			
		map.setCenter(pos);
	}


	 function cron()
	{
		var id=<?php echo $id_ticket;?>;
		
		$.getJSON("<?=base_url()?>index.php/tracker/ajax_map_family?id_ticket="+id,
			function(data){
				initialize(data);
			});
	};	
			 
	google.maps.event.addDomListener(window, 'load', cron);
		
	

time=setInterval(cron, 30000); //cada 30 segundos llamará a la función
</script>		    
<?php if(isset($descripcion)){ ?>	
 <div style="background-color: rgb(255, 255, 255); width: 500px; height: 80px; text-align: center;font-size:24px;padding-top:30px;" id="info">
 <?php echo $descripcion; 
 ?>
</div>
<?php } ?>
<div id="map_canvas" style="height: 500px;width:500px;"></div>
