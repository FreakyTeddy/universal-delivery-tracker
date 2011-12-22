<link rel="stylesheet" type="text/css" href='<?=base_url()?>css/report.css' />
<script type="text/javascript">
	<?php if(isset($error)){
		echo "alert('".$error."');";
		unset($error);
	}?>
</script>

<div id="report_msj">
	<div id="pack">
		<div class="title">Pack:</div>
		<div class="name"><?php echo $pack["nombre"];?></div>
		<div class="title">Descripci&oacute;n:</div>
		<div class="name"><?php echo $pack["descripcion"];?></div>
		
		<div class="servicios">Servicios incluidos en el pack: </div>
		<div id="contratados">
			<?php 
			if(is_array($services)){
				foreach ($services as $service) {
					echo "<div id=\"item\"> - ".$service["nombre"]."</div>";
				}
			}
			?>
		</div>
		<div class="row" style="padding-top:25px;">
			<label class="col1">Dispositivos: </label>
			<span class="col2">	
				<select id="device" class="input">
				<?php 
				foreach ( $devices as $device){
					echo "<option value=\"".$device["imei"]."\" >".$device["imei"]."</option>";
				}
				?>
				</select>
			</span>
		</div>		
	</div>
	<div id="reporte">
	<a href="<?php echo $reportlink; ?>">Generar Reporte</a>
	<a href="<?php echo $recorridolink; ?>">Generar Recorridos</a>
	</br>
	<a href="<?php echo $addlink; ?>">Agregar Dispositivo</a>
	</div>
</div>	