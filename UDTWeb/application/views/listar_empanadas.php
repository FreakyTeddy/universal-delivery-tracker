

PEDIDOS PENDIENTES:
<div id="report">
<table id="report_table" border="1">
  <tr>
    <th>Numero de Pedido</th>
    <th>Descripcion</th>
    <th>Repartidor</th>
  </tr>
  <?php 
if(isset($pedidos)){
	foreach ( $pedidos as $pedido ){
		$datos = json_decode($pedido["datos"]);
		echo "<tr><td>".$pedido["id_ticket"]."</td><td>".$pedido["descripcion"]."</td><td>".$datos->nombre."</td></tr>";
	}
}
?>
  
</table>
</div>
