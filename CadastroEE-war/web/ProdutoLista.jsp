<%-- 
    Document   : ProdutoLista
    Created on : 23 de ago. de 2024, 12:48:19
    Author     : Lucia
--%>

<%@page import="cadastroee.model.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Produtos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body class="container">
        <div>
            <h1>Listagem de Produtos</h1>
            <div>
                <a class="btn btn-primary m-2" href="ServletProdutoFC?acao=formIncluir">Novo Produto</a>
            </div>
            <div>
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <td>#</td>
                            <td>Nome</td>
                            <td>Quantidade</td>
                            <td>Preço</td>
                            <td>Opções</td>
                        </tr>
                    </thead>
                    <%
                        List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");

                        if (produtos != null && !produtos.isEmpty()) {
                            for (Produto p : produtos) {
                    %>
                    <tr>
                        <td><%=p.getIdProduto()%></td>
                        <td><%=p.getNome()%></td>
                        <td><%=p.getQuantidade()%></td>
                        <td><%=p.getPrecoVenda()%></td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="ServletProdutoFC?acao=formAlterar&id=<%=p.getIdProduto()%>">Alterar</a>
                            <a class="btn btn-danger btn-sm" href="ServletProdutoFC?acao=excluir&id=<%=p.getIdProduto()%>">Excluir</a>
                        </td>
                    </tr>
                    <%}
                    } else {%>
                    <tr>
                        <td colspan="5"><%=produtos%> : Nenhum produto encontrado.</td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </body>
</html>
