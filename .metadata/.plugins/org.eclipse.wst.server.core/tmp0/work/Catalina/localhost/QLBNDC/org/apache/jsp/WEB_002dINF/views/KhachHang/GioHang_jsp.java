/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.55
 * Generated at: 2022-06-06 15:33:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.KhachHang;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class GioHang_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/D:/HK2-Nam-3/CNPM/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/QLBNDC/WEB-INF/lib/spring-webmvc-4.0.1.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1390885082000L));
    _jspx_dependants.put("jar:file:/D:/HK2-Nam-3/CNPM/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/QLBNDC/WEB-INF/lib/jstl-impl.jar!/META-INF/c.tld", Long.valueOf(1343794618000L));
    _jspx_dependants.put("/WEB-INF/tags/taglib.jsp", Long.valueOf(1653219132149L));
    _jspx_dependants.put("/WEB-INF/lib/spring-webmvc-4.0.1.RELEASE.jar", Long.valueOf(1652342471921L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-impl.jar", Long.valueOf(1652342471852L));
    _jspx_dependants.put("jar:file:/D:/HK2-Nam-3/CNPM/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/QLBNDC/WEB-INF/lib/spring-webmvc-4.0.1.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1390885082000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"utf-8\">\r\n");
      out.write("<!--  This file has been downloaded from bootdey.com @bootdey on twitter -->\r\n");
      out.write("<!--  All snippets are MIT license http://bootdey.com/license -->\r\n");
      out.write("<title>Giỏ hàng</title>\r\n");
      out.write("<base href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.servletContext.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/\" />\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\r\n");
      out.write("<link\r\n");
      out.write("	href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css\"\r\n");
      out.write("	rel=\"stylesheet\">\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<!--navigation-->\r\n");
      out.write("	<nav class=\"navbar navbar-expand-md navbar-dark bg-danger sticky-top\"\r\n");
      out.write("		margin=\"auto\">\r\n");
      out.write("		<div class=\"container-fluid\">\r\n");
      out.write("			<a href=\"#\" class=\"navbar-brand\"> <img src=\"asset/logo.png\"\r\n");
      out.write("				alt=\"\">\r\n");
      out.write("			</a>\r\n");
      out.write("			<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"\r\n");
      out.write("				data-target=\"#navbarReponsive\">\r\n");
      out.write("				<span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("			</button>\r\n");
      out.write("			<div class=\"collapse navbar-collapse\" id=\"navbarReponsive\">\r\n");
      out.write("				<ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("					<li class=\"nav-item active\"><a href=\"KhachHang/TrangChu.htm\"\r\n");
      out.write("						class=\"nav-link\">Trang chủ</a></li>\r\n");
      out.write("					<li class=\"nav-item active\"><a href=\"KhachHang/GioHang.htm\"\r\n");
      out.write("						class=\"nav-link\">Giỏ hàng</a></li>\r\n");
      out.write("					<li class=\"nav-item active\"><a href=\"KhachHang/XemThongTinCaNhan.htm\"\r\n");
      out.write("						class=\"nav-link\">Thông tin cá nhân</a></li>\r\n");
      out.write("					<li class=\"nav-item active\"><a href=\"KhachHang/LichSuMua.htm\"\r\n");
      out.write("						class=\"nav-link\">Lịch sử mua hàng</a></li>\r\n");
      out.write("					<li class=\"nav-item active\"><a href=\"Login/LogOut.htm\"\r\n");
      out.write("						class=\"nav-link\">Đăng Xuất</a></li>\r\n");
      out.write("				</ul>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</nav>\r\n");
      out.write("	<div class=\"container px-3 my-5 clearfix\">\r\n");
      out.write("		<!-- Shopping cart table -->\r\n");
      out.write("		<div class=\"step-wizard\">\r\n");
      out.write("				<ul class=\"step-wizard-list\">\r\n");
      out.write("					<li class=\"step-wizard-item current-item\"><span\r\n");
      out.write("						class=\"progress-count\">1</span> <span class=\"progress-label\">CHỌN\r\n");
      out.write("							HÀNG</span></li>\r\n");
      out.write("					<li class=\"step-wizard-item\"><span class=\"progress-count\">2</span>\r\n");
      out.write("						<span class=\"progress-label\">XÁC NHẬN THÔNG TIN</span></li>\r\n");
      out.write("					<li class=\"step-wizard-item\"><span class=\"progress-count\">3</span>\r\n");
      out.write("						<span class=\"progress-label\">XÁC NHẬN ĐƠN HÀNG</span></li>\r\n");
      out.write("					<li class=\"step-wizard-item \"><span class=\"progress-count\">4</span>\r\n");
      out.write("						<span class=\"progress-label\">THANH TOÁN</span></li>\r\n");
      out.write("				</ul>\r\n");
      out.write("\r\n");
      out.write("			</div>\r\n");
      out.write("		<div class=\"card\">\r\n");
      out.write("			<div class=\"card-header\">\r\n");
      out.write("				<h2>Giỏ hàng</h2>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"card-body\">\r\n");
      out.write("				<div class=\"table-responsive\">\r\n");
      out.write("					<table class=\"table table-bordered m-0\">\r\n");
      out.write("						<thead>\r\n");
      out.write("							<tr>\r\n");
      out.write("								<!-- Set columns width -->\r\n");
      out.write("								<th class=\"text-center py-3 px-4\" style=\"min-width: 400px;\">Sản\r\n");
      out.write("									phẩm</th>\r\n");
      out.write("								<th class=\"text-right py-3 px-4\" style=\"width: 100px;\">Giá</th>\r\n");
      out.write("								<th class=\"text-center py-3 px-4\" style=\"width: 120px;\">Số\r\n");
      out.write("									lượng</th>\r\n");
      out.write("								<th class=\"text-center align-middle py-3 px-0\"\r\n");
      out.write("									style=\"width: 40px;\"></th>\r\n");
      out.write("							</tr>\r\n");
      out.write("						</thead>\r\n");
      out.write("						<tbody>\r\n");
      out.write("							<form action=\"KhachHang/GioHang.htm\" , method=\"POST\" id=\"form1\">\r\n");
      out.write("								");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("							</form>\r\n");
      out.write("						</tbody>\r\n");
      out.write("					</table>\r\n");
      out.write("				</div>\r\n");
      out.write("				<!-- / Shopping cart table -->\r\n");
      out.write("\r\n");
      out.write("				<div class=\"float-right\" style=\"margin-top: 20px;\">\r\n");
      out.write("					<button type=\"submit\"\r\n");
      out.write("						class=\"btn btn-lg btn-default md-btn-flat mt-2 mr-3\" form=\"form1\"\r\n");
      out.write("						name=\"btnTC\">Về trang chủ</button>\r\n");
      out.write("					<button type=\"submit\" class=\"btn btn-lg btn-primary mt-2\"\r\n");
      out.write("						form=\"form1\" name=\"btnDH\">Bước Kế</button>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("	<!--footer-->\r\n");
      out.write("	<footer>\r\n");
      out.write("		<div class=\"container-fluid padding\"\r\n");
      out.write("			style=\"background-color: #3f3f3f; color: #d5d5d5; padding-top: 2rem;\">\r\n");
      out.write("			<div class=\"row text-center\">\r\n");
      out.write("				<div class=\"col-md-4\">\r\n");
      out.write("\r\n");
      out.write("					<p>123-456-789</p>\r\n");
      out.write("					<p>Email : winmart@gmail.com</p>\r\n");
      out.write("					<p>512 Quốc lộ 13</p>\r\n");
      out.write("					<p>Bình Thạnh,TP.Hồ Chí Minh</p>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-md-4\">\r\n");
      out.write("\r\n");
      out.write("					<p>Hỗ trợ khách hàng</p>\r\n");
      out.write("					<p>Thông tin về công ty</p>\r\n");
      out.write("					<p>Tuyển dụng việc làm</p>\r\n");
      out.write("					<p>Khiếu nại</p>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-md-4\">\r\n");
      out.write("\r\n");
      out.write("					<p>Facebook</p>\r\n");
      out.write("					<p>Về trang chủ</p>\r\n");
      out.write("\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/views/KhachHang/GioHang.jsp(20,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/resource/KhachHang/GioHang.css");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    boolean _jspx_th_c_005fforEach_005f0_reused = false;
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/views/KhachHang/GioHang.jsp(87,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("lsp");
      // /WEB-INF/views/KhachHang/GioHang.jsp(87,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/KhachHang/GioHang.jsp(87,8) '${listSanPham}'",_jsp_getExpressionFactory().createValueExpression(_jspx_page_context.getELContext(),"${listSanPham}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("									<tr>\r\n");
            out.write("										<td class=\"p-4\">\r\n");
            out.write("											<div class=\"media align-items-center\">\r\n");
            out.write("\r\n");
            out.write("												<div class=\"media-body\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lsp.TENSP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</div>\r\n");
            out.write("											</div>\r\n");
            out.write("										</td>\r\n");
            out.write("										<td class=\"text-right font-weight-semibold align-middle p-4\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lsp.GIA}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</td>\r\n");
            out.write("										<td class=\"align-middle p-4\"><input type=\"number\"\r\n");
            out.write("											name=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lsp.MASP}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\" class=\"form-control text-center\"\r\n");
            out.write("											value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${listSL.get(listSanPham.indexOf(lsp))}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\"></td>\r\n");
            out.write("										<td class=\"text-center align-middle px-0\"><a\r\n");
            out.write("											href=\"KhachHang/GioHang/");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lsp.getMASP()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write(".htm\"\r\n");
            out.write("											class=\"shop-tooltip close float-none text-danger\" title=\"\"\r\n");
            out.write("											data-original-title=\"Remove\">×</a></td>\r\n");
            out.write("									</tr>\r\n");
            out.write("								");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      _jspx_th_c_005fforEach_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fforEach_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fforEach_005f0_reused);
    }
    return false;
  }
}
