<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	<script>
		!window.jQuery && document.write('<script src="<?=base_url()?>js/jquery-1.4.3.min.js"><\/script>');
	</script>

<!-- Google Maps Api.-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="http://cdn.jquerytools.org/1.2.6/all/jquery.tools.min.js"></script> 	
<script type="text/javascript" src="<?=base_url()?>js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/jquery.gmap-1.1.0.js"></script>
<link rel="stylesheet" type="text/css" href="<?=base_url()?>js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<title>CASA DE EMPANADAS</title>
</head>

<body>
	ADMINISTRADOR DE PEDIDOS:
	<div id="menu">
		<a href="<?=base_url()?>index.php/empanadas/crear_ticket">Crear Ticket</a>
		<a href="<?=base_url()?>index.php/empanadas/listar_tickets">Ver Pedidos</a>
	</div>
   <div id="main_page">
		 <?php if(isset($current_view)){$this->load->view($current_view);}?>
	</div>	
	
</body>
</html>   