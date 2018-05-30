<?php 

    require_once("ConexaoBanco.php");

    $conexao = ConexaoBanco::obterConexao();

    $id = $_GET["id"];

    if($conexao == null){
        
        echo(json_encode(array("sucesso" => false)));
        
    } else {
        
        $SQL = "SELECT * FROM tbl_producao WHERE id_producao = ?";
        
        $statement = $conexao->prepare($SQL);
        
        $statement->bindParam(1, $id);
        
        $statement->execute();
        
        $statement->setFetchMode(PDO::FETCH_ASSOC);
        
        $resultSet = $statement->fetch();
            
        $conexao = null;
        
        echo(json_encode($resultSet));
        
    }

?>