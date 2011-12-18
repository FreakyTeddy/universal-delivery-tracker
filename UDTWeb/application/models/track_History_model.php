<?php

include_once 'db_abstract_model.php';

class Track_History_Model extends Db_Abstract_Model {
        
        function __construct() {
                parent::__construct();
                $this->tbl = 'tracks_history';
        }
        
}
?>