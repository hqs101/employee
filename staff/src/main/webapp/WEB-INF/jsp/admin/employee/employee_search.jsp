<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/employee/search" >
                    		<!-- 查询开始 -->
                            <div class="form-group">
                                <label for="query-user" class="sr-only2">用户ID</label>
                                	<input type="hidden" id="query-user-id" name="user.id" value="${query.user.id }" class="form-control">
				                 	<input type="text" class="form-control" id="user-name" name="user.id" value="${query.user.id }" 
				                 		onclick="bring({
										                 	url:'${ctx}/admin/user/list?search=true',
										                 	name: 'id',
										                 	idInput: 'user-id',
										                 	nameInput: 'user-name'
										                 	})" />	
                            </div>
                            <div class="form-group">
                                <label for="query-name" class="sr-only2">姓名</label>
                                	<input type="text" id="query-name" name="name" value="${query.name }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                                <label for="query-phone" class="sr-only2">联系电话</label>
                                	<input type="text" id="query-phone" name="phone" value="${query.phone }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                                <label for="query-status" class="sr-only2">员工状态/1.录用/2.停职</label>
                                	<input type="text" id="query-status" name="status" value="${query.status }" class="form-control input-sm">
                                
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
                                            <th data-field="user">用户ID</th>
                                            <th data-field="position">岗位</th>
                                            <th data-field="name">姓名</th>
                                            <th data-field="birth">出生日期</th>
                                            <th data-field="sex">性别</th>
                                            <th data-field="height">身高</th>
                                            <th data-field="weight">体重</th>
                                            <th data-field="place">籍贯</th>
                                            <th data-field="nation">民族</th>
                                            <th data-field="phone">联系电话</th>
                                            <th data-field="iDCard">身份证号</th>
                                            <th data-field="school">毕业学校</th>
                                            <th data-field="major">专业</th>
                                            <th data-field="education">学历</th>
                                            <th data-field="location">户口所在地</th>
                                            <th data-field="address">居住地址</th>
                                            <th data-field="wechat">微信号</th>
                                            <th data-field="email">邮箱</th>
                                            <th data-field="marry">婚姻状况</th>
                                            <th data-field="inTime">入职时间</th>
                                            <th data-field="status">员工状态/1.录用/2.停职</th>
                                        </tr>
                                    </thead>
							<tbody>
								<c:forEach items="${list }" var="n" varStatus="idx">
								<tr data-index="${idx.index }" data-id="${n.id }">
									<td></td>
									<td style="">${n.id }</td>
									<td style="">${n.user.id}</td>
									<td style="">${n.position }</td>
									<td style="">${n.name }</td>
									<td style="">
										<fmt:formatDate value="${n.birth }" pattern=""/>
									</td>
									<td style="">${n.sex }</td>
									<td style="">${n.height }</td>
									<td style="">${n.weight }</td>
									<td style="">${n.place }</td>
									<td style="">${n.nation }</td>
									<td style="">${n.phone }</td>
									<td style="">${n.iDCard }</td>
									<td style="">${n.school }</td>
									<td style="">${n.major }</td>
									<td style="">${n.education }</td>
									<td style="">${n.location }</td>
									<td style="">${n.address }</td>
									<td style="">${n.wechat }</td>
									<td style="">${n.email }</td>
									<td style="">${n.marry }</td>
									<td style="">
										<fmt:formatDate value="${n.inTime }" pattern=""/>
									</td>
									<td>${enum:list('EmployeeStatus', n.status)}</td>
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