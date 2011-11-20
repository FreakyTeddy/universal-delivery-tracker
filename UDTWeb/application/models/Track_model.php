<?php

include_once 'db_abstract_model.php';

class Track_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'tracks';
        }
        
        function get_all_by_offset($offset,$order=null){
                $this->db->limit($offset);
                
                if($order != null){
                        
                        $this->db->order_by($order);
                }
                
                return $this->db->get($this->tbl)->result_array();
        }
        
		 function update($imei, $data){
                try{
                        $this->db->where('imei', $imei);
                        $this->db->update($this->tbl, $data);
                }catch(Exception $e){
                        throw new Exception('No fue posible realizar la actualizacion: ' . $e->getMessage());
                } 
        }
		
		function test($parametro){
			return  "El parametro ".$parametro;			
		}
        
}
?>