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
</div>
<div id="reporte">
<a href="<?php echo $reportlink; ?>">Generar Reporte</a>
</div>