	<script>

	$(document).ready(function() {
		  $('#agregar').click(function() {

			  $.ajax({
				  type: 'POST',
				  url: '<?php echo $add_url;?>',
				  data: 'imei='+$("#device").val()+'&descripcion='+$("#descripcion").val(),
				  success: function(response){
						if( response != -1 ){
								alert('Se creo el ticket# ' + response);
						}else{
							alert('No se pudo crear el ticket');
							}
						$("#descripcion").val(''); 
						  }
				});
			    
		  });
		});
	

</script>  

<label>Dispositivo: </label>
<select id="device">
<?php 
if(isset($devices)){
foreach ( $devices as $device){
	echo "<option value=\"".$device["imei"]."\" >".$device["imei"]."</option>";
}
}
?>

</select>
Descripcion del Pedido: <input type="text" name="descripcion"id="descripcion" height="100" /></br>
<input type="submit" id="agregar" value="Agregar" />
</form>