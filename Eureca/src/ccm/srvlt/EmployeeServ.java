package ccm.srvlt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ccm.controller.actfac.EmployeeAF;
import ccm.controller.action.Action;

/**
 * Servlet implementation class EmployeeServ
 */
@WebServlet("/EmployeeServ")
public class EmployeeServ extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServ() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      
      String command = request.getParameter("command");
      System.out.println("EmployeeServ에서 요청을 받음을 확인 : "+command);
      
      System.out.println(command);
      
      EmployeeAF ef = (EmployeeAF) EmployeeAF.getInstance();
      Action action = ef.getAction(command);
      
      if(action != null)
      {
         action.execute(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      doGet(request, response);
   }

}