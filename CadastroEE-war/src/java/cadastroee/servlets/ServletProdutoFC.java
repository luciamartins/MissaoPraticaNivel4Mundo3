package cadastroee.servlets;

import cadastroee.model.Produto;
import cadastroee.controller.ProdutoFacadeLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucia
 */
@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        if (acao == null) {
            throw new jakarta.servlet.ServletException("Parâmetro Requerido");
        }

        String destino = (acao.equals("formAlterar") || acao.equals("formIncluir")) ? "ProdutoDados.jsp" : "ProdutoLista.jsp";
        List<Produto> produtos = null;
        try {
            switch (acao) {
                case "listar":
                    produtos = (List<Produto>) facade.findAll();
                    request.setAttribute("produtos", produtos);
                    break;

                case "formAlterar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    try {
                        Produto produto = facade.find(id);
                        request.setAttribute("produto", produto);
                    } catch (Exception e) {
                        System.out.println("Erro:" + e);
                    }
                    break;

                case "excluir":
                    int id_ex = Integer.parseInt(request.getParameter("id"));
                    Produto produto_ex = facade.find(id_ex);
                    try {
                        facade.remove(produto_ex);
                    } catch (Exception e) {
                        System.out.println("Erro ao remover:" + e);
                    }
                    produtos = (List<Produto>) facade.findAll();
                    request.setAttribute("produtos", produtos);
                    break;

                case "alterar":
                    int id_al = Integer.parseInt(request.getParameter("id"));
                    Produto produto_alterar = facade.find(id_al);
                    String nomeAlterar = request.getParameter("nome");
                    int quantidadeAlterar = Integer.parseInt(request.getParameter("quantidade"));
                    Float precoAlterar = Float.parseFloat(request.getParameter("precoVenda"));

                    produto_alterar.setNome(nomeAlterar);
                    produto_alterar.setQuantidade(quantidadeAlterar);
                    produto_alterar.setPrecoVenda(precoAlterar);
                    try {
                        facade.edit(produto_alterar);
                    } catch (Exception e) {
                        System.out.println("Erro ao alterar:" + e);
                    }
                    produtos = (List<Produto>) facade.findAll();
                    request.setAttribute("produtos", produtos);
                    break;

                case "incluir":
                    String nomeIncluir = request.getParameter("nome");
                    int quantidadeIncluir = Integer.parseInt(request.getParameter("quantidade"));
                    Float precoIncluir = Float.parseFloat(request.getParameter("precoVenda"));

                    Produto novoProduto = new Produto();
                    novoProduto.setNome(nomeIncluir);
                    novoProduto.setQuantidade(quantidadeIncluir);
                    novoProduto.setPrecoVenda(precoIncluir);
                    try {
                        facade.create(novoProduto);
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar: " + e);
                    }
                    produtos = (List<Produto>) facade.findAll();
                    request.setAttribute("produtos", produtos);
                    break;

                default:
                    produtos = (List<Produto>) facade.findAll();
                    request.setAttribute("produtos", produtos);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        /**
         * if(!acao.equals("incluir")){ request.setAttribute("produtos",
         * produtos); }*
         */
        if (!destino.isEmpty()) {
            RequestDispatcher rd = request.getRequestDispatcher(destino);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
