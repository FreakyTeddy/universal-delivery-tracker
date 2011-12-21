<script type="text/javascript">
	<?php if(isset($error)){
		echo "alert('".$error."');";
		unset($error);
	}?>
</script>
<div id="pack">
Pack:<?php echo $pack["nombre"];?></br>
Descripci&oacute;n:<?php echo $pack["descripcion"];?>
</div>

<div id="servicios">
Servicios incluidos en el pack:
	<div id="contratados">
	<?php 
	if(is_array($services)){
		foreach ($services as $service) {
			echo "<div id=\"item\">".$service["nombre"]."</div>";
		}
	}
	?>
	</div>
	<label>Dispositivos: </label>
<select id="device">
<?php 
foreach ( $devices as $device){
	echo "<option value=\"".$device["imei"]."\" >".$device["imei"]."</option>";
}
?>
</select>
</div>
<div id="reporte">
<a href="<?php echo $reportlink; ?>">Generar Reporte</a>
<a href="<?php echo $recorridolink; ?>">Generar Recorridos</a>
</br>
<a href="<?php echo $addlink; ?>">Agregar Dispositivo</a>
</div>