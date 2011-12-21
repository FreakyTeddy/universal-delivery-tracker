<?php

include_once 'db_abstract_model.php';

class Device_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'dispositivo';
        }
        
     
		
		
}
?>