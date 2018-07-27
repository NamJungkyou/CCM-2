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
 * 업로드된 사진을 불러오는 Servlet
 * DB연동 및 서버와 연결을 시켜줘야함
 * web.xml에서 서블릿명과 url-pattern을 선언해야함
 * 
 * @작성자 글로벌IT경영 김민현
 */

/**
 * Servlet implementation class ImageDataServlet
 */
@WebServlet("/ImageDataServlet")
public class ImageDataServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String jdbcDrvier = "com.mysql.jdbc.Driver"; // jdbcDrvier 명칭
	private final static String url = "jdbc:mysql://ccmfmsmysqlserver.mysql.database.azure.com/fms"; // 연동DB url
	private final static String id = "ccmadmin@ccmfmsmysqlserver"; // 연동DB 아이디
	private final static String pwd = "ccmfmsmysqladmin1234!"; // 연동DB 비밀번호
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String freePic = request.getParameter("freePic");
		if(freePic == null)
			return;
		response.setContentType("image/jpeg"); // 사진타입을 지정
		try {
			Class.forName(jdbcDrvier);
			conn = DriverManager.getConnection(url, id, pwd);
			// freelancer 테이블에서 freePic이름을 기준으로 DB에 저장된 freeFilePath(사진저장경로)를 불러오는 sql문
			pstmt = conn.prepareStatement("select freeFilePath from freelancer where freePic=?");
			pstmt.setString(1, freePic); // String으로 선언한 사진이름을 freePic=?에 넣어줌
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Blob blob = rs.getBlob(1); // blob타입으로 저장된 사진 호출을 위해 blob선언
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
