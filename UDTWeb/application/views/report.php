<link rel="stylesheet" type="text/css" href="<?=base_url()?>css/ui-lightness/jquery-ui-1.8.16.custom.css" media="screen" />	
<script type="text/javascript" src="<?=base_url()?>js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/jquery-ui-1.8.16.custom.min.js"></script>


<meta charset="utf-8">
	<script>
	$(function() {
		var dates = $( "#from, #to" ).datepicker({
			maxDate: "+1D" ,
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			numberOfMonths: 2,
			onSelect: function( selectedDate ) {
				var option = this.id == "from" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );

			}
		});
	});

	 $("a").click(function () { 
		 alert('Handler for .click() called.');
	    });

	
	$(document).ready(function() {
		  $('#generate').click(function() {

			  $.ajax({
				  type: 'POST',
				  url: '<?php echo $report_url;?>',
				  data: 'imei='+$("#device").val()+'&from='+$("#from").val()+'&to='+$("#to").val(),
				  dataType:'json',
				  success: function(response){
					   var resp = response;
					   if(resp!=null){
					   for(var i=0;i< resp.length;i++){
					        var obj = resp[i];
					        $('#report_table').append('<tr id="fila'+i+'"><td>'+obj['timestamp']+'</td><td>'+obj['lat']+'</td><td>'+obj['lon']+'</td><td><a target="blank" href="http://maps.google.com/maps/api/staticmap?zoom=16&size=1024x1024&maptype=roadmap&markers=color:blue|'+(obj['lat']/1000000)+','+(obj['lon']/1000000)+'&sensor=false">Ver</a></td></tr>');
					  }
					   }
				  }
				});
			    
		  });
		});
	

</script>  

	
	</script>



<div class="demo">
<label>Dispositivo: </label>
<select id="device">
<?php 
foreach ( $devices as $device){
	echo "<option value=\"".$device["imei"]."\" >".$device["imei"]."</option>";
}
?>
</select>
<label for="from">Desde</label>
<input type="text" id="from" name="from"/>
<label for="to">hasta</label>
<input type="text" id="to" name="to"/>
<button id="generate">Generate!</button>
</div>
<div id="report">
<table id="report_table" border="1">
  <tr>
    <th>Timestamp</th>
    <th>Lat</th>
    <th>Lon</th>
    <th>Direccion</th>    
  </tr>
  
</table>
</div>

