<?php 

    define("dominio", "localhost");
    define("usuario", "root");
    define("senha", "bcd127");
    define("banco", "db_appflix");

    class ConexaoBanco{
        
        public static function obterConexao(){
            
            $conexao = null;
        
            try{

                $conexao = new PDO("mysql:host=".dominio.";dbname=".banco, usuario, senha);

                $conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            } catch(Exception $e){

                echo($e->getMessage());

            }

            return($conexao);    

        }
        
    }

?>