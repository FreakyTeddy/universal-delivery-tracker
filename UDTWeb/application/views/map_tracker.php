 <link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
		
		var tracks =  <?php echo $json_tracks; ?>;
		var map;
		
		 function initialize() {
			var mapOptions = {
				zoom: 13,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
		
			map = new google.maps.Map(document.getElementById("map_canvas"),
		           mapOptions);
			var marker;        
			var pos;
			var i = 0; 
			while(tracks[i]){ 
				pos = new google.maps.LatLng(tracks[i]['lat']/1000000, tracks[i]['lon']/1000000);
				marker = new google.maps.Marker({
					map:map,
					draggable:false,
					animation: google.maps.Animation.DROP,
					position: pos
				});
												
				i++ ;
			};
			 map.setCenter(pos)	;
		}
		
		google.maps.event.addDomListener(window, 'load', initialize);

		 function cron()
			{
				var id=<?php echo $ticket_id;?>;
				
				$.getJSON("<?=base_url()?>index.php/tracker/ajax_map?ticket_id="+id,
					function(data){
						console.log(data);
						tracks = data;
						initialize();
					});
			};
				
			

		time=setInterval(cron, 30000); //cada 30 segundos llamará a la función   
</script>  
<?php if(isset($descripcion)){ ?>	
 <div style="background-color: rgb(255, 255, 255); width: 500px; height: 100px; text-align: center;" id="info">
 <?php echo $descripcion; ?>
</div>
<?php } ?>
<div id="map_canvas" style="height: 500px;width:500px;"></div>



