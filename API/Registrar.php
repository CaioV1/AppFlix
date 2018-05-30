<?php

    require_once("ConexaoBanco.php");
    require_once("Producao.php");

    $conexao = ConexaoBanco::obterConexao();

    if($conexao == null){

        echo(json_encode(array("sucesso" => false)));

    } else {

        $producao = new Producao();

        $imagem64 = $_POST['imagem'];

        $bytes = base64_decode($imagem64);

        header('Content-Type: bitmap; charset=utf-8');

        $imagemNome = uniqid().".jpg";

        $arquivo = fopen('Imagens/'.$imagemNome, 'wb');

        fwrite($arquivo, $bytes);

        fclose($arquivo);

        $producao->setTitulo($_POST["titulo"]);
        $producao->setSinopse($_POST["sinopse"]);
        $producao->setImagem($imagemNome);
        $producao->setNota($_POST["nota"]);
        $producao->setURL($_POST["url"]);

        $SQL = "INSERT INTO tbl_producao(titulo, sinopse, imagem, nota, url) VALUES(?, ?, ?, ?, ?)";

        $statement = $conexao->prepare($SQL);

        $statement->bindValue(1, $producao->getTitulo());
        $statement->bindValue(2, $producao->getSinopse());
        $statement->bindValue(3, $producao->getImagem());
        $statement->bindValue(4, $producao->getNota());
        $statement->bindValue(5, $producao->getURL());

        $envio = $statement->execute();

        $conexao = null;

        if($envio){

            echo(json_encode(array("sucesso" => true)));

        } else {

            echo(json_encode(array("sucesso" => false)));

        }

    }

?>
