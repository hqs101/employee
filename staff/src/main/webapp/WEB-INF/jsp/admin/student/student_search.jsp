<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="student"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/student/search" >
                    		<!-- 查询开始 -->
                            <div class="form-group">
                                <label for="query-name" class="sr-only2">姓名</label>
                                	<input type="text" id="query-name" name="name" value="${query.name }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                            	<button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-check"></i>&nbsp;查询</button>
                            </div>
                            <!-- 查询结束 -->
                        </form>
                    
						<table id="table" data-toggle="table" data-click-to-select="true" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                        	<th data-field="_state" data-radio="true"></th>
                                            <th data-field="id" data-visible="false">ID</th>
                                            <th data-field="name">姓名</th>
                                            <th data-field="age">年龄</th>
                                        </tr>
                                    </thead>
							<tbody>
								<c:forEach items="${list }" var="n" varStatus="idx">
								<tr data-index="${idx.index }" data-id="${n.id }">
									<td></td>
									<td style="">${n.id }</td>
									<td style="">${n.name }</td>
									<td style="">${n.age }</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
                   
						<%@include file="/common/page.jsp" %>

					</div>
                </div>
            </div>
        </div>
    </div>

<%@include file="/common/foot.jsp" %>
<script>

var page = "${page.page}";
function reload() {
	page_submit(page);
}

paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
</html>