<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href='<?=base_url()?>css/anoceanofsky.css' />
<title> ::: Universal Tracker ::: </title>
</head>

<body>
    <div id="page">
        <div class="topNaviagationLink"><?php echo anchor('/welcome/index', 'Inicio'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('', 'Productos'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('', 'Trackear'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('', 'Contactenos'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('/auth/login/', 'Login'); ?></div>
        <div class="topNaviagationLink"><?php echo anchor('/auth/register/', 'Register'); ?></div>
	</div>
	<div id="main_page">
		 <?php if(isset($current_view)){$this->load->view($current_view);}?>
	</div>	
	<div id="footer">
		Web development</a> by <a href="http://www.americanoticias.multimediosamerica.com.ar/blog.php/toti_pasman">Totti Passman</a> | <a href="http://www.quackit.com">web tutorials</a> | <a href="http://www.htmlcodes.me">html code</a> | <a href="http://www.htmlcodes.me">copyright 2011</a> 
	</div>
</body>
</html>   