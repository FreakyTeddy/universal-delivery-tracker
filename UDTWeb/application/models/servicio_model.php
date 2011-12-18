<?php

include_once 'db_abstract_model.php';

class Servicio_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'servicios';
        }
        
   		
		 function get_servicios_by_userid($user_id){
			$sql = "SELECT s.id_servicio FROM servicio_adherido s, pack_contratado pc WHERE s.id_pack = pc.id_pack AND pc.id_cliente = ".$user_id;
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;	   
	   }
		
}
?>