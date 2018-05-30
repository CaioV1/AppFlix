<?php 

    class Producao{
        
        private $id;
        private $titulo;
        private $sinopse;
        private $imagem;
        private $nota;
        private $URL;
        
        public function setId($id){
            
            $this->id = $id;
            
        }
        
        public function getId(){
            
            return $this->id;
            
        }
        
        public function setTitulo($titulo){
            
            $this->titulo = $titulo;
            
        }
        
        public function getTitulo(){
            
            return $this->titulo;
            
        }
        
        public function setSinopse($sinopse){
            
            $this->sinopse = $sinopse;
            
        }
        
        public function getSinopse(){
            
            return $this->sinopse;
            
        }
        
        public function setImagem($imagem){
            
            $this->imagem = $imagem;
            
        }
        
        public function getImagem(){
            
            return $this->imagem;
            
        }
        
        public function setNota($nota){
            
            $this->nota = $nota;
            
        }
        
        public function getNota(){
            
            return $this->nota;
            
        }
        
        public function setURL($URL){
            
            $this->URL = $URL;
            
        }
        
        public function getURL(){
            
            return $this->URL;
            
        }
        
    }

?>