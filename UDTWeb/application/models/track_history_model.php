<?php

include_once 'db_abstract_model.php';

class Track_History_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'tracks_history';
        }
        
        function get_tracks_by_date($device,$from,$to){
        	$sql = "SELECT * FROM tracks_history WHERE 	imei='$device' AND  timestamp between  '$from' AND  '$to'";
        	error_log($sql);
			$query = $this->db->query($sql);
			if ($query->num_rows() > 0){
				return  $query->result_array();
			}
			return null;
        	
        }
}
?>