<%-- 
    Document   : ProdutoDados
    Created on : Aug 23, 2024, 9:54:06 PM
    Author     : Lucia
--%>

<%@page import="cadastroee.model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados do Produto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body class="container">
        <%
            Produto produto = (Produto) request.getAttribute("produto");
            String acao = produto != null ? "alterar" : "incluir";
        %>
        <div>
            <h1>Dados do Produto</h1>
            <div>
                <div>
                    <a class="btn btn-primary m-2" href="ServletProdutoFC?acao=listar">Voltar</a>
                </div>
                <div>
                    <form action="ServletProdutoFC" method="post">
                        <input type="hidden" name="acao" value="<%=acao%>" />
                        <%if (produto != null) {%>
                        <input type="hidden" name="id" value="<%=produto.getIdProduto()%>" /> 
                        <%}%>
                        <div class="mb-3">
                            <label class="form-label" for="nome">Nome:</label>
                            <input class=" form-control" type="text" name="nome" value="<%=produto != null ? produto.getNome() : ""%>" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="quantidade">Quantidade:</label><br>
                            <input class=" form-control" type="text" name="quantidade" value="<%=produto != null ? produto.getQuantidade() : ""%>" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="precoVenda">Preço:</label>
                            <input class=" form-control" type="text" name="precoVenda" value="<%=produto != null ? produto.getPrecoVenda() : ""%>" required/>
                        </div>
                        <div>
                            <input class="btn btn-primary" type="submit" value="<%=acao == "incluir" ? "Cadastrar" : "Alterar"%>" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
