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
		
		function get_tracks_by_ticket($ticket_id){
			$sql = "SELECT lat, lon  FROM tracks WHERE   imei in ( select id_dispositivo from disp_reporta_ticket where id_ticket = ".$ticket_id.")";
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;
		}
		
		function get_family_tracks($ticket_id){
			$sql = "SELECT lat, lon, descripcion  FROM tracks t, dispositivo d WHERE   t.imei=d.imei and t.imei in ( select id_dispositivo from disp_reporta_ticket where id_ticket = ".$ticket_id.")";
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;
		}
		
		
}
?>