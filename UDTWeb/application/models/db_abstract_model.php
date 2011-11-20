<?php
abstract class Db_Abstract_Model extends CI_Model {
        
        protected $tbl;
        
        function __construct() {
                parent::__construct();
        }
        
        function list_all($order = 'id asc'){
                $this->db->order_by($order);
                return $this->db->get($this->tbl)->result_array();
        }
        
        function get_paged_list($limit = 10, $offset = 0, $order = 'id asc'){
                $this->db->order_by($order);
                return $this->db->get($this->tbl, $limit, $offset);
        }
        
        function count_all(){
                return $this->db->count_all($this->tbl);
        }

        function get_by_id($id){
                $this->db->where('id', $id);
                return $this->db->get($this->tbl)->row_array();
        }
        
        function get_random() {
                
                $this->db->order_by("id", "RANDOM"); 
                $this->db->limit(1);
                $this->db->where(array('active' => '1'));
                return $this->db->get($this->tbl)->row_array();
        }
        
        
        function save($data){
                $this->db->insert($this->tbl, $data);
                return $this->db->insert_id();
        }
        
        function update($id, $data){
                try{
                        $this->db->where('id', $id);
                        $this->db->update($this->tbl, $data);
                }catch(Exception $e){
                        throw new Exception('No fue posible realizar la actualizacion: ' . $e->getMessage());
                } 
        }
        
        function delete($id){
                $this->db->where('id', $id);
                $this->db->delete($this->tbl);
        }

        
        function get_by_imei($imei){
                $this->db->where('imei', $imei);
                return $this->db->get($this->tbl)->row_array();
        }
        
        function get_by_name($name){
                $this->db->where('name', $name);
                return $this->db->get($this->tbl)->row_array();
        }
}
?>