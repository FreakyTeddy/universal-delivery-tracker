<?php

include_once 'db_abstract_model.php';

class Device_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'dispositivo';
        }
        
     
  function list_all($order = 'imei asc'){
                $this->db->order_by($order);
                return $this->db->get($this->tbl)->result_array();
        }
		
}
?>