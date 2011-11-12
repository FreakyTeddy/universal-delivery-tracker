<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	<script>
		!window.jQuery && document.write('<script src="<?=base_url()?>js/jquery-1.4.3.min.js"><\/script>');
	</script>

<!--   Note: you will need to replace the sensor parameter below with either an explicit true or false value.-->
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAAJK4GHHlJh0Xk9L441bOhXBSydMAXsgAohD7A40hRBICJDy9EmBTihOqq13-Foa2npVBjRHDoP2auqQ" type="text/javascript"></script>
	
<script type="text/javascript" src="<?=base_url()?>js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<?=base_url()?>js/jquery.gmap-1.1.0.js"></script>
<link rel="stylesheet" type="text/css" href="<?=base_url()?>js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" type="text/css" href='<?=base_url()?>css/anoceanofsky.css' />
<title> ::: Universal Tracker ::: </title>
</head>

<body>
    <div id="page">
        <div class="topNaviagationLink"><?php echo anchor('/welcome/index', 'Inicio'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('', 'Productos'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('/tracker/index/', 'Trackear'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('', 'Contactenos'); ?></div>
        <?php if ( $this->tank_auth->is_logged_in() ) { ?>
        <div class="topNaviagationLink"><?php echo anchor('/auth/logout/', 'Logout'); ?></div>
        <?php  }else{?>
        <div class="topNaviagationLink"><?php echo anchor('/auth/login/', 'Login'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('/auth/register/', 'Register'); ?></div>
        <?php }?>
        
	</div>
	<div id="main_page">
		 <?php if(isset($current_view)){$this->load->view($current_view);}?>
	</div>	
	<div id="footer">
		Web development</a> by <a href="http://www.americanoticias.multimediosamerica.com.ar/blog.php/toti_pasman">Totti Passman</a> | <a href="http://www.quackit.com">web tutorials</a> | <a href="http://www.htmlcodes.me">html code</a> | <a href="http://www.htmlcodes.me">copyright 2011</a> 
	</div>
</body>
</html>   