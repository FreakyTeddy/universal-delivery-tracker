<?php

include_once 'db_abstract_model.php';

class Ticket_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'ticket';
        }
       
	   function get_by_id($id){
                $this->db->where('id_ticket', $id);
				$this->db->where('tipo !=',2);
                return $this->db->get($this->tbl)->row_array();
       }
	   
	   function get_family_ticket($user_id){
			$sql = "SELECT t.* FROM cliente_genera_tickets ct , ticket t  WHERE ct.id_ticket=t.id_ticket and t.tipo=2 and ct.id_cliente =".$user_id;
	   		error_log($sql);
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->row_array();
			}
			return null;
	   }
        
}
?>