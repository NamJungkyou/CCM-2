package ccm.srvlt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ImageDateServlet.java와 동일한 기능(emloyee테이블에서 사진호출)
 * 자세한 사항은 ImageDateServlet 참조
 * 
 * @작성자 글로벌IT경영 김민현
 */

/**
 * Servlet implementation class EmployeeImageDataServlet
 */
@WebServlet("/EmployeeImageDataServlet")
public class EmployeeImageDataServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String jdbcDrvier = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://ccmfmsmysqlserver.mysql.database.azure.com/fms";
	private final static String id = "ccmadmin@ccmfmsmysqlserver";
	private final static String pwd = "ccmfmsmysqladmin1234!";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*String freePic = request.getParameter("freePic");*/
		String empPicture = request.getParameter("empPicture");
		if (empPicture == null)
			return;
		response.setContentType("image/jpeg");
		try {
			Class.forName(jdbcDrvier);
			conn = DriverManager.getConnection(url, id, pwd);
			pstmt = conn.prepareStatement("select empFilePath from employee where empPicture=?");
			pstmt.setString(1, empPicture);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Blob blob = rs.getBlob(1);
				InputStream is = blob.getBinaryStream();
				OutputStream os = response.getOutputStream();
				int length;
				while((length = is.read())!= -1)
					os.write(length);
				is.close();
				os.close();
			}
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
