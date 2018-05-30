<?php 

    require_once("ConexaoBanco.php");

    $conexao = ConexaoBanco::obterConexao();

    if($conexao == null){
        
        echo(json_encode(array("sucesso" => false)));
        
    } else {
        
        $id = $_GET["id"];
        
        $SQL = "DELETE FROM tbl_producao WHERE id_producao = ?";
        
        $statement = $conexao->prepare($SQL);
        
        $statement->bindParam(1, $id);
        
        $envio = $statement->execute();
        
        $conexao = null;
        
        if($envio){
            
            echo(json_encode(array("sucesso" => true)));
            
        } else {
            
            echo(json_encode(array("sucesso" => false)));
            
        }

    }
            
?>