<?php 

    require_once("ConexaoBanco.php");

    $conexao = ConexaoBanco::obterConexao();

    $listaProducoes = array();

    if($conexao == null){
        
        echo(json_encode(array("sucesso" => false)));
        
    } else {
        
        $SQL = "SELECT * FROM tbl_producao";
        
        $statement = $conexao->prepare($SQL);
        
        $statement->execute();
        
        $statement->setFetchMode(PDO::FETCH_ASSOC);
        
        while($resultSet = $statement->fetch()){
            
            $listaProducoes[] = $resultSet;
            
        }
        
        $conexao = null;
        
        echo(json_encode($listaProducoes));
        
    }

?>